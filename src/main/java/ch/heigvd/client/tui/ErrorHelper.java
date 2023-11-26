package ch.heigvd.client.tui;

import ch.heigvd.protocol.commands.Decline;
import ch.heigvd.exceptions.ParseException;

public class ErrorHelper {
    public static void showServerDecline(Decline declined) {
        Dialoguer.showError("Server declined command: " + declined.getMessage());
    }

    public static void showParseError(ParseException exception) {
        Dialoguer.showError("Invalid response from server: " + exception.getMessage());
        Dialoguer.showInfo("Is the client up to date?");
    }
}
