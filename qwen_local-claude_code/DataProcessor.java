import java.util.*;

/**
 * Main data processor class responsible for orchestrating the data processing workflow.
 * This class handles input validation, processes data through various components,
 * and returns structured results.
 */
public class DataProcessor {

    private final CalculationService calculationService;
    private final DataTransformer dataTransformer;

    /**
     * Constructs a new DataProcessor with default services.
     */
    public DataProcessor() {
        this.calculationService = new CalculationService();
        this.dataTransformer = new DataTransformer();
    }

    /**
     * Processes a list of data items with optional processing options.
     *
     * @param data The input data to process
     * @param options Optional processing configuration
     * @return List of processed data items with calculated totals and categories
     * @throws DataProcessingException if the processing fails due to invalid data or other issues
     */
    public List<Map<String, Object>> processData(List<Map<String, Object>> data, Map<String, Object> options) throws DataProcessingException {
        if (data == null) {
            throw new DataProcessingException("Input data cannot be null");
        }

        if (options == null) {
            options = new HashMap<>();
        }

        List<Map<String, Object>> result = new ArrayList<>();

        for (Map<String, Object> item : data) {
            try {
                if (item.containsKey("price") && item.containsKey("quantity")) {
                    // Perform calculation
                    double total = calculationService.calculateTotal(item, options);

                    // Transform data to result format
                    Map<String, Object> processedItem = dataTransformer.transform(item, total);
                    result.add(processedItem);
                } else {
                    // Use the calculation service to handle missing fields properly
                    throw new DataProcessingException("Skipping item due to missing required fields: " + item);
                }
            } catch (CalculationException e) {
                throw new DataProcessingException("Error processing item: " + item, e);
            }
        }

        return result;
    }

    /**
     * Main method for running the data processor demo.
     *
     * @param args Command line arguments (not used)
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
        }
    }
}