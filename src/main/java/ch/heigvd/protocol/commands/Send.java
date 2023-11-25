package ch.heigvd.protocol.commands;

import ch.heigvd.exceptions.ParseException;

import java.util.Arrays;

public class Send extends Command {
    private final String group;
    private final String message;

    public Send(String group, String message) {
        this.group = group;
        this.message = message;
    }

    public static Send from(String input) throws ParseException {
        String[] splitted = input.split(" ");
        if (splitted.length < 3) {
            throw new ParseException();
        }

        return new Send(splitted[1], String.join(" ",
                Arrays.copyOfRange(splitted, 2, splitted.length)));
    }

    public String getGroup() {
        return group;
    }

    public String getMessage() {
        return message;
    }

    public String to() {
        return "SEND " + group + " " + message;
    }
}
