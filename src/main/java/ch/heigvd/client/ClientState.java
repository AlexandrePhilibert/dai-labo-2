package ch.heigvd.client;

import ch.heigvd.protocol.commands.Command;
import ch.heigvd.exceptions.ParseException;
import ch.heigvd.protocol.CommandParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Writer;

public class ClientState {
    private String recipient;

    private final Writer writer;

    private final BufferedReader reader;

    public ClientState(Writer writer, BufferedReader reader) {
        this.writer = writer;
        this.reader = reader;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public void sendCommand(Command command) throws IOException {
        writer.write(command.to() + "\n");
        writer.flush();
    }

    public Command receiveCommand() throws IOException, ParseException {
        String command = reader.readLine();

        return CommandParser.parse(command);
    }


}
