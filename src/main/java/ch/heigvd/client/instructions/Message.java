package ch.heigvd.client.instructions;

import ch.heigvd.client.ClientState;
import ch.heigvd.client.tui.Dialoguer;
import ch.heigvd.client.tui.ErrorHelper;
import ch.heigvd.protocol.commands.Acknowledge;
import ch.heigvd.protocol.commands.Command;
import ch.heigvd.protocol.commands.Decline;
import ch.heigvd.protocol.commands.Send;
import ch.heigvd.exceptions.ParseException;
import com.diogonunes.jcolor.Attribute;

import java.io.IOException;

import static com.diogonunes.jcolor.Ansi.colorize;

public class Message extends Instruction {
    private final String message;

    public Message(String message) {
        this.message = message;
    }

    @Override
    public void execute(ClientState state) throws IOException, RuntimeException {
        if (state.getRecipient() == null) {
            Dialoguer.showError("No recipient is set!");
            Dialoguer.showInfo("You can use the " + colorize("\\to", Attribute.BRIGHT_WHITE_TEXT()) + colorize(" command to select a recipient", Attribute.WHITE_TEXT()));
            return;
        }

        state.sendCommand(new Send(state.getRecipient(), message));
        try {
            Command response = state.receiveCommand();
            if (!(response instanceof Acknowledge)) {
                if (response instanceof Decline declined) {
                    ErrorHelper.showServerDecline(declined);
                } else {
                    Dialoguer.showError("Unexpected server answer: " + response.to());
                }
            }
        } catch (ParseException e) {
            ErrorHelper.showParseError(e);
        }
    }
}
