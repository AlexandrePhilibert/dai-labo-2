package ch.heigvd.server;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;
import picocli.CommandLine;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

@CommandLine.Command(name = "server", mixinStandardHelpOptions = true, version = "0.0.1", description = "A server implementation of the CPT protocol")
public class ServerCommand implements Callable<Integer> {
    private static final Logger LOGGER = LogManager.getLogger(ServerCommand.class);

    @CommandLine.Option(names = {"-p", "--port"}, description = "The port the server will listen to")
    private Integer port = 39168;
    @CommandLine.Option(names = {"-t", "--threads"}, description = "The number of concurrent connections the server will be able to handle")
    private Integer threads = 2;

    private final AtomicBoolean shutdownRequested = new AtomicBoolean(false);

    @Override
    public Integer call() throws Exception {
        // Add logging
        //TODO: Add a verbose flag to give more debug info.
        Configurator.setRootLevel(Level.INFO);

        // Add a shutdown hook, just so we can show a nice message on shutdown:
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            LOGGER.info("Interrupt requested, shutting down...");
            shutdownRequested.set(true);
        }));

        ExecutorService executor = null;
        try (
                ServerSocket serverSocket = new ServerSocket(port)
        ) {
            executor = Executors.newFixedThreadPool(
                    threads
            );

            LOGGER.info("Starting CPT server...");
            LOGGER.info("Listening on port :{}", port);

            while (!shutdownRequested.get()) {
                Socket clientSocket = serverSocket.accept();
                executor.submit(new ClientHandler(clientSocket));
            }

        } catch (IOException e) {
            LOGGER.error("An error occurred in the server thread: ", e);
        } finally {
            if (executor != null) {
                executor.shutdown();
            }
        }


        return 0;
    }
}
