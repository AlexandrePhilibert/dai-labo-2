package ch.heigvd.client.instructions;

import ch.heigvd.client.ClientState;
import ch.heigvd.client.tui.Dialoguer;
import ch.heigvd.client.tui.ErrorHelper;
import ch.heigvd.protocol.commands.Accept;
import ch.heigvd.protocol.commands.Command;
import ch.heigvd.protocol.commands.Connect;
import ch.heigvd.protocol.commands.Decline;
import ch.heigvd.exceptions.ParseException;
import com.diogonunes.jcolor.Attribute;

import java.io.IOException;

import static com.diogonunes.jcolor.Ansi.colorize;

public class Login extends Instruction {

    private final String username;

    public Login(String username) {
        this.username = username;
    }

    @Override
    public void execute(ClientState state) throws IOException {
        state.sendCommand(new Connect(username));
        try {
            Command response = state.receiveCommand();
            if (response instanceof Decline decline) {
                ErrorHelper.showServerDecline(decline);
            } else if (response instanceof Accept) {
                Dialoguer.showSuccess("Logged in as " + colorize(username, Attribute.BRIGHT_WHITE_TEXT()) + colorize(" successfully!", Attribute.WHITE_TEXT()));
            } else {
                Dialoguer.showError("Unknown response received from the server! " + response.to());
            }
        } catch (ParseException e) {
            ErrorHelper.showParseError(e);
        }

    }
}
