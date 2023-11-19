package ch.heigvd.cli;

import picocli.CommandLine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.Callable;

@CommandLine.Command(name = "server", description = "Starts the server")
public class ServerCommand implements Callable<Integer> {
    @CommandLine.Option(names = {"-h", "--host"}, description = "The hostname")
    String hostname;

    @CommandLine.Option(names = {"-p", "--port"}, description = "The port", defaultValue = "39168")
    int port;

    @Override
    public Integer call() {
        while(true) {
            try (ServerSocket serverSocket = new ServerSocket(port)) {
                try (
                    Socket socket = serverSocket.accept();
                    BufferedReader reader = new BufferedReader(
                        new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8)
                    )
                ) {
                }
            } catch (IOException e) {
                System.out.println("Failed to start server");

                return 1;
            }
        }
    }
}
