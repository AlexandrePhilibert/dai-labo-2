package ch.heigvd.cli.instructions;

import ch.heigvd.cli.ClientState;

public class Exit extends Instruction {
    @Override
    public void exectue(ClientState state) {
        // Noop for now, but could store state for example.
    }
}
