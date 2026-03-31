/**
 * Handles calculation logic for processing items, including total calculation
 * and discount application. This class is responsible for all numerical
 * computations related to item processing.
 */
public class ItemProcessor {

    /**
     * Calculates the total value for an item given its price, quantity, and discount.
     * Formula: price * quantity * (1 - discount)
     *
     * @param price the unit price of the item
     * @param quantity the quantity of items
     * @param discount the discount rate (0.0 to 1.0)
     * @return the calculated total after applying discount
     * @throws IllegalArgumentException if price or quantity is negative,
     *         or if discount is outside the valid range [0.0, 1.0]
     */
    public double calculateTotal(double price, int quantity, double discount) {
        validatePrice(price);
        validateQuantity(quantity);
        validateDiscount(discount);
        
        return price * quantity * (1 - discount);
    }

    /**
     * Validates that the price is non-negative.
     *
     * @param price the price to validate
     * @throws IllegalArgumentException if price is negative
     */
    private void validatePrice(double price) {
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative: " + price);
        }
    }

    /**
     * Validates that the quantity is non-negative.
     *
     * @param quantity the quantity to validate
     * @throws IllegalArgumentException if quantity is negative
     */
    private void validateQuantity(int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative: " + quantity);
        }
    }

    /**
     * Validates that the discount is within the valid range [0.0, 1.0].
     *
     * @param discount the discount to validate
     * @throws IllegalArgumentException if discount is outside the valid range
     */
    private void validateDiscount(double discount) {
        if (discount < 0.0 || discount > 1.0) {
            throw new IllegalArgumentException(
                "Discount must be between 0.0 and 1.0: " + discount);
        }
    }
}
