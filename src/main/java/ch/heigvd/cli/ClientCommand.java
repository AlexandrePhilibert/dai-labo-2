package ch.heigvd.cli;

import picocli.CommandLine;

import java.util.Scanner;
import java.util.concurrent.Callable;

@CommandLine.Command(name="client", description = "Starts the client")
public class ClientCommand implements Callable<Integer> {

    @CommandLine.Option(names = {"-u", "--username"}, required = true)
    String username;

    private String recipient;

    private String getPrompt() {
        return "(" + (recipient == null ? "None" : recipient) + "> ";
    }

    @Override
    public Integer call() {
        // TODO: Send CONNECT command

        try (Scanner scanner = new Scanner(System.in)) {
            String command;

            do {
                System.out.print(getPrompt());

                command = scanner.nextLine();

                // TODO: Parse command and execute it
            } while (!command.equals("QUIT"));
        }

        return 0;
    }
}
