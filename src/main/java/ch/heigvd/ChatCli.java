package ch.heigvd;


import ch.heigvd.cli.ClientCommand;
import ch.heigvd.cli.ServerCommand;
import picocli.CommandLine;

@CommandLine.Command(
    name = "cpt",
    mixinStandardHelpOptions = true,
    version = "0.0.1",
    description = "A Chat client for the CPT protocol",
    subcommands = {
        ClientCommand.class,
        ServerCommand.class,
    }
)
public class ChatCli {}
