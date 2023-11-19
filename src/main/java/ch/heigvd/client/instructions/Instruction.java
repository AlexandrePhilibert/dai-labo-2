package ch.heigvd.client.instructions;

import ch.heigvd.client.ClientState;

import java.io.IOException;

public abstract class Instruction {
    public abstract void execute(ClientState state) throws IOException;
}
