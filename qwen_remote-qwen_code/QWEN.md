# Project Overview

This is a **Java refactoring exercise** project. It contains a single Java class (`DataProcessor.java`) with intentionally poorly structured "spaghetti code" that serves as a practice target for code refactoring.

## Purpose

The project demonstrates common code quality issues and provides a concrete example for practicing:
- Separation of concerns
- Error handling best practices
- Code documentation (Javadoc)
- Architectural improvements

## Directory Contents

| File | Description |
|------|-------------|
| `DataProcessor.java` | The main Java file containing monolithic code that needs refactoring |
| `Task.md` | Requirements document specifying the refactoring tasks |
| `QWEN.md` | This context file |

## Current Code Issues

The `DataProcessor.processData()` method exhibits several anti-patterns:
- **Mixed responsibilities**: Calculation logic, data transformation, and logging are all in one method
- **Poor error handling**: Uses `System.out.println` instead of proper exceptions
- **Magic numbers**: Hardcoded values like `100` for category thresholds
- **Type casting**: Unsafe raw casts without validation
- **No documentation**: Missing Javadoc comments

## Refactoring Requirements (from Task.md)

1. Break down the monolithic method into logical classes with proper separation of concerns
2. Add error handling (use exceptions instead of printing to console)
3. Write Javadoc for each new class and method
4. Explain the architectural changes made and why

## Building and Running

**Compile:**
```bash
javac DataProcessor.java
```

**Run:**
```bash
java DataProcessor
```

## Technologies

- **Language**: Java (uses `java.util.*` - Maps, Lists, HashMaps, ArrayLists)
- **No external dependencies**: Pure standard library Java

## Development Notes

This is a minimal test/exercise project, not a full application. There are no:
- Build tools (Maven, Gradle)
- Test frameworks
- Package structure (single default package)
- External libraries
