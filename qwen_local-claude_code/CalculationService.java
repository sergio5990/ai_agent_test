import java.util.Map;

/**
 * Service responsible for performing calculations on data items.
 * This includes price calculations, discount application, and total computations.
 */
public class CalculationService {

    /**
     * Calculates the total price for an item after applying any applicable discounts.
     *
     * @param item The data item containing price and quantity information
     * @param options Processing options that may contain discount information
     * @return The calculated total price for the item
     * @throws CalculationException if there are issues with numeric conversions or calculations
     */
    public double calculateTotal(Map<String, Object> item, Map<String, Object> options) throws CalculationException {
        if (item == null) {
            throw new CalculationException("Item cannot be null");
        }

        if (!item.containsKey("price") || !item.containsKey("quantity")) {
            throw new CalculationException("Item must contain 'price' and 'quantity' fields");
        }

        try {
            double price = ((Number) item.get("price")).doubleValue();
            int quantity = ((Number) item.get("quantity")).intValue();

            if (price < 0 || quantity < 0) {
                throw new CalculationException("Price and quantity must be non-negative");
            }

            double discount = 0.0;
            if (options != null && options.containsKey("discount")) {
                discount = ((Number) options.get("discount")).doubleValue();
                if (discount < 0 || discount > 1) {
                    throw new CalculationException("Discount must be between 0 and 1");
                }
            }

            return price * quantity * (1 - discount);
        } catch (ClassCastException e) {
            throw new CalculationException("Invalid data types for price or quantity: " + item, e);
        } catch (NumberFormatException e) {
            throw new CalculationException("Invalid numeric values in item: " + item, e);
        }
    }

    /**
     * Determines if an amount is considered expensive based on the threshold.
     *
     * @param total The calculated total to categorize
     * @return true if the total is greater than 100, false otherwise
     */
    public boolean isExpensive(double total) {
        return total > 100;
    }
}