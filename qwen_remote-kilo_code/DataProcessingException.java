/** 
 * Custom exception class for data processing errors.
 * This exception is thrown when there are issues processing data items.
 */
public class DataProcessingException extends Exception {
    
    /**
     * Constructs a new DataProcessingException with the specified detail message.
     * 
     * @param message the detail message
     */
    public DataProcessingException(String message) {
        super(message);
    }
    
    /**
     * Constructs a new DataProcessingException with the specified detail message and cause.
     * 
     * @param message the detail message
     * @param cause the cause of the exception
     */
    public DataProcessingException(String message, Throwable cause) {
        super(message, cause);
    }
}
