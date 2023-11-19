package ch.heigvd.cli.instructions;

public class InstructionFactory {

    public InstructionFactory() {}

    public Instruction parse(String line) {
        String[] l = line.split(" ");
        String instruction = l[0];

        return switch(instruction) {
            case "\\to" -> new To(l[1]);
            case "\\exit" -> new Exit();
            default -> new Message(line);
        };
    }
}
