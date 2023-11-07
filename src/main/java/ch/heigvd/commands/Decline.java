package ch.heigvd.commands;

import ch.heigvd.exceptions.ParseException;

public class Decline extends Command {
    private final String reason;

    public Decline(String reason) {
        this.reason = reason;
    }


    public static Decline from(String parameters) throws ParseException {
        // Get the rest, and throw an error if the command is invalid
        String[] split = parameters.split(" ");
        if (split.length != 2) {
            throw new ParseException();
        }

        return new Decline(split[1]);
    }

    public String to() {
        return "DECLINE " + reason;
    }
}
