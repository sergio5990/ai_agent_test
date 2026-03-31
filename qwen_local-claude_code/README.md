# Data Processor Application

This is a Java application that demonstrates proper refactoring of monolithic code into well-separated components.

## Architecture Overview

The application follows a clean architecture pattern with proper separation of concerns:

1. **DataProcessor** - Main orchestrator that coordinates the data processing workflow
2. **CalculationService** - Handles all calculation logic including price and discount computations
3. **DataTransformer** - Transforms raw data into the final processed format with categories
4. **Custom Exceptions** - Proper error handling instead of console printing

## Key Refactoring Improvements

### 1. Separation of Concerns
- Calculation logic moved to `CalculationService`
- Data transformation moved to `DataTransformer`
- Main orchestration logic in `DataProcessor`

### 2. Proper Error Handling
- Replaced console printing with custom exceptions (`DataProcessingException`, `CalculationException`)
- Added comprehensive error handling for invalid data, missing fields, and calculation issues

### 3. Javadoc Documentation
- Added comprehensive javadoc comments to all new classes and methods
- Documented purpose, parameters, return values, and exceptions

### 4. Improved Code Structure
- Each class has a single responsibility
- Better maintainability and testability
- Clear interface contracts

## How to Run

```bash
# Compile all Java files
javac *.java

# Run the application
java DataProcessor
```

## Sample Output

When run, the application will process test data and output results like:
```
[{name=Laptop, total=900.0, category=expensive}, {name=Mouse, total=45.0, category=cheap}, {name=Unnamed, total=135.0, category=expensive}, {name=Keyboard, total=72.0, category=cheap}]
```

## Design Decisions Made

1. **Exception Handling**: Instead of `System.out.println("Skipping item: " + item)` in the original code, proper exceptions are now thrown to indicate data processing errors.

2. **Category Logic**: The original logic for categorizing items (expensive vs cheap) is now moved to the calculation service and properly documented.

3. **Validation**: Added validation for negative prices/quantities and invalid discount values to make the system more robust.

4. **Extensibility**: The architecture allows for easy extension - for example, adding new categories or calculation types would involve modifying the CalculationService rather than touching the main processing logic.

This refactoring transforms the original "spaghetti code" into a well-structured, maintainable application with proper separation of concerns and robust error handling.