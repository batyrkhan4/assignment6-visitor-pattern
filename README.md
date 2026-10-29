# Assignment 6 - File System Visitor Pattern

This project simulates a basic file system structure (Directories and Files) and uses the **Visitor Design Pattern** to perform operations like calculating total file sizes and searching for specific files. 

## Clean Code Refactoring
This project has been heavily optimized based on Robert C. Martin's *Clean Code* principles, specifically focusing on **Emergence (Chapter 12)** and **Concurrency (Chapter 13)**.

### Chapter 12: Emergence (Simple Design)
* **Expressiveness & Clutter-Free:** Removed unused imports (e.g., `java.nio.file.FileVisitor`, `javax.swing.*`) to reduce noise, making the intent of the classes clearer.
* **No Duplication:** Ensured traversal logic is cleanly handled via the Visitor pattern to prevent scattered, duplicated recursive loops.

### Chapter 13: Concurrency (Thread Safety)
* **Thread-Safe Collections:** Replaced the standard `ArrayList` in `Directory` with `CopyOnWriteArrayList`. This prevents `ConcurrentModificationException` if a thread adds a file while another thread is calculating the directory size.
* **Protected Shared State:** Replaced the primitive `long totalSize` in `SizeCalculatorVisitor` with `AtomicLong`. This ensures that size accumulation is an atomic, thread-safe operation.
* **Limiting Data Scope:** Wrapped the search results list in `SearchVisitor` with `Collections.synchronizedList()` and guarded the return method with a `synchronized` block to safely encapsulate the data from concurrent modifications.

## Project Structure

```text
src/
 ├── Main.java                        # Entry point of the application
 ├── elements/
 │    ├── FileSystemElement.java      # Base interface for elements accepting visitors
 │    ├── Directory.java              # Composite element representing a folder
 │    └── File.java                   # Leaf element representing a file
 └── visitors/
      ├── FileSystemVisitor.java      # Base interface for visitors
      ├── SearchVisitor.java          # Visitor to search files by name
      └── SizeCalculatorVisitor.java  # Visitor to calculate total size
