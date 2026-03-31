import java.util.Map;

/**
 * Calculator class responsible for performing financial calculations on data items.
 * This class handles the computation of totals based on price, quantity, and discount.
 */
public class TotalCalculator {
    
    /**
     * Calculates the total amount for a given item based on price, quantity, and discount.
     * 
     * @param price the unit price of the item
     * @param quantity the quantity of the item
     * @param discount the discount rate (between 0 and 1)
     * @return the calculated total amount
     * @throws IllegalArgumentException if price or quantity are invalid
     */
    public double calculateTotal(double price, int quantity, double discount) throws IllegalArgumentException {
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative: " + price);
        }
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative: " + quantity);
        }
        if (discount < 0 || discount > 1) {
            throw new IllegalArgumentException("Discount must be between 0 and 1: " + discount);
        }
        
        return price * quantity * (1 - discount);
    }
    
    /**
     * Calculates the total amount for an item map containing price and quantity.
     * 
     * @param item the data item containing price and quantity information
     * @param discount the discount rate to apply
     * @return the calculated total amount
     * @throws IllegalArgumentException if the item doesn't contain required fields or values are invalid
     */
    public double calculateTotal(Map<String, Object> item, double discount) throws IllegalArgumentException {
        if (!item.containsKey("price")) {
            throw new IllegalArgumentException("Item must contain 'price' field");
        }
        if (!item.containsKey("quantity")) {
            throw new IllegalArgumentException("Item must contain 'quantity' field");
        }
        
        double price = getNumericValueAsDouble(item.get("price"));
        int quantity = getNumericValueAsInt(item.get("quantity"));
        
        return calculateTotal(price, quantity, discount);
    }
    
    /**
     * Safely extracts a numeric value as a double from an object.
     * 
     * @param obj the object to convert
     * @return the numeric value as a double
     * @throws IllegalArgumentException if the object is not numeric
     */
    private double getNumericValueAsDouble(Object obj) throws IllegalArgumentException {
        if (obj instanceof Number) {
            return ((Number) obj).doubleValue();
        } else if (obj instanceof String) {
            try {
                return Double.parseDouble((String) obj);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Cannot convert string to number: " + obj, e);
            }
        } else {
            throw new IllegalArgumentException("Object is not numeric: " + obj);
        }
    }
    
    /**
     * Safely extracts a numeric value as an int from an object.
     * 
     * @param obj the object to convert
     * @return the numeric value as an int
     * @throws IllegalArgumentException if the object is not numeric
     */
    private int getNumericValueAsInt(Object obj) throws IllegalArgumentException {
        if (obj instanceof Number) {
            return ((Number) obj).intValue();
        } else if (obj instanceof String) {
            try {
                return Integer.parseInt((String) obj);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Cannot convert string to integer: " + obj, e);
            }
        } else {
            throw new IllegalArgumentException("Object is not numeric: " + obj);
        }
    }
}
