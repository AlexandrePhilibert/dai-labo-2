package ch.heigvd.client;

import ch.heigvd.protocol.commands.Command;
import ch.heigvd.exceptions.ParseException;
import ch.heigvd.protocol.CommandParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Writer;
import java.time.Instant;
import java.util.HashMap;

public class ClientState {
    private String recipient;

    private final Writer writer;

    private final BufferedReader reader;

    private final HashMap<String, Instant> lastGroupSync;

    public ClientState(Writer writer, BufferedReader reader) {
        this.writer = writer;
        this.reader = reader;
        this.lastGroupSync = new HashMap<>();
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public void setLastSync(String group, Instant lastSync) {
        lastGroupSync.put(group, lastSync);
    }

    public Instant getLastSync(String group) {
        return lastGroupSync.getOrDefault(group, Instant.ofEpochSecond(0));
    }

    public void sendCommand(Command command) throws IOException {
        writer.write(command.to() + "\n");
        writer.flush();
    }

    public Command receiveCommand() throws IOException, ParseException {
        String command = reader.readLine();

        return CommandParser.parse(command);
    }


}
