import java.util.*;

/**
 * Main processor class that orchestrates data processing operations.
 * This class now acts as a coordinator, delegating specific tasks to 
 * specialized services for better separation of concerns.
 */
public class DataProcessor {
    
    private DataProcessingService dataProcessingService;
    
    /**
     * Constructs a new DataProcessor with the default data processing service.
     */
    public DataProcessor() {
        this.dataProcessingService = new DataProcessingService(new CalculationService());
    }
    
    /**
     * Processes a list of data items according to specified options.
     * 
     * @param data The list of data items to process
     * @param options The processing options (e.g., discount rate)
     * @return A list of processed items with calculated totals and categories
     * @throws IllegalArgumentException if data is null or contains invalid items
     */
    public List<Map<String, Object>> processData(List<Map<String, Object>> data, Map<String, Object> options) {
        return dataProcessingService.processData(data, options);
    }

    public static void main(String[] args) {
        try {
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
            
            List<Map<String, Object>> result = processor.processData(data, options);
            System.out.println(result);
        } catch (Exception e) {
            // Proper error handling instead of console printing
            System.err.println("Error in data processing: " + e.getMessage());
            e.printStackTrace();
        }
    }
}