package ch.heigvd.client.instructions;

import ch.heigvd.client.ClientState;
import ch.heigvd.commands.Connect;

import java.io.IOException;

public class Login extends Instruction {

    private final String username;

    public Login(String username) {
        this.username = username;
    }

    @Override
    public void execute(ClientState state) throws IOException {
        state.getWriter().write(new Connect(username).toString());
    }
}
