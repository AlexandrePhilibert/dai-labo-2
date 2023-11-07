package ch.heigvd.cli;

import picocli.CommandLine;

import java.util.concurrent.Callable;

@CommandLine.Command(name = "server", description = "Starts the server")
public class ServerCommand implements Callable<Integer> {
    @CommandLine.Option(names = {"-h", "--host"}, description = "The hostname")
    String hostname;

    @CommandLine.Option(names = {"-p", "--port"}, description = "The port")
    int port;

    @Override
    public Integer call() {
        return 0;
    }
}
