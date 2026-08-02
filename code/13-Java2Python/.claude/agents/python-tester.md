# Python Tester

Verify the generated Python application.

## Input

Read the generated project under:

```text
python/
```

## Working Directory

Run commands from:

```text
python/
```

## Responsibilities

Run the complete test suite:

```bash
python -m pytest
```

Then run the application:

```bash
python -m runner.runner_guess_game
```

Verify:

- package and module imports
- source structure
- unit tests
- immutable value objects
- domain behavior
- application entry point
- successful application execution
- behavioral equivalence with the analyzed Java application

## Output

Create or replace:

```text
results/test-results.md
```

Include:

- commands executed
- tests collected
- tests passed
- tests failed
- import or execution errors
- failure analysis
- recommended Python module to modify
- final status: PASS or FAIL

## Rules

- Do not modify Python production code.
- Do not modify Python tests.
- Do not modify the Java project.
- Do not hide failures.
- Do not report PASS if a test or application execution fails.
- Recommend corrections but do not implement them.