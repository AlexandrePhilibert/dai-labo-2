package ch.heigvd.server;

import ch.heigvd.commands.Message;
import ch.heigvd.commands.Send;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This clas is a representation of a group, with all messages within held.
 */
public class Group {
    private final List<Message> messages;
    private final String name;

    public Group(String name) {
        this.name = name;
        // In order to avoid synchronized blocks everywhere, use a synchronized list to ensure that
        // no race / concurrent modification errors happen.
        messages = Collections.synchronizedList(new ArrayList<>());
    }

    /**
     * Posts a new message to a given channel.
     *
     * @param sender   The name of the sender
     *                               TODO: Create a thin wrapper to make sure we don't put something unintended in this parameter.
     * @param contents The contents of the message.
     */
    public void post(String sender, String contents) {
        Message message = new Message(name, sender, Instant.now(), contents);
        messages.add(message);
    }

    public List<Message> getAfter(Instant notBefore) {
        // TODO: Find a more optimized solution (maybe using a bucket-based solution)
        //       But for now, this is more than enough
        return messages.stream()
                .filter(e -> e.getTimestamp().isAfter(notBefore))
                .toList();
    }
}
