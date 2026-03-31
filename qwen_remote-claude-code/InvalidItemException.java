/**
 * Exception thrown when an item is invalid or missing required fields.
 */
public class InvalidItemException extends Exception {

    /**
     * Constructs a new InvalidItemException with the specified detail message.
     *
     * @param message The detail message
     */
    public InvalidItemException(String message) {
        super(message);
    }

    /**
     * Constructs a new InvalidItemException with the specified detail message and cause.
     *
     * @param message The detail message
     * @param cause The cause of the exception
     */
    public InvalidItemException(String message, Throwable cause) {
        super(message, cause);
    }
}