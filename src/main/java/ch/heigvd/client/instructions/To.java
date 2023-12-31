package ch.heigvd.client.instructions;

import ch.heigvd.client.ClientState;
import ch.heigvd.client.tui.Dialoguer;

public class To extends Instruction {

    private final String recipient;

    public To(String recipient) {
        this.recipient = recipient;
    }

    @Override
    public void execute(ClientState state) {
        state.setRecipient(recipient);
    }
}
