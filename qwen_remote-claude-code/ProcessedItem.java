import java.util.*;

/**
 * Represents a processed item with calculated values and category.
 */
public class ProcessedItem {
    private String name;
    private double total;
    private String category;

    /**
     * Creates a new ProcessedItem.
     *
     * @param name The name of the item
     * @param total The calculated total value
     * @param category The category based on total value
     */
    public ProcessedItem(String name, double total, String category) {
        this.name = name;
        this.total = total;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public double getTotal() {
        return total;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ProcessedItem that = (ProcessedItem) obj;
        return Double.compare(that.total, total) == 0 &&
               Objects.equals(name, that.name) &&
               Objects.equals(category, that.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, total, category);
    }

    @Override
    public String toString() {
        return "ProcessedItem{" +
                "name='" + name + '\'' +
                ", total=" + total +
                ", category='" + category + '\'' +
                '}';
    }
}