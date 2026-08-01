# Evaluation Report — Self-Improving Guess the Number

## Tests Created

JUnit 5 test suite under `src/test/java/com/example/` (39 tests total):

- **`IntervalTest`** (10 tests) — valid bounds (including negative ranges), single-value boundary interval, rejects `lower > upper` with `IllegalArgumentException`.
- **`GameResultTest`** (5 tests) — accessors for success/failure results, single-attempt boundary, rejects negative/zero attempts.
- **`SecretNumberGeneratorTest`** (5 tests) — 1000-sample loops asserting every generated number stays within the given interval, across several bound combinations.
- **`NumberGuesserTest`** (10 tests) — asserts the required midpoint formula `lower + (upper - lower) / 2`, plus the invariant that every guess stays within the interval.
- **`GuessGameTest`** (9 tests) — correct `GameResult` on success, every secret 1–100 found within 7 attempts (deterministic via a fake generator), lower/upper boundary secrets, single-value interval, interval-narrowing across attempts, and rejection of null constructor arguments.

Supporting test doubles in `src/test/java/com/example/testsupport/`: `FakeSecretNumberGenerator` (fixed secret) and `RecordingNumberGuesser` (records intervals passed to `nextGuess`).

## Initial Implementation

Production classes created under `src/main/java/com/example/`: `Interval`, `GameResult` (both immutable records with constructor validation), `SecretNumberGenerator`, `NumberGuesser`, `GuessGame` (constructor-injected, narrows the interval each attempt based on comparison with the secret), and `RunnerGuessGame` (entry point).

Per the architecture requirements, `NumberGuesser.nextGuess` was intentionally implemented with the imperfect initial strategy:

```java
return interval.lower();
```

The obsolete scaffold file `src/main/java/org/guesser/Main.java` (wrong package, not part of the architecture) was removed.

## Failing Tests (Iteration 1)

`mvn test` — **BUILD FAILURE**, 39 run / 33 passed / 6 failed:

- `NumberGuesserTest.guessesTheMidpointOfTheInterval` — 3 parameterized cases failed (e.g. expected `50`, got `1` for interval `(1,100)`).
- `GuessGameTest.producesCorrectGameResultOnSuccess` — success was `false`.
- `GuessGameTest.findsUpperBoundarySecretWithinSevenAttempts` — success was `false`.
- `GuessGameTest.everySecretBetween1And100IsFoundWithinSevenAttempts` — e.g. secret `8` took `8` attempts (limit 7).

**Root cause:** the linear `lower()` guessing strategy cannot guarantee finding the secret within 7 attempts across a 100-value range, and doesn't match the required midpoint values.

## Implementation Changes

Single targeted change to `NumberGuesser.nextGuess` (the only class identified as responsible):

```java
// before
return interval.lower();

// after
return interval.lower() + (interval.upper() - interval.lower()) / 2;
```

No other production class was modified; no test was modified.

## Final Implementation

`NumberGuesser` now uses binary-search midpoint guessing, matching the architecture's "final strategy". All other production classes are unchanged from the initial implementation:

- `Interval` / `GameResult` — immutable records with validating compact constructors.
- `SecretNumberGenerator` — random number generation bounded to the interval.
- `GuessGame` — constructor-injected, narrows `[lower, upper]` based on guess-vs-secret comparison, returns a `GameResult` on exact match.
- `RunnerGuessGame` — wires up and plays one game via `main`.

## Total Iterations

1 improvement iteration (of a maximum of 3 allowed).

## Final Status

**PASS** — `mvn test`: 39 run, 39 passed, 0 failed, 0 errors. BUILD SUCCESS.
