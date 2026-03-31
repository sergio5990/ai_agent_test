# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Repository Overview

This is a Java project containing a `DataProcessor.java` file that requires refactoring. The current implementation is described as "spaghetti code" in the Task.md file and needs to be broken down into properly separated concerns.

## Architecture

The main codebase consists of:
- `DataProcessor.java`: Contains a monolithic `processData` method that performs data processing with business logic for calculating totals based on price, quantity, and discounts
- `Task.md`: Defines the refactoring requirements

## Development Commands

To compile and run the Java code:
```bash
javac DataProcessor.java
java DataProcessor
```

## Refactoring Requirements

According to Task.md, the following improvements are needed:
1. Break down the monolithic method into logical classes with proper separation of concerns (e.g., a separate class for calculations, another for data processing)
2. Add error handling (use exceptions instead of printing to console)
3. Write Javadoc for each new class and method
4. Implement proper exception handling instead of console printing
5. Create separate classes for different responsibilities (calculation, data processing, etc.)

## Code Style Guidelines

- Use proper Javadoc documentation for all classes and methods
- Implement proper error handling with exceptions
- Follow separation of concerns principles
- Maintain clean, readable code with appropriate class decomposition