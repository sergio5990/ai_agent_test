import java.util.Map;

/**
 * Custom exception class for data processing errors.
 * This exception is thrown when issues occur during the data processing workflow,
 * such as invalid input data or calculation problems.
 */
public class DataProcessingException extends Exception {

    /**
     * Constructs a new DataProcessingException with the specified detail message.
     *
     * @param message The detail message
     */
    public DataProcessingException(String message) {
        super(message);
    }

    /**
     * Constructs a new DataProcessingException with the specified detail message and cause.
     *
     * @param message The detail message
     * @param cause The cause (which is saved for later retrieval by the Throwable.getCause() method)
     */
    public DataProcessingException(String message, Throwable cause) {
        super(message, cause);
    }
}