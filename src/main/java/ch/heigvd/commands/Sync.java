package ch.heigvd.commands;

import ch.heigvd.exceptions.ParseException;

import java.time.Instant;

public class Sync extends Command {
    private final String target;
    private final Instant instant;

    public Sync(String target, Instant instant) {
        this.target = target;
        this.instant = instant;
    }


    public static Sync from(String parameters) throws ParseException {
        // Get the rest, and throw an error if the command is invalid
        String[] split = parameters.split(" ");
        if (split.length != 3) {
            throw new ParseException();
        }

        try {
            return new Sync(split[1], Instant.ofEpochSecond(Long.parseLong(split[2])));
        } catch (NumberFormatException e) {
            // The second parameter is not a number!
            throw new ParseException();
        }

    }

    public String to() {
        return "SYNC " + target + " " + instant.getEpochSecond();
    }
}
