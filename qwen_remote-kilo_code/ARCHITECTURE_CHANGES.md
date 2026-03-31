# Architectural Changes Documentation

## Overview
The original DataProcessor.java contained spaghetti code with multiple responsibilities in a single method. The refactoring separated concerns into distinct classes with specific purposes.

## Classes Created

### 1. TotalCalculator.java
- **Purpose**: Handles all financial calculations (price × quantity × (1 - discount))
- **Responsibility**: Performs mathematical operations and validates input values
- **Benefits**: Centralized calculation logic, easy to modify calculation formulas, proper input validation

### 2. CategoryClassifier.java
- **Purpose**: Determines item categories based on total value
- **Responsibility**: Classifies items as 'expensive' or 'cheap' based on a threshold
- **Benefits**: Separated business logic from data processing, easy to modify classification rules

### 3. DataProcessingException.java
- **Purpose**: Custom exception for data processing errors
- **Responsibility**: Provides structured error reporting instead of console printing
- **Benefits**: Better error handling, allows calling code to decide how to handle errors

### 4. Updated DataProcessor.java
- **Purpose**: Orchestrates the data processing workflow
- **Responsibilities**: Coordinates between other classes, manages the processing flow
- **Benefits**: Clear separation of concerns, easier to maintain and extend

## Key Improvements

### 1. Separation of Concerns
- Each class has a single, well-defined responsibility
- Calculation logic is separate from data processing logic
- Error handling is centralized in appropriate places

### 2. Error Handling
- Replaced console printing with proper exception handling
- Added input validation with descriptive error messages
- Used custom exceptions to provide structured error reporting

### 3. Documentation
- Added comprehensive Javadoc for all classes and methods
- Clear parameter and return value descriptions
- Exception conditions documented

### 4. Maintainability
- Code is now modular and easier to test
- Changes to calculation logic don't affect data processing logic
- New features can be added without modifying existing functionality

## Benefits of This Architecture

1. **Testability**: Individual components can be tested in isolation
2. **Maintainability**: Changes to one aspect don't affect others
3. **Extensibility**: New features can be added easily
4. **Readability**: Code structure clearly shows the intent and flow
5. **Reusability**: Components can be reused in other contexts

This refactoring transforms the original monolithic approach into a clean, object-oriented design that follows SOLID principles.
