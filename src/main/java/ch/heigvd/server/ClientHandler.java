package ch.heigvd.server;

import ch.heigvd.protocol.commands.*;
import ch.heigvd.protocol.enums.DeclineReason;
import ch.heigvd.exceptions.ParseException;
import ch.heigvd.protocol.CommandParser;
import ch.heigvd.server.utils.IdProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class ClientHandler implements Runnable {
    private final Logger logger;

    private final Socket clientSocket;
    private final String connectionId;

    private String username;

    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
        // TODO: Change this ID with a randomized session id.
        this.connectionId = IdProvider.getId();
        logger = LogManager.getLogger("ClientHandler {" + connectionId + "}");
    }

    @Override
    public void run() {
        logger.info("Received new connection from {}", clientSocket.getInetAddress().toString());

        try (
                clientSocket; // This allow to use try-with-resources with the socket
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(clientSocket.getInputStream(), StandardCharsets.UTF_8)
                );
                BufferedWriter out = new BufferedWriter(
                        new OutputStreamWriter(
                                clientSocket.getOutputStream(),
                                StandardCharsets.UTF_8
                        )
                )
        ) {
            // Wait for a line, and then process it
            String line;
            while ((line = in.readLine()) != null) {
                try {
                    Command command = CommandParser.parse(line);
                    List<Command> responses = apply(command);
                    for (Command response : responses) {
                        out.write(response.to());
                        out.write('\n');
                    }
                } catch (ParseException e) {
                    out.write(new Decline(DeclineReason.INVALID_COMMAND).to());
                    out.write('\n');
                }
                out.flush();
            }
        } catch (Exception e) {
            // TODO: Move this up so that the connection is not closed on an expected error
            logger.error("An error occurred!", e);
            throw new RuntimeException(e);
        } finally {
            logger.info("Closed connection");
        }
    }


    private List<Command> apply(Command command) {
        if (!isConnected()) {
            if (command instanceof Connect connect) {
                if (GroupManager.exists(connect.getUsername())) {
                    return List.of(new Decline(DeclineReason.USERNAME_TAKEN));
                }

                username = connect.getUsername();
                GroupManager.getGroup(username);

                return List.of(new Accept());
            } else {
                return List.of(new Decline(DeclineReason.NOT_CONNECTED));
            }
        }

        // The user is connected, we can do more things.
        if (command instanceof Connect) {
            return List.of(new Decline(DeclineReason.ALREADY_LOGGED_IN));
        } else if (command instanceof Send send) {
            logger.info("{} sent a message to {}: {}", this.username, send.getGroup(), send.getMessage());
            GroupManager.getGroup(send.getGroup())
                    .post(this.username, send.getMessage());

            return List.of(new Acknowledge());
        } else if (command instanceof Sync sync) {

            if (!sync.getTarget().startsWith("g_") && !sync.getTarget().equals(username)) {
                return List.of(new Decline(DeclineReason.ACCESS_DENIED));
            }

            logger.info("received a sync from {} for {}", username, sync.getTarget());

            List<Command> responses = new ArrayList<>(
                    GroupManager.getGroup(sync.getTarget())
                            .getAfter(sync.getInstant())
            );
            responses.add(new EndSync());

            return responses;
        } else {
            return List.of(new Decline(DeclineReason.UNSUPPORTED_COMMAND));
        }
    }


    private boolean isConnected() {
        return this.username != null;
    }
}
