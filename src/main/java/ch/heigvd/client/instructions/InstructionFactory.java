package ch.heigvd.client.instructions;

import java.util.List;

public class InstructionFactory {

    public InstructionFactory() {}

    public List<Instruction> parse(String line) {
        String[] l = line.split(" ");
        String instruction = l[0];

        return switch(instruction) {
            case "\\to" -> List.of(new To(l[1]));
            case "\\get" -> List.of(new Sync(l[1]));
            case "\\exit" -> List.of(new Exit());
            default -> List.of(new Message(line));
        };
    }
}
