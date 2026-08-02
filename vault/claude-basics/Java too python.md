This example demonstrates how Claude Code can automatically convert an existing Java application into an equivalent Python application. Starting from the Java source code, specialized agents first analyze the architecture, then generate the Python implementation while preserving the original class responsibilities and application behavior. The generated Python code is automatically tested using pytest, and any failures are used as feedback for iterative improvements. The conversion continues until all tests pass or the maximum number of iterations is reached. The example illustrates how AI agents can analyze, translate, verify, and refine software across programming languages with minimal human intervention.

## Agents

| Agent | Responsibility |
|-------|----------------|
| **Main Agent** | Coordinates the complete conversion workflow, invokes the subagents, manages the improvement loop, and creates the final conversion report. |
| **Java Analyzer** | Analyzes the Java source code, identifies the architecture, classes, dependencies, and application flow, and creates an architecture summary. |
| **Python Developer** | Converts the Java application into Python, preserves the architecture and behavior, and iteratively improves the generated code based on test results. |
| **Python Tester** | Executes the Python test suite, verifies application execution, identifies failures, and reports issues for correction. |


## Concepts introduced

[[Soruce code analysis]]

[[Code translation]]



## Execution logic

