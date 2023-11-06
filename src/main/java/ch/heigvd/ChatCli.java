package ch.heigvd;


import picocli.CommandLine;

import java.util.concurrent.Callable;

@CommandLine.Command(name = "cpt", mixinStandardHelpOptions = true, version = "0.0.1", description = "A Chat client for the CPT protocol")
public class ChatCli implements Callable<Integer> {

    @Override
    public Integer call() {
        return 0;
    }
}
