package ch.heigvd.protocol.commands;

import ch.heigvd.exceptions.ParseException;

import java.time.Instant;
import java.util.Arrays;

public class Message extends Command {
    private final String target;
    private final String sender;
    private final Instant timestamp;
    private final String message;

    public Message(String target, String sender, Instant timestamp, String message) {
        this.target = target;
        this.sender = sender;
        this.timestamp = timestamp;
        this.message = message;
    }


    public static Message from(String parameters) throws ParseException {
        // Get the rest, and throw an error if the command is invalid
        String[] split = parameters.split(" ");
        if (split.length < 5) {
            throw new ParseException();
        }

        try {
            return new Message(split[1], split[2], Instant.ofEpochSecond(Long.parseLong(split[3])),
                    String.join(" ", Arrays.copyOfRange(split, 4, split.length)));
        } catch (NumberFormatException e) {
            throw new ParseException();
        }
    }

    public String getTarget() {
        return target;
    }

    public String getSender() {
        return sender;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String to() {
        return "MSG " + target + " " + sender + " " + timestamp.getEpochSecond() + " " + message;
    }
}
