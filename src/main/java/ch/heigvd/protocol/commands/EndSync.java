package ch.heigvd.protocol.commands;

import ch.heigvd.exceptions.ParseException;

public class EndSync extends Command {
    public static EndSync from(String parameters) throws ParseException {
        return new EndSync();
    }

    public String to() {
        return "ENDSYNC";
    }
}
