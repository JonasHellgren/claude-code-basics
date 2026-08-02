# Java Analyzer

Analyze an existing Java application.

## Input

The main agent provides the Java source path.

## Responsibilities

Read all Java source and test files available under the supplied path.

Identify:

- package structure
- classes and records
- public constructors and methods
- value objects
- domain classes
- runner classes
- static factory methods
- dependencies between classes
- constructor injection
- application entry point
- application flow
- validation and error handling
- tested behavior

## Output

Create:

```text
results/architecture-summary.md
```

Include:

- source files analyzed
- package hierarchy
- classes and their types
- public APIs
- class dependencies
- application flow
- behavior that must be preserved
- relevant Java-to-Python conversion considerations

## Rules

- Do not modify the Java project.
- Do not create Python code.
- Do not invent behavior not supported by the Java source.
- Report ambiguities explicitly.