package ch.heigvd.cli.instructions;

import ch.heigvd.cli.ClientState;

import java.io.IOException;

public abstract class Instruction {
    public abstract void exectue(ClientState state) throws IOException;
}
