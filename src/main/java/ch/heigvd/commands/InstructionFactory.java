package ch.heigvd.commands;

import ch.heigvd.cli.instructions.Exit;
import ch.heigvd.cli.instructions.Instruction;
import ch.heigvd.cli.instructions.To;

public class InstructionFactory {

    public InstructionFactory() {}

    public Instruction parse(String line) {
        return switch(line.split(" ")[0]) {
            case "\\to" -> new To();
            case "\\exit" -> new Exit();
            default -> throw new RuntimeException("Invalid instruction");
        };
    }
}
