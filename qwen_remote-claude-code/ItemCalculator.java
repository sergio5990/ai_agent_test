/**
 * Utility class for performing calculations on item data.
 */
public class ItemCalculator {

    /**
     * Calculates the total value for an item based on price, quantity, and discount.
     *
     * @param price The price of the item
     * @param quantity The quantity of the item
     * @param discount The discount to apply (as a decimal, e.g., 0.1 for 10%)
     * @return The calculated total value
     */
    public static double calculateTotal(double price, int quantity, double discount) {
        return price * quantity * (1 - discount);
    }

    /**
     * Determines the category of an item based on its total value.
     * Items with total > 100 are categorized as "expensive", otherwise "cheap".
     *
     * @param total The total value of the item
     * @return The category ("expensive" or "cheap")
     */
    public static String categorizeItem(double total) {
        return total > 100 ? "expensive" : "cheap";
    }
}