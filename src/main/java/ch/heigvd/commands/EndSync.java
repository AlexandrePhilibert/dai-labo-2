package ch.heigvd.commands;

import ch.heigvd.exceptions.ParseException;

import java.time.Instant;

public class EndSync extends Command {
    public static EndSync from(String parameters) throws ParseException {
        return new EndSync();
    }

    public String to() {
        return "ENDSYNC";
    }
}
