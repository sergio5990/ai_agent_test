import java.util.Map;

/**
 * Utility class for validating item data.
 */
public class ItemValidator {

    /**
     * Validates that the required fields exist in the item map.
     *
     * @param item The item to validate
     * @throws InvalidItemException if required fields are missing
     */
    public static void validateItem(Map<String, Object> item) throws InvalidItemException {
        if (!item.containsKey("price")) {
            throw new InvalidItemException("Item is missing required 'price' field: " + item);
        }
        if (!item.containsKey("quantity")) {
            throw new InvalidItemException("Item is missing required 'quantity' field: " + item);
        }
    }

    /**
     * Extracts and validates the price from an item.
     *
     * @param item The item containing the price
     * @return The validated price as a double
     * @throws InvalidItemException if the price is invalid
     */
    public static double extractPrice(Map<String, Object> item) throws InvalidItemException {
        Object priceObj = item.get("price");
        if (!(priceObj instanceof Number)) {
            throw new InvalidItemException("Invalid price value in item: " + item + ". Price must be a number.");
        }
        return ((Number) priceObj).doubleValue();
    }

    /**
     * Extracts and validates the quantity from an item.
     *
     * @param item The item containing the quantity
     * @return The validated quantity as an integer
     * @throws InvalidItemException if the quantity is invalid
     */
    public static int extractQuantity(Map<String, Object> item) throws InvalidItemException {
        Object quantityObj = item.get("quantity");
        if (!(quantityObj instanceof Number)) {
            throw new InvalidItemException("Invalid quantity value in item: " + item + ". Quantity must be a number.");
        }
        return ((Number) quantityObj).intValue();
    }

    /**
     * Extracts the discount from options, returning a default value if not present.
     *
     * @param options The options map containing the discount
     * @return The discount value or 0.0 if not present
     * @throws InvalidItemException if the discount value is invalid
     */
    public static double extractDiscount(Map<String, Object> options) throws InvalidItemException {
        if (options == null || !options.containsKey("discount")) {
            return 0.0;
        }

        Object discountObj = options.get("discount");
        if (!(discountObj instanceof Number)) {
            throw new InvalidItemException("Invalid discount value in options: " + options + ". Discount must be a number.");
        }
        return ((Number) discountObj).doubleValue();
    }
}