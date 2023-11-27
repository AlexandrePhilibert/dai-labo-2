package ch.heigvd.client.instructions;

import ch.heigvd.client.ClientState;
import ch.heigvd.client.tui.Dialoguer;
import ch.heigvd.client.tui.ErrorHelper;
import ch.heigvd.exceptions.ParseException;
import ch.heigvd.protocol.commands.Command;
import ch.heigvd.protocol.commands.Decline;
import ch.heigvd.protocol.commands.EndSync;
import ch.heigvd.protocol.commands.Message;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

public class Sync extends Instruction {
    private static final SimpleDateFormat FORMATTER = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
    String group;

    public Sync(String group) {
        this.group = group;
    }

    @Override
    public void execute(ClientState state) throws IOException {
        if (group == null) {
            return;
        }



        state.sendCommand(new ch.heigvd.protocol.commands.Sync(group, state.getLastSync(group)));

        Command response;
        try {
            do  {
                response = state.receiveCommand();

                if (response instanceof Decline d) {
                    ErrorHelper.showServerDecline(d);
                    return;
                }

                if (response instanceof Message m) {
                    System.out.println("[" + FORMATTER.format(Date.from(m.getTimestamp())) + "] " + m.getSender() + ": " + m.getMessage());
                }
            } while (!(response instanceof EndSync));

            state.setLastSync(group, Instant.now());
        } catch (ParseException e) {
            ErrorHelper.showParseError(e);
        }
    }
}
