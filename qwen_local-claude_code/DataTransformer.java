import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Service responsible for transforming raw data items into the final processed format.
 * This includes setting default values and categorizing items based on calculated totals.
 */
public class DataTransformer {

    /**
     * Transforms a raw data item into the final processed format with calculated total and category.
     *
     * @param item The raw data item to transform
     * @param total The calculated total for the item
     * @return A new map containing the transformed data with name, total, and category fields
     */
    public Map<String, Object> transform(Map<String, Object> item, double total) {
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null");
        }

        Map<String, Object> processedItem = new HashMap<>();

        // Set name with default value if not present
        processedItem.put("name", item.getOrDefault("name", "Unnamed"));

        // Set calculated total
        processedItem.put("total", total);

        // Determine and set category based on total amount
        processedItem.put("category", determineCategory(total));

        return processedItem;
    }

    /**
     * Determines the category of an item based on its total value.
     *
     * @param total The calculated total amount
     * @return "expensive" if the total is greater than 100, "cheap" otherwise
     */
    private String determineCategory(double total) {
        return total > 100 ? "expensive" : "cheap";
    }
}