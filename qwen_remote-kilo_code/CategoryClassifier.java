import java.util.Map;

/**
 * Classifier class responsible for categorizing items based on their total value.
 * This class determines whether an item is expensive or cheap based on a threshold.
 */
public class CategoryClassifier {
    
    private static final double EXPENSIVE_THRESHOLD = 100.0;
    
    /**
     * Determines the category of an item based on its total value.
     * 
     * @param total the total value of the item
     * @return the category as a string ('expensive' or 'cheap')
     */
    public String classifyCategory(double total) {
        return total > EXPENSIVE_THRESHOLD ? "expensive" : "cheap";
    }
    
    /**
     * Adds category information to a processed item map.
     * 
     * @param processedItem the item map to add category to
     * @param total the total value used for classification
     */
    public void addCategoryToItem(Map<String, Object> processedItem, double total) {
        processedItem.put("category", classifyCategory(total));
    }
}
