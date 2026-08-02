# Architecture Summary — JavaTDDLoop (GuessGame Application)

## Java Source Path

```text
C:\Users\jonashe2\claude-code-basics\code\12-JavaCodeLoop\JavaTDDLoop\src
```

Build: Maven (`pom.xml`), Java 17 source/target, JUnit Jupiter 5.10.2 (test scope only, no other runtime dependencies).

## Source Files Analyzed

Main (`src/main/java`):

- `com/example/domain/GuessGame.java`
- `com/example/domain/NumberGuesser.java`
- `com/example/domain/SecretNumberGenerator.java`
- `com/example/runner/RunnerGuessGame.java`
- `com/example/valueobjects/GameResult.java`
- `com/example/valueobjects/Interval.java`

Test (`src/test/java`):

- `com/example/GameResultTest.java`
- `com/example/GuessGameTest.java`
- `com/example/IntervalTest.java`
- `com/example/NumberGuesserTest.java`
- `com/example/SecretNumberGeneratorTest.java`
- `com/example/testsupport/FakeSecretNumberGenerator.java`
- `com/example/testsupport/RecordingNumberGuesser.java`
- `com/example/testsupport/StubbornNumberGuesser.java`

Total: 6 main files, 8 test files (14 Java files).

## Package Hierarchy

```text
com.example
├── domain
│   ├── GuessGame
│   ├── NumberGuesser
│   └── SecretNumberGenerator
├── runner
│   └── RunnerGuessGame
├── valueobjects
│   ├── GameResult
│   └── Interval
└── (test only) testsupport
    ├── FakeSecretNumberGenerator
    ├── RecordingNumberGuesser
    └── StubbornNumberGuesser
```

## Classes and Their Types

| Class | Package | Type | Role |
|---|---|---|---|
| `Interval` | `valueobjects` | Java `record` (immutable value object) | Inclusive integer range `[lower, upper]`, self-validating |
| `GameResult` | `valueobjects` | Java `record` (immutable value object) | Outcome of one game (secret number, attempts, success flag), self-validating |
| `SecretNumberGenerator` | `domain` | Domain class, collaborator | Produces a random secret number within an `Interval`; wraps a `RandomGenerator` |
| `NumberGuesser` | `domain` | Domain class, strategy | Produces the next guess via binary-search midpoint of an `Interval` |
| `GuessGame` | `domain` | Domain class, orchestrator | Plays the full game loop, coordinating `SecretNumberGenerator` and `NumberGuesser` |
| `RunnerGuessGame` | `runner` | Runner / application entry point (`final`, non-instantiable) | Wires dependencies and executes one game via `main` |
| `FakeSecretNumberGenerator` | `testsupport` (test-only) | Test double (stub), extends `SecretNumberGenerator` | Always returns a fixed secret, ignoring the interval |
| `RecordingNumberGuesser` | `testsupport` (test-only) | Test double (spy), extends `NumberGuesser` | Records every interval passed to `nextGuess`, then delegates to real midpoint logic |
| `StubbornNumberGuesser` | `testsupport` (test-only) | Test double (broken/adversarial), extends `NumberGuesser` | Always returns the same fixed guess regardless of interval, used to test the game's termination guarantee |

## Public APIs

### `Interval` (record: `lower: int`, `upper: int`)
- `Interval(int lower, int upper)` — canonical constructor; compact validation throws `IllegalArgumentException` if `lower > upper`.
- `static Interval of(int lower, int upper)` — static factory, delegates to constructor.
- `int lower()`, `int upper()` — record accessors.
- Structural equality/`hashCode`/`toString` from record semantics (used directly in test assertions, e.g. `assertEquals(FULL_RANGE, recorded.get(0))`).

### `GameResult` (record: `secretNumber: int`, `attempts: int`, `success: boolean`)
- `GameResult(int secretNumber, int attempts, boolean success)` — canonical constructor; compact validation throws `IllegalArgumentException` if `attempts <= 0`.
- `static GameResult success(int secretNumber, int attempts)`
- `static GameResult failure(int secretNumber, int attempts)`
- `int secretNumber()`, `int attempts()`, `boolean success()` — record accessors.
- `RunnerGuessGame` relies on the record's generated `toString()` for `System.out.println(result)`.

