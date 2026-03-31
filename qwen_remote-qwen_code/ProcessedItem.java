/**
 * Represents a processed item with its name, calculated total, and category.
 * This class provides a type-safe way to store processed data instead of using
 * generic Map structures.
 */
public class ProcessedItem {
    
    /**
     * Enumeration representing the category of a processed item based on its total value.
     */
    public enum Category {
        CHEAP,
        EXPENSIVE
    }

    private final String name;
    private final double total;
    private final Category category;

    /**
     * Threshold value for determining if an item is expensive or cheap.
     * Items with total greater than this value are categorized as EXPENSIVE.
     */
    private static final double CATEGORY_THRESHOLD = 100.0;

    /**
     * Constructs a new ProcessedItem with the specified properties.
     *
     * @param name the name of the item
     * @param total the calculated total value of the item
     * @param category the category classification of the item
     */
    public ProcessedItem(String name, double total, Category category) {
        this.name = name;
        this.total = total;
        this.category = category;
    }

    /**
     * Gets the name of the item.
     *
     * @return the item name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the calculated total value of the item.
     *
     * @return the total value
     */
    public double getTotal() {
        return total;
    }

    /**
     * Gets the category classification of the item.
     *
     * @return the item category
     */
    public Category getCategory() {
        return category;
    }

    /**
     * Determines the category based on the total value.
     *
     * @param total the total value to evaluate
     * @return EXPENSIVE if total exceeds threshold, CHEAP otherwise
     */
    public static Category determineCategory(double total) {
        return total > CATEGORY_THRESHOLD ? Category.EXPENSIVE : Category.CHEAP;
    }

    /**
     * Returns a string representation of this processed item.
     *
     * @return a formatted string with item details
     */
    @Override
    public String toString() {
        return String.format("ProcessedItem{name='%s', total=%.2f, category=%s}", name, total, category);
    }
}
