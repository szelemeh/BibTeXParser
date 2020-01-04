package Exceptions;

/**
 * Thrown to indicate that a parsing error occurred
 */
public class ParsingException extends Exception{
    public ParsingException(String message) {
        super(message);
    }
}
