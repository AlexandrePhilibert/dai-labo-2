package ch.heigvd.commands;

import ch.heigvd.exceptions.ParseException;

public class Acknowledge extends Command {
    public static Acknowledge from(String parameters) throws ParseException {
        // Get the rest, and throw an error if the command is invalid
        String[] split = parameters.split(" ");
        if (split.length != 1) {
            throw new ParseException();
        }

        return new Acknowledge();
    }

    public String to() {
        return "ACK";
    }
}
