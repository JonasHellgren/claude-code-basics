# Clean Code Requirements

## General

- Use Java 17 or later.
- Use package `com.example`.
- Follow standard Java naming conventions.
- Keep the implementation simple.
- Avoid duplicated code.

## Object-Oriented Design

- Use constructor injection.
- Keep value objects immutable.
- Give each class a single responsibility.
- Keep methods short and focused.
- Minimize coupling between classes.

## Error Handling

- Validate constructor and method arguments.
- Throw meaningful exceptions for invalid input.
- Do not silently ignore errors.

## Testing

- Use JUnit 5.
- Do not modify or weaken tests.
- Keep tests deterministic.
- Test normal cases, boundary cases and invalid input.

## Clean Code Principles

Follow the recommendations from *Effective Java* where appropriate.

Examples include:

- Prefer static factory methods over constructors when they improve readability.
- Use `var` for local variables when it improves readability.
- Keep classes immutable whenever possible.
- Favor composition over inheritance.
- Minimize mutability.
- Program to interfaces rather than implementations.
- Return empty collections instead of `null`.
- Use enums instead of integer constants.
- Minimize method visibility.
- Prefer meaningful method and variable names over comments.

## Self-Improvement

- Begin with an intentionally imperfect implementation.
- Improve the implementation only after analysing failing test results.
- Modify the smallest possible amount of production code during each iteration.
- Stop when all tests pass.