package ch.heigvd.client;

import java.io.Writer;

public class ClientState {
    private String recipient;

    private final Writer writer;

    public ClientState(Writer writer) {
        this.writer = writer;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public Writer getWriter() {
        return writer;
    }
}
