import java.util.*;

/**
 * Main data processor class that orchestrates the processing of input data items.
 * This class delegates calculation logic to ItemProcessor and uses ProcessedItem
 * for type-safe result representation. It handles data validation and transformation.
 */
public class DataProcessor {

    private final ItemProcessor itemProcessor;

    /**
     * Constructs a new DataProcessor with its dependencies.
     */
    public DataProcessor() {
        this.itemProcessor = new ItemProcessor();
    }

    /**
     * Processes a list of data items with the given options.
     * Each item should contain price and quantity fields to be processed.
     *
     * @param data the list of data items to process
     * @param options processing options, may include "discount" key
     * @return a list of processed items with name, total, and category
     * @throws DataProcessingException if an item has invalid data
     * @throws IllegalArgumentException if options contains invalid discount
     */
    public List<ProcessedItem> processData(List<Map<String, Object>> data, Map<String, Object> options) {
        if (data == null) {
            throw new IllegalArgumentException("Data list cannot be null");
        }
        
        if (options == null) {
            options = new HashMap<>();
        }

        double discount = extractDiscount(options);
        List<ProcessedItem> result = new ArrayList<>();

        for (Map<String, Object> item : data) {
            ProcessedItem processedItem = processItem(item, discount);
            result.add(processedItem);
        }

        return result;
    }

    /**
     * Extracts and validates the discount value from options map.
     *
     * @param options the options map containing potential discount
     * @return the discount value, or 0.0 if not specified
     * @throws DataProcessingException if discount value is invalid
     */
    private double extractDiscount(Map<String, Object> options) {
        if (!options.containsKey("discount")) {
            return 0.0;
        }

        Object discountValue = options.get("discount");
        try {
            double discount = ((Number) discountValue).doubleValue();
            if (discount < 0.0 || discount > 1.0) {
                throw new DataProcessingException(
                    "Discount must be between 0.0 and 1.0: " + discount);
            }
            return discount;
        } catch (ClassCastException e) {
            throw new DataProcessingException(
                "Invalid discount value type: " + discountValue, e);
        }
    }

    /**
     * Processes a single data item and returns a ProcessedItem.
     *
     * @param item the data item to process
     * @param discount the discount rate to apply
     * @return the processed item
     * @throws DataProcessingException if required fields are missing or invalid
     */
    private ProcessedItem processItem(Map<String, Object> item, double discount) {
        validateRequiredFields(item);

        String name = extractName(item);
        double price = extractPrice(item);
        int quantity = extractQuantity(item);

        double total = itemProcessor.calculateTotal(price, quantity, discount);
        ProcessedItem.Category category = ProcessedItem.determineCategory(total);

        return new ProcessedItem(name, total, category);
    }

    /**
     * Validates that the item contains required fields.
     *
     * @param item the item to validate
     * @throws DataProcessingException if price or quantity is missing
     */
    private void validateRequiredFields(Map<String, Object> item) {
        if (!item.containsKey("price")) {
            throw new DataProcessingException(
                "Missing required field 'price' in item: " + item);
        }
        if (!item.containsKey("quantity")) {
            throw new DataProcessingException(
                "Missing required field 'quantity' in item: " + item);
        }
    }

    /**
     * Extracts the name from an item, defaulting to "Unnamed" if not present.
     *
     * @param item the item to extract name from
     * @return the item name or "Unnamed" if not specified
     */
    private String extractName(Map<String, Object> item) {
        Object name = item.getOrDefault("name", "Unnamed");
        return name != null ? name.toString() : "Unnamed";
    }

    /**
     * Extracts and validates the price from an item.
     *
     * @param item the item to extract price from
     * @return the price value
     * @throws DataProcessingException if price is not a valid number
     */
    private double extractPrice(Map<String, Object> item) {
        Object priceValue = item.get("price");
        try {
            return ((Number) priceValue).doubleValue();
        } catch (ClassCastException e) {
            throw new DataProcessingException(
                "Invalid price value type: " + priceValue, e);
        }
    }

    /**
     * Extracts and validates the quantity from an item.
     *
     * @param item the item to extract quantity from
     * @return the quantity value
     * @throws DataProcessingException if quantity is not a valid number
     */
    private int extractQuantity(Map<String, Object> item) {
        Object quantityValue = item.get("quantity");
        try {
            return ((Number) quantityValue).intValue();
        } catch (ClassCastException e) {
            throw new DataProcessingException(
                "Invalid quantity value type: " + quantityValue, e);
        }
    }

    /**
     * Main method demonstrating the usage of the refactored DataProcessor.
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        DataProcessor processor = new DataProcessor();
        List<Map<String, Object>> data = new ArrayList<>();

        // Item 1: Laptop
        Map<String, Object> item1 = new HashMap<>();
        item1.put("name", "Laptop");
        item1.put("price", 1000);
        item1.put("quantity", 1);
        data.add(item1);

        // Item 2: Mouse
        Map<String, Object> item2 = new HashMap<>();
        item2.put("name", "Mouse");
        item2.put("price", 25);
        item2.put("quantity", 2);
        data.add(item2);

        // Item 3: Unnamed item (tests default name)
        Map<String, Object> item3 = new HashMap<>();
        item3.put("price", 50);
        item3.put("quantity", 3);
        data.add(item3);

        // Item 4: Keyboard
        Map<String, Object> item4 = new HashMap<>();
        item4.put("name", "Keyboard");
        item4.put("price", 80);
        item4.put("quantity", 1);
        data.add(item4);

        Map<String, Object> options = new HashMap<>();
        options.put("discount", 0.1);

        try {
            List<ProcessedItem> result = processor.processData(data, options);
            System.out.println("Processed " + result.size() + " items:");
            for (ProcessedItem item : result) {
                System.out.println(item);
            }
        } catch (DataProcessingException e) {
            System.err.println("Error processing data: " + e.getMessage());
        }
    }
}
