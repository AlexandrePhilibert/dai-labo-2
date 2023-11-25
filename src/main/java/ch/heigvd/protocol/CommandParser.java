package ch.heigvd.protocol;

import ch.heigvd.protocol.commands.*;
import ch.heigvd.exceptions.ParseException;

public class CommandParser {
    public static Command parse(String line) throws ParseException {
        // Get the start
        String command = line.split(" ")[0];
        return switch (command) {
            case "CONNECT" -> Connect.from(line);
            case "ACCEPT" -> Accept.from(line);
            case "DECLINE" -> Decline.from(line);
            case "SEND" -> Send.from(line);
            case "ACK" -> Acknowledge.from(line);
            case "SYNC" -> Sync.from(line);
            case "MSG" -> Message.from(line);
            case "ENDSYNC" -> EndSync.from(line);

            default -> throw new ParseException("Unknown command!");
        };
    }
}
