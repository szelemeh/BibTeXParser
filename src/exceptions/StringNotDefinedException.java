package exceptions;

/**
 * Thrown to indicate that BibTeX string
 * used in concatenation is not yet defined.
 */
public class StringNotDefinedException extends Exception{
    public StringNotDefinedException(String message) {
        super(message);
    }
}
