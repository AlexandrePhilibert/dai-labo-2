package ch.heigvd.cli.instructions;

import ch.heigvd.cli.ClientState;
import ch.heigvd.commands.Connect;

import java.io.IOException;

public class Login extends Instruction {

    private final String username;

    public Login(String username) {
        this.username = username;
    }

    @Override
    public void exectue(ClientState state) throws IOException {
        state.getWriter().write(new Connect(username).toString());
    }
}