### `SecretNumberGenerator`
- `public SecretNumberGenerator()` — default constructor, uses `RandomGenerator.getDefault()`.
- `protected SecretNumberGenerator(RandomGenerator random)` — injectable-random constructor (used only for subclassing/testing).
- `public int generate(Interval interval)` — returns a random int in `[interval.lower(), interval.upper()]` inclusive; overridable (used by `FakeSecretNumberGenerator`).
- `static SecretNumberGenerator create()` — static factory.

### `NumberGuesser`
- `public int nextGuess(Interval interval)` — returns `lower + (upper - lower) / 2` (binary-search midpoint, integer division truncates toward zero); overridable.
- `static NumberGuesser create()` — static factory.

### `GuessGame`
- `public GuessGame(Interval startInterval, SecretNumberGenerator generator, NumberGuesser guesser)` — constructor injection; each argument null-checked via `Objects.requireNonNull`, throwing `NullPointerException` with a descriptive message on null.
- `static GuessGame of(Interval startInterval, SecretNumberGenerator generator, NumberGuesser guesser)` — static factory, delegates to constructor.
- `public GameResult play()` — runs the guessing loop (see Application Flow below) and returns a `GameResult`.

### `RunnerGuessGame`
- `private RunnerGuessGame()` — private constructor, class is non-instantiable.
- `public static void main(String[] args)` — application entry point.

## Class Dependencies

```text
RunnerGuessGame (runner)
  └── creates Interval.of(1, 100)
  └── creates SecretNumberGenerator.create()
  └── creates NumberGuesser.create()
  └── creates GuessGame.of(interval, generator, guesser)   [constructor injection]
       └── GuessGame
            ├── depends on Interval        (valueobjects)
            ├── depends on SecretNumberGenerator (domain)  — generates secret number once per play()
            ├── depends on NumberGuesser    (domain)        — asked for next guess each iteration
            └── produces GameResult         (valueobjects)
  └── prints result.toString()

SecretNumberGenerator
  └── depends on Interval (valueobjects)
  └── depends on java.util.random.RandomGenerator (JDK)

NumberGuesser
  └── depends on Interval (valueobjects)

GameResult, Interval
  └── no dependencies on other project classes (pure value objects)
```

Constructor injection: `GuessGame` receives its `Interval`, `SecretNumberGenerator`, and `NumberGuesser` collaborators through its constructor (validated non-null), rather than constructing them itself. `RunnerGuessGame.main` is the composition root that builds and wires all three before calling `play()`.

Test doubles (`FakeSecretNumberGenerator`, `RecordingNumberGuesser`, `StubbornNumberGuesser`) exploit this injection point plus Java subclassing/method overriding to substitute behavior without modifying `GuessGame`.

## Application Flow

1. `RunnerGuessGame.main` is the sole entry point.
2. It builds a starting `Interval` of `[1, 100]`.
3. It creates a `SecretNumberGenerator` and a `NumberGuesser` via their static factories.
4. It creates a `GuessGame` via `GuessGame.of(...)`, injecting the three collaborators.
5. It calls `game.play()`, which runs the core algorithm:
   a. Generate one secret number for the whole play-through: `generator.generate(startInterval)`.
   b. Initialize `currentInterval = startInterval`, `attempts = 0`.
   c. Compute `maxAttempts = startInterval.upper() - startInterval.lower() + 1` (the count of integers in the starting interval — an upper bound on attempts needed for any adversarial guesser, guaranteeing termination).
   d. Loop while `attempts < maxAttempts`:
      - Ask the guesser for `nextGuess(currentInterval)`.
      - Increment `attempts`.
      - If the guess equals the secret, return `GameResult.success(secretNumber, attempts)` immediately.
      - If the guess is below the secret, narrow interval to `[guess + 1, currentInterval.upper()]`.
      - Else (guess above the secret), narrow interval to `[currentInterval.lower(), guess - 1]`.
   e. If the loop exhausts `maxAttempts` without a match, return `GameResult.failure(secretNumber, attempts)` (with `attempts == maxAttempts`).
6. `main` prints `result` via its record-generated `toString()`.

## Behavior That Must Be Preserved

