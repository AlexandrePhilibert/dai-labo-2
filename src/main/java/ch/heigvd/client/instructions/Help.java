package ch.heigvd.client.instructions;

import ch.heigvd.client.ClientState;

import java.io.IOException;

public class Help extends Instruction {
    @Override
    public void execute(ClientState state) throws IOException {
        System.out.println("? Type \\help to get this message");
        System.out.println("? Type \\to <inbox> in order to specify the recipient");
        System.out.println("? Type \\get <inbox> to get the messages sent to an inbox");
        System.out.println("? Type a message to send it to your current recipient");
    }
}
