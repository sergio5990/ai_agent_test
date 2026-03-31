# Architectural Changes Documentation

## Summary

The original monolithic `DataProcessor.processData()` method has been refactored into a well-structured, maintainable codebase following object-oriented design principles.

## New Classes Created

### 1. `ProcessedItem.java`
**Responsibility:** Data model representing a processed item.

**Why:** 
- Replaces unsafe `Map<String, Object>` with a type-safe class
- Encapsulates item properties (name, total, category) with proper getters
- Contains the category determination logic as a static method
- Uses an enum for category values instead of magic strings ("expensive"/"cheap")

### 2. `ItemProcessor.java`
**Responsibility:** Pure calculation logic for computing totals.

**Why:**
- Separates mathematical calculations from data orchestration
- Contains validation logic for price, quantity, and discount
- Provides reusable calculation methods that can be tested independently
- Follows Single Responsibility Principle (SRP)

### 3. `DataProcessingException.java`
**Responsibility:** Custom exception for data processing errors.

**Why:**
- Replaces `System.out.println` error handling with proper exceptions
- Provides specific error messages for debugging
- Allows callers to catch and handle processing errors appropriately
- Follows Java best practices for error handling

### 4. `DataProcessor.java` (Refactored)
**Responsibility:** Orchestrates the data processing workflow.

**Why:**
- Now focuses on coordination rather than calculation
- Delegates calculations to `ItemProcessor`
- Returns type-safe `List<ProcessedItem>` instead of `List<Map<String, Object>>`
- Contains clear separation of extraction, validation, and processing logic

## Key Improvements

| Issue | Before | After |
|-------|--------|-------|
| **Error Handling** | `System.out.println` for errors | Custom exceptions (`DataProcessingException`, `IllegalArgumentException`) |
| **Type Safety** | Raw `Map<String, Object>` with unsafe casts | Type-safe `ProcessedItem` class with enum |
| **Magic Numbers** | Hardcoded `100` threshold | Constant `CATEGORY_THRESHOLD` in `ProcessedItem` |
| **Magic Strings** | "expensive"/"cheap" strings | `Category` enum (`CHEAP`, `EXPENSIVE`) |
| **Method Length** | Single 30-line method | Multiple focused methods (5-15 lines each) |
| **Documentation** | No Javadoc | Complete Javadoc for all classes and methods |
| **Testability** | Difficult to test individual logic | Each class can be unit tested independently |
| **Separation of Concerns** | All logic in one method | Calculation, data model, and orchestration separated |

## Design Principles Applied

1. **Single Responsibility Principle (SRP):** Each class has one reason to change
2. **Don't Repeat Yourself (DRY):** Validation logic is centralized
3. **Fail Fast:** Invalid data throws exceptions immediately
4. **Type Safety:** Compile-time checking instead of runtime cast errors
5. **Encapsulation:** Internal implementation details are private

## Files Structure

```
qwen_code+qwen_remote/
├── DataProcessor.java          # Main orchestrator (refactored)
├── DataProcessingException.java # Custom exception
├── ItemProcessor.java          # Calculation logic
├── ProcessedItem.java          # Data model
├── Task.md                     # Requirements
└── QWEN.md                     # Project context
```

## Usage Example

```java
DataProcessor processor = new DataProcessor();
Map<String, Object> options = new HashMap<>();
options.put("discount", 0.1);

try {
    List<ProcessedItem> result = processor.processData(data, options);
    for (ProcessedItem item : result) {
        System.out.println(item.getName() + " - $" + item.getTotal());
        System.out.println("Category: " + item.getCategory());
    }
} catch (DataProcessingException e) {
    System.err.println("Processing failed: " + e.getMessage());
}
```
