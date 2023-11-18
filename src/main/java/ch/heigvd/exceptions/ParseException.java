package ch.heigvd.exceptions;

public class ParseException extends Exception {
    public ParseException() {

    }
    public ParseException(String contents) {
        super(contents);
    }
}
