package ch.heigvd.cli.instructions;

import ch.heigvd.cli.ClientState;
import ch.heigvd.commands.Send;

import java.io.IOException;

public class Message extends Instruction {
    private final String message;

    public Message(String message) {
        this.message = message;
    }

    @Override
    public void exectue(ClientState state) throws IOException, RuntimeException {
        if (state.getRecipient() == null) {
            throw new RuntimeException("Recipient must be set before sending message");
        }

        System.out.println(new Send(state.getRecipient(), message).to());

        state.getWriter().write(new Send(state.getRecipient(), message).to());
    }
}
