package ch.heigvd.commands;

import ch.heigvd.commands.enums.DeclineReason;
import ch.heigvd.exceptions.ParseException;

public class Decline extends Command {
    private final DeclineReason reason;
    private final String customDeclineReason;

    public Decline(DeclineReason reason) {
        this.reason = reason;
        this.customDeclineReason = null;
    }

    public Decline(String customDeclineReason) {
        this.reason = DeclineReason.CUSTOM;
        this.customDeclineReason = customDeclineReason;
    }


    public static Decline from(String parameters) throws ParseException {
        // Get the rest, and throw an error if the command is invalid
        String[] split = parameters.split(" ");
        if (split.length != 2) {
            throw new ParseException();
        }
        DeclineReason reason = DeclineReason.from(split[1]);
        if (reason != DeclineReason.CUSTOM) {
            return new Decline(reason);
        } else {
            return new Decline(split[1]);
        }
    }

    public String to() {
        if (reason != DeclineReason.CUSTOM) {
            return "DECLINE " + reason.getCode();
        } else {
            return "DECLINE " + customDeclineReason;
        }
    }
}
