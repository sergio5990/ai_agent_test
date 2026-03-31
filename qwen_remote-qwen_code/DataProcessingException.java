/**
 * Exception thrown when data processing encounters invalid or missing data.
 * This runtime exception provides specific information about what went wrong
 * during the processing of input data items.
 */
public class DataProcessingException extends RuntimeException {

    /**
     * Constructs a new DataProcessingException with the specified detail message.
     *
     * @param message the detail message explaining the cause of the exception
     */
    public DataProcessingException(String message) {
        super(message);
    }

    /**
     * Constructs a new DataProcessingException with the specified detail message
     * and cause.
     *
     * @param message the detail message explaining the cause of the exception
     * @param cause the underlying cause of this exception
     */
    public DataProcessingException(String message, Throwable cause) {
        super(message, cause);
    }
}