- **Interval invariant**: `lower <= upper` required; constructing with `lower > upper` must raise an error (Java: `IllegalArgumentException`). Equal bounds (`lower == upper`) are valid (single-value interval).
- **GameResult invariant**: `attempts` must be `> 0`; `attempts <= 0` must raise an error (Java: `IllegalArgumentException`). Zero and negative attempts are both rejected.
- **GuessGame null-safety**: constructing `GuessGame` with a null `startInterval`, `generator`, or `guesser` must raise an error (Java: `NullPointerException`), independently for each of the three parameters.
- **Midpoint guess formula**: `NumberGuesser.nextGuess` must compute exactly `lower + (upper - lower) // 2` using integer (truncating) division — this exact formula is asserted by parameterized tests (e.g. `(1,100)→50`, `(5,10)→7`, `(-10,10)→0`, `(1,2)→1`). Must preserve Java's truncate-toward-zero integer division semantics, which differs from Python's floor division for negative operands — see conversion note below.
- **Guess bounded within interval**: the guess must always satisfy `lower <= guess <= upper`.
- **Secret generation bounded within interval**: `SecretNumberGenerator.generate` must always return a value in `[lower, upper]` inclusive; for a single-value interval it must always return that value.
- **Single secret per play-through**: the secret number is generated exactly once at the start of `play()`, not re-generated per attempt.
- **Interval narrowing after a wrong guess**:
  - If `guess < secret`: new interval is `[guess + 1, upper]`.
  - If `guess > secret`: new interval is `[lower, guess - 1]`.
  - The interval must strictly narrow (shrink in width) on every iteration when using the real `NumberGuesser` (verified by `intervalNarrowsAsPlayProgresses`, which also checks the secret stays within the interval at every step).
- **Success path**: when `guess == secret`, `play()` returns immediately with `GameResult.success(secretNumber, attempts)` using the current attempt count (no further guessing).
- **Guaranteed termination / failure path**: `play()` must never loop indefinitely even if the guesser always returns the same non-converging guess. It must give up after exactly `maxAttempts = upper - lower + 1` attempts and return `GameResult.failure(secretNumber, attempts)` with `attempts == maxAttempts`. Verified test: for `Interval(1,100)` and a guesser stuck on `1`, secret `50`, expects `attempts == 100` and `success == false`, within a 2-second timeout.
- **Full-range correctness bound**: with the real binary-search `NumberGuesser` over `Interval(1,100)`, every integer secret 1–100 must be found successfully within 7 attempts inclusive (verified exhaustively for all 100 secrets). Single-value intervals must succeed in exactly 1 attempt.
- **Boundary secrets**: the lowest (1) and highest (100) secrets in the full range must be found within 7 attempts.
- **Record equality/value semantics**: `Interval` and `GameResult` must support value-based equality (used directly in test assertions comparing whole objects, e.g. `assertEquals(FULL_RANGE, recorded.get(0))`).
- **Static factory equivalence**: `Interval.of`, `GameResult.success`/`failure`, `SecretNumberGenerator.create`, `NumberGuesser.create`, and `GuessGame.of` must behave identically to their corresponding constructors.
- **Runner behavior**: the entry point must build an `Interval(1, 100)`, a default `SecretNumberGenerator`, a default `NumberGuesser`, play one game, and print the result's string representation to standard output.
- **Overridable extension points**: `SecretNumberGenerator.generate` and `NumberGuesser.nextGuess` are designed to be overridden by substitute implementations (fakes/spies/stubs) without changing `GuessGame`'s logic — the equivalent Python collaborators should remain substitutable (e.g., via duck typing, subclassing, or dependency injection) to preserve testability.

## Relevant Java-to-Python Conversion Considerations

- **Records → dataclasses**: `Interval` and `GameResult` are natural fits for Python `@dataclass(frozen=True)` (or `NamedTuple`) to preserve immutability and value equality. Validation performed in the Java compact constructor should be reproduced in `__post_init__` (for frozen dataclasses, note that `__post_init__` cannot assign fields normally, but no field derivation is needed here — only validation — so this is straightforward).
- **Exception mapping**:
  - `IllegalArgumentException` (invalid `Interval`, invalid `GameResult.attempts`) → Python `ValueError`.
  - `NullPointerException` (`GuessGame` null-checked constructor args) → Python `TypeError` or `ValueError` is the idiomatic choice, since Python has no direct `NullPointerException` equivalent; pick one and apply consistently, and note the change in the conversion report since it's an observable behavior-type difference. `Objects.requireNonNull`'s per-parameter descriptive messages should be preserved as Python exception messages.
