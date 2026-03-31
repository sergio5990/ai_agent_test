import java.util.*;

/**
 * Processes data items by calculating totals and categorizing them based on price.
 * This class separates concerns by delegating calculations and validation to other classes.
 */
public class DataProcessor {

    /**
     * Processes a list of data items by calculating totals and categorizing them.
     *
     * @param data The list of items to process
     * @param options Processing options (e.g., discount rate)
     * @return A list of ProcessedItem objects with calculated values
     * @throws InvalidItemException If any item is invalid
     */
    public List<ProcessedItem> processData(List<Map<String, Object>> data, Map<String, Object> options) throws InvalidItemException {
        if (data == null) {
            return new ArrayList<>();
        }

        if (options == null) {
            options = new HashMap<>();
        }

        List<ProcessedItem> result = new ArrayList<>();
        for (Map<String, Object> item : data) {
            if (item == null) {
                continue; // Skip null items
            }

            // Validate the item
            ItemValidator.validateItem(item);

            // Extract values safely
            double price = ItemValidator.extractPrice(item);
            int quantity = ItemValidator.extractQuantity(item);
            double discount = ItemValidator.extractDiscount(options);

            // Perform calculations
            double total = ItemCalculator.calculateTotal(price, quantity, discount);
            String category = ItemCalculator.categorizeItem(total);

            // Create processed item
            String name = (String) item.getOrDefault("name", "Unnamed");
            ProcessedItem processedItem = new ProcessedItem(name, total, category);
            result.add(processedItem);
        }
        return result;
    }

    public static void main(String[] args) {
        DataProcessor processor = new DataProcessor();
        List<Map<String, Object>> data = new ArrayList<>();

        Map<String, Object> item1 = new HashMap<>();
        item1.put("name", "Laptop");
        item1.put("price", 1000);
        item1.put("quantity", 1);
        data.add(item1);

        Map<String, Object> item2 = new HashMap<>();
        item2.put("name", "Mouse");
        item2.put("price", 25);
        item2.put("quantity", 2);
        data.add(item2);

        Map<String, Object> item3 = new HashMap<>();
        item3.put("price", 50);
        item3.put("quantity", 3);
        data.add(item3);

        Map<String, Object> item4 = new HashMap<>();
        item4.put("name", "Keyboard");
        item4.put("price", 80);
        item4.put("quantity", 1);
        data.add(item4);

        Map<String, Object> options = new HashMap<>();
        options.put("discount", 0.1);

        try {
            List<ProcessedItem> result = processor.processData(data, options);
            System.out.println(result);
        } catch (InvalidItemException e) {
            System.err.println("Error processing data: " + e.getMessage());
        }
    }
}