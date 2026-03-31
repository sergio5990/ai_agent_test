import java.util.*;

public class DataProcessor {
    public List<Map<String, Object>> processData(List<Map<String, Object>> data, Map<String, Object> options) {
        if (options == null) {
            options = new HashMap<>();
        }
        List<Map<String, Object>> result = new ArrayList<>();
        for (Map<String, Object> item : data) {
            if (item.containsKey("price") && item.containsKey("quantity")) {
                double price = ((Number) item.get("price")).doubleValue();
                int quantity = ((Number) item.get("quantity")).intValue();
                double discount = options.containsKey("discount") ? ((Number) options.get("discount")).doubleValue() : 0.0;
                double total = price * quantity * (1 - discount);
                
                Map<String, Object> processedItem = new HashMap<>();
                processedItem.put("name", item.getOrDefault("name", "Unnamed"));
                processedItem.put("total", total);
                if (total > 100) {
                    processedItem.put("category", "expensive");
                } else {
                    processedItem.put("category", "cheap");
                }
                result.add(processedItem);
            } else {
                System.out.println("Skipping item: " + item);
            }
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
        
        List<Map<String, Object>> result = processor.processData(data, options);
        System.out.println(result);
    }
}