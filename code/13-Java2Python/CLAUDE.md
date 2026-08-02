# Main Agent – Java to Python Converter

Start the workflow only when the user enters:

```text
start
```

## Goal

Convert an existing Java application into an equivalent, runnable Python application while preserving its architecture and behavior.

## Java Source

Use the Java project located at:

```text
C:\Users\jonashe2\claude-code-basics\code\12-JavaCodeLoop\JavaTDDLoop\src
```

## Output

Create the Python application under:

```text
python/
├── domain/
├── valueobjects/
├── runner/
└── tests/
```

Create reports under:

```text
results/
├── architecture-summary.md
├── test-results.md
└── conversion-report.md
```

## Agents

- java-analyzer
- python-developer
- python-tester

## Workflow

1. Invoke `java-analyzer` and provide the Java source path.

2. Verify that:

```text
results/architecture-summary.md
```

was created.

3. Invoke `python-developer`.

4. Verify that the Python application was created under:

```text
python/
```

5. Invoke `python-tester`.

6. If verification fails:

- Read:

```text
results/test-results.md
```

- Invoke `python-developer` and provide the test report.
- Allow modifications only under:

```text
python/
```

- Invoke `python-tester` again.

7. Repeat the improvement loop until:

- all tests pass and the application executes successfully, or
- three improvement iterations have completed.

8. Create:

```text
results/conversion-report.md
```

## Conversion Report

Include:

- Java source path
- Java packages analyzed
- Java classes analyzed
- Generated Python modules
- Architectural mappings
- Test execution summary
- Files modified during improvement
- Number of improvement iterations
- Remaining differences
- Final status: PASS or FAIL

## Rules

- Never modify the Java source project.
- Create all Python production code under `python/`.
- Create all Python tests under `python/tests/`.
- Create reports only under `results/`.
- Preserve the Java application's behaviour.
- Preserve class responsibilities and dependencies whenever reasonable.
- Use Pythonic constructs where appropriate instead of literal Java syntax.
- Allow `python-developer` to modify generated Python code during the improvement loop.
- Never weaken valid tests simply to obtain PASS.
- Stop immediately when all tests pass.
- Do not ask the user for confirmation during the workflow.