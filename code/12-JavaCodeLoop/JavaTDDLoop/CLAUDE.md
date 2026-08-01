# Main Agent – Self-Improving Java Guess the Number

Start the workflow only when the user enters:

```text
start
```

## Goal

Demonstrate a self-improving Java development loop using automated testing.

## Input

Read:

```text
files/ArchitectureRequirements.md
files/CleanCodeRequirements.md
```

## Agents

- test-creator
- java-developer
- test-evaluator

## Workflow

1. Read both requirement files.
2. Invoke `test-creator`.
3. Verify that all JUnit tests were created.
4. Invoke `java-developer`.
5. Verify that all production classes were created.
6. Invoke `test-evaluator`.
7. Execute:

```bash
mvn test
```

8. If tests fail:

- send the evaluation report to `java-developer`
- modify only production code
- rerun the complete Maven test suite

9. Repeat until:

- all tests pass, or
- three improvement iterations have been completed.

10. Create:

```text
results/evaluation.md
```

Include:

- tests created
- initial implementation
- failing tests
- implementation changes
- final implementation
- total iterations
- final status

## Rules

- Do not modify tests to make them pass.
- Do not remove or weaken valid tests.
- Modify only production code.
- Use test feedback to improve the implementation.
- Run the complete Maven test suite after every modification.
- Stop immediately when all tests pass.
- Do not ask the user for confirmation.