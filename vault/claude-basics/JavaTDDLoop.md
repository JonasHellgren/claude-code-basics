
This example demonstrates a self-improving Java development workflow using Claude Code and Test-Driven Development (TDD). The project automatically creates a small Java application for the classic Guess the Number problem from architectural requirements. It first generates JUnit tests, then implements an intentionally inefficient guessing strategy. The tests are executed with Maven, and the failing results are used as feedback to improve the implementation iteratively. The process continues until all tests pass or the maximum number of iterations is reached. The example also demonstrates a clean object-oriented architecture with value objects, domain services, constructor injection, and separation of responsibilities.
## Agents

|Agent|Responsibility|
|---|---|
|**Main Agent**|Coordinates the complete workflow, invokes the subagents, manages the improvement loop, and creates the final evaluation report.|
|**Test Creator**|Creates JUnit 5 tests from the architecture and clean-code requirements without generating production code.|
|**Java Developer**|Creates and iteratively improves the Java production code based on the test evaluation while following the architectural and clean-code requirements.|
|**Test Evaluator**|Runs the Maven test suite, analyses compilation errors and failing tests, and recommends which production class should be modified next.|


## Concepts introduced

[[Test-driven development (TDD)]]
[[Iterative improvement]]



## Execution logic
