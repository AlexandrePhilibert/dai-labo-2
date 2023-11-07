package ch.heigvd.commands;

import ch.heigvd.exceptions.ParseException;

public class Accept extends Command {
    public static Accept from(String parameters) throws ParseException {
        return new Accept();
    }

    public String to() {
        return "ACCEPT";
    }
}
