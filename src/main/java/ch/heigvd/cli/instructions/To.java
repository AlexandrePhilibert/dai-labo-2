package ch.heigvd.cli.instructions;

import ch.heigvd.cli.ClientState;

public class To extends Instruction {

    private final String recipient;

    public To(String recipient) {
        this.recipient = recipient;
    }

    @Override
    public void exectue(ClientState state) {
        state.setRecipient(recipient);
    }
}
