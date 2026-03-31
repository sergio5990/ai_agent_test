/**
 * Custom exception class for calculation errors.
 * This exception is thrown when issues occur during mathematical calculations,
 * such as invalid numeric values or invalid operations.
 */
public class CalculationException extends Exception {

    /**
     * Constructs a new CalculationException with the specified detail message.
     *
     * @param message The detail message
     */
    public CalculationException(String message) {
        super(message);
    }

    /**
     * Constructs a new CalculationException with the specified detail message and cause.
     *
     * @param message The detail message
     * @param cause The cause (which is saved for later retrieval by the Throwable.getCause() method)
     */
    public CalculationException(String message, Throwable cause) {
        super(message, cause);
    }
}