import java.util.*;

/**
 * Main data processor class that orchestrates the processing of data items.
 * This class delegates specific responsibilities to specialized classes.
 */
public class DataProcessor {
    
    private TotalCalculator calculator;
    private CategoryClassifier classifier;
    
    /**
     * Constructor that initializes the required components for data processing.
     */
    public DataProcessor() {
        this.calculator = new TotalCalculator();
        this.classifier = new CategoryClassifier();
    }
    
    /**
     * Processes a list of data items according to the provided options.
     * 
     * @param data the list of data items to process
     * @param options the processing options (e.g., discount rate)
     * @return the list of processed data items
     * @throws DataProcessingException if there are issues processing the data
     */
    public List<Map<String, Object>> processData(List<Map<String, Object>> data, Map<String, Object> options) throws DataProcessingException {
        if (data == null) {
            throw new DataProcessingException("Input data cannot be null");
        }
        
        if (options == null) {
            options = new HashMap<>();
        }
        
        List<Map<String, Object>> result = new ArrayList<>();
        
        for (int i = 0; i < data.size(); i++) {
            Map<String, Object> item = data.get(i);
            try {
                Map<String, Object> processedItem = processSingleItem(item, options);
                if (processedItem != null) {
                    result.add(processedItem);
                }
            } catch (IllegalArgumentException e) {
                throw new DataProcessingException(
                    String.format("Error processing item at index %d (%s): %s", i, item.toString(), e.getMessage()), e);
            }
        }
        
        return result;
    }
    
    /**
     * Processes a single data item according to the provided options.
     * 
     * @param item the data item to process
     * @param options the processing options (e.g., discount rate)
     * @return the processed data item, or null if the item should be skipped
     * @throws IllegalArgumentException if there are issues processing the item
     */
    private Map<String, Object> processSingleItem(Map<String, Object> item, Map<String, Object> options) throws IllegalArgumentException {
        if (!item.containsKey("price") || !item.containsKey("quantity")) {
            // Instead of printing to console, we could log this or handle differently
            // For now, we'll skip items that don't have required fields
            return null;
        }
        
        double discount = getDiscountFromOptions(options);
        double total = calculator.calculateTotal(item, discount);
        
        Map<String, Object> processedItem = new HashMap<>();
        processedItem.put("name", item.getOrDefault("name", "Unnamed"));
        processedItem.put("total", total);
        classifier.addCategoryToItem(processedItem, total);
        
        return processedItem;
    }
    
    /**
     * Extracts the discount value from the options map.
     * 
     * @param options the options map
     * @return the discount value, defaulting to 0.0 if not specified
     */
    private double getDiscountFromOptions(Map<String, Object> options) {
        if (options.containsKey("discount")) {
            Object discountObj = options.get("discount");
            if (discountObj instanceof Number) {
                return ((Number) discountObj).doubleValue();
            } else if (discountObj instanceof String) {
                try {
                    return Double.parseDouble((String) discountObj);
                } catch (NumberFormatException e) {
                    return 0.0; // Default to no discount if parsing fails
                }
            }
        }
        return 0.0; // Default to no discount
    }
    
    /**
     * Main method for testing the refactored data processor.
     */
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
            List<Map<String, Object>> result = processor.processData(data, options);
            System.out.println(result);
        } catch (DataProcessingException e) {
            System.err.println("Error processing data: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