- **Integer division semantics**: Java's `/` on `int` truncates toward zero; Python's `//` floors toward negative infinity. These differ for negative operands. `NumberGuesser.nextGuess` computes `(upper - lower) / 2` where `upper >= lower`, so `(upper - lower)` is always `>= 0`, making truncation and floor division equivalent in this specific case — safe to use Python `//` here, but this should be called out since it's a subtle semantic trap if the formula or inputs ever change.
- **Random number generation**: `RandomGenerator.nextInt(bound)` (Java) should map to Python's `random.randrange(bound)` or `random.randint(lower, upper)`; verify inclusive/exclusive bound semantics match (Java `nextInt(bound)` is exclusive upper, `[0, bound)`, matching `random.randrange(bound)`).
- **Static factory methods**: `of`, `create`, `success`, `failure` can become `@classmethod` or `@staticmethod` factory functions/constructors in Python, or module-level functions — either preserves the API shape described above.
- **Constructor injection**: straightforward to preserve via `__init__` parameters in Python; no special framework needed given the small dependency graph.
- **Method overriding for test doubles**: `FakeSecretNumberGenerator`, `RecordingNumberGuesser`, `StubbornNumberGuesser` subclass and override single methods. Python subclassing achieves the same effect directly; alternatively these could be simplified to duck-typed classes/functions since Python does not require inheritance for structural substitution, but subclassing better preserves the original architecture and is recommended.
- **Non-instantiable runner class**: `RunnerGuessGame`'s private constructor and `main` pattern is idiomatically replaced by a module-level `main()` function guarded by `if __name__ == "__main__":` in Python; there is no need to prevent instantiation of a class since no class is required.
- **`toString()` on records**: Java records auto-generate `toString()` (e.g. `GameResult[secretNumber=42, attempts=5, success=true]`) which is printed by the runner. Python dataclasses auto-generate a similar but differently formatted `__repr__`/`str` (e.g. `GameResult(secretNumber=42, attempts=5, success=True)`). The printed output format will differ from Java's; if exact output text matters to any consumer, this should be flagged as a known difference in the conversion report — no test in this Java project asserts the exact string format, so this is a low-risk, cosmetic difference.
- **Package structure → module structure**: `com.example.domain`, `com.example.valueobjects`, `com.example.runner` map cleanly to Python packages `domain/`, `valueobjects/`, `runner/` as instructed by the target project layout.
- **JUnit parameterized tests (`@CsvSource`)**: map to `pytest.mark.parametrize` with equivalent argument tuples; `@Timeout(2)` maps to `pytest-timeout` or a manual timing assertion.
- **Protected constructor for random injection**: `SecretNumberGenerator`'s `protected SecretNumberGenerator(RandomGenerator random)` exists solely to support test-double subclassing overriding `generate()` rather than actually injecting a random source (no test uses this constructor directly — `FakeSecretNumberGenerator` overrides `generate()` entirely instead). This constructor's protected visibility has no Python equivalent (Python has no access modifiers); a single-underscore convention or simply an internal constructor parameter can be used if reproduced at all — it may not be needed since no test exercises it.

## Ambiguities / Notes

- No ambiguities were found in the algorithmic behavior — all logic is fully specified by the source and thoroughly pinned down by the test suite (35 test methods total across 5 test classes, including exhaustive coverage of all 100 possible secrets in the default range).
- The `protected SecretNumberGenerator(RandomGenerator random)` constructor is present in the source but not exercised by any test; its intended use (direct random-source injection) is inferable from the JDK type but not verified. Flagged above as a low-priority/optional item for the Python port.
- No configuration files, external I/O, logging, or persistence exist anywhere in this application — it is a pure, self-contained, single-run console program with no CLI arguments consumed (`main(String[] args)` ignores `args`).
