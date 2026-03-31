import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Service class for processing data items.
 * This class handles the logic of transforming input data into processed results
 * with proper validation and error handling.
 */
public class DataProcessingService {
    
    private CalculationService calculationService;
    
    /**
     * Constructs a new DataProcessingService with the provided calculation service.
     * 
     * @param calculationService The calculation service to use for calculations
     */
    public DataProcessingService(CalculationService calculationService) {
        this.calculationService = calculationService;
    }
    
    /**
     * Processes a list of data items according to specified options.
     * 
     * @param data The list of data items to process
     * @param options The processing options (e.g., discount rate)
     * @return A list of processed items with calculated totals and categories
     * @throws IllegalArgumentException if data is null
     */
    public List<Map<String, Object>> processData(List<Map<String, Object>> data, Map<String, Object> options) {
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be null");
        }
        
        List<Map<String, Object>> result = new ArrayList<>();
        double discount = getDiscount(options);
        
        for (Map<String, Object> item : data) {
            try {
                Map<String, Object> processedItem = processSingleItem(item, discount);
                result.add(processedItem);
            } catch (Exception e) {
                // Log the error and continue processing other items
                System.err.println("Error processing item: " + item + ". Error: " + e.getMessage());
                // In a real application, you might want to log this properly
            }
        }
        
        return result;
    }
    
    /**
     * Processes a single data item.
     * 
     * @param item The item to process
     * @param discount The discount rate to apply
     * @return A processed item map with name, total and category
     * @throws IllegalArgumentException if required fields are missing or invalid
     */
    private Map<String, Object> processSingleItem(Map<String, Object> item, double discount) {
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null");
        }
        
        if (!item.containsKey("price") || !item.containsKey("quantity")) {
            throw new IllegalArgumentException("Item must contain 'price' and 'quantity'");
        }
        
        double price = ((Number) item.get("price")).doubleValue();
        int quantity = ((Number) item.get("quantity")).intValue();
        
        double total = calculationService.calculateTotal(price, quantity, discount);
        
        Map<String, Object> processedItem = new HashMap<>();
        processedItem.put("name", item.getOrDefault("name", "Unnamed"));
        processedItem.put("total", total);
        processedItem.put("category", calculationService.determineCategory(total));
        
        return processedItem;
    }
    
    /**
     * Extracts the discount rate from options.
     * 
     * @param options The processing options
     * @return The discount rate (0.0 if not specified or invalid)
     */
    private double getDiscount(Map<String, Object> options) {
        if (options == null || !options.containsKey("discount")) {
            return 0.0;
        }
        
        try {
            return ((Number) options.get("discount")).doubleValue();
        } catch (Exception e) {
            System.err.println("Invalid discount value: " + options.get("discount") + ". Using 0.0");
            return 0.0;
        }
    }
}