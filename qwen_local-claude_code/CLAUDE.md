# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

This is a simple Java project that processes data involving price calculations. The main class `DataProcessor.java` contains a single monolithic method that handles data processing, calculations, and output.

## Key Files and Structure

- `DataProcessor.java` - Main class that processes data with price calculations
- `Task.md` - Contains refactoring requirements for the DataProcessor class

## Development Setup and Commands

### Building/Running
- The code is a simple Java application that can be compiled with `javac DataProcessor.java` and run with `java DataProcessor`

### Testing
- This project appears to be a simple demonstration without formal tests, but the main class includes sample test data in its `main` method that can be used for validation

### Development Workflow
1. Make changes to DataProcessor.java according to the refactoring requirements in Task.md:
   - Break down monolithic method into logical classes
   - Add proper error handling and exceptions instead of console output
   - Add Javadoc documentation to new classes and methods
2. Compile with: `javac DataProcessor.java`
3. Run with: `java DataProcessor`

## Architecture and Implementation Details

The current architecture is a single monolithic class with:
- Data processing logic mixed with calculation logic
- Output handling (console printing) mixed with business logic
- No proper separation of concerns

The refactoring task requires:
1. Separation of calculation logic into its own class
2. Data processing operations into a separate class
3. Proper exception handling instead of console output printing
4. Javadoc documentation of new components

The project follows a simple data processing pattern where:
- Input is a list of maps containing item information (name, price, quantity)
- Options map may contain discount information
- Processed results include calculated totals and category classification

Key architectural improvements needed:
1. Move calculation logic to a dedicated CalculationService class
2. Separate data processing concerns into DataProcessor or DataTransformation classes
3. Implement proper error handling with custom exceptions
4. Add comprehensive Javadoc documentation to all new classes and methods

The main challenge is that the current implementation uses generic `Map<String, Object>` types throughout and mixes multiple responsibilities in one method.