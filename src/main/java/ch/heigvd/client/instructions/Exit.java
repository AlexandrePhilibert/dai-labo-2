package ch.heigvd.client.instructions;

import ch.heigvd.client.ClientState;

public class Exit extends Instruction {
    @Override
    public void execute(ClientState state) {
        // Noop for now, but could store state for example.
    }
}
