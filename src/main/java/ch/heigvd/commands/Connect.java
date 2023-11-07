package ch.heigvd.commands;

import ch.heigvd.exceptions.ParseException;

public class Connect extends Command {
    private final String username;

    public Connect(String username) {
        this.username = username;
    }

    public static Connect from(String parameters) throws ParseException {
        // Get the rest, and throw an error if the command is invalid
        String[] split = parameters.split(" ");
        if (split.length != 2) {
            throw new ParseException();
        }

        return new Connect(split[1]);
    }

    public String to() {
        return "CONNECT " + username;
    }
}
