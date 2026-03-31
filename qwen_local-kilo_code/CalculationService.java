import java.util.Map;

/**
 * Service class for handling calculation operations.
 * This class encapsulates all the logic related to calculating totals
 * and determining categories based on calculated values.
 */
public class CalculationService {
    
    /**
     * Calculates the total price for an item after applying discount.
     * 
     * @param price The unit price of the item
     * @param quantity The quantity of items
     * @param discount The discount rate to apply (0.0 to 1.0)
     * @return The calculated total price
     * @throws IllegalArgumentException if price or quantity is negative
     */
    public double calculateTotal(double price, int quantity, double discount) {
        if (price < 0 || quantity < 0) {
            throw new IllegalArgumentException("Price and quantity must be non-negative");
        }
        
        return price * quantity * (1 - discount);
    }
    
    /**
     * Determines the category of an item based on its total price.
     * 
     * @param total The calculated total price
     * @return "expensive" if total > 100, otherwise "cheap"
     */
    public String determineCategory(double total) {
        return total > 100 ? "expensive" : "cheap";
    }
}