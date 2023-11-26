package ch.heigvd.client;

import ch.heigvd.client.instructions.*;
import picocli.CommandLine;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.concurrent.*;

@CommandLine.Command(name="client", description = "Starts the client")
public class ClientCommand implements Callable<Integer> {
    @CommandLine.Option(names = {"-h", "--host"}, description = "The hostname", required = true)
    String hostname;

    @CommandLine.Option(names = {"-p", "--port"}, description = "The port", defaultValue = "39168")
    int port;

    @CommandLine.Option(names = {"-u", "--username"}, required = true)
    String username;

    private ClientState state;

    private String getPrompt() {
        return "(" + (state.getRecipient() == null ? "None" : state.getRecipient()) + ") > ";
    }

    @Override
    public Integer call() throws RuntimeException, IOException {
        // TODO: Gracefully handle error

        try (
            Socket socket = new Socket(hostname, port);
            BufferedReader reader = new BufferedReader(
                new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8)
            );
            BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8)
            );
            Scanner scanner = new Scanner(System.in)
        ) {
            state = new ClientState(writer, reader);

            // Connect the user upon invocation
            InstructionFactory instructionFactory = new InstructionFactory();
            new Login(username).execute(state);

            while(true) {
                System.out.print(getPrompt());

                String line = scanner.nextLine();
                for (Instruction instruction : instructionFactory.parse(line)) {
                    instruction.execute(state);

                    if (instruction instanceof Exit ) {
                        return 0;
                    }
                }
            }
        }
    }
}