# Architecture

Three packages: `domain` (behavior), `valueobjects` (immutable data), `runner` (entry point).

```
RunnerGuessGame (main)
        │ wires
        ▼
    GuessGame ──uses──▶ SecretNumberGenerator ──▶ Interval
        │  │
        │  └─uses──▶ NumberGuesser ──▶ Interval
        └─produces──▶ GameResult
```

# Classes


| Name                    | Type                    | Methods                                                                     |
|-------------------------|-------------------------|-----------------------------------------------------------------------------|
| `GuessGame`             | Domain                  | `play()` — runs the game and returns a `GameResult`.                        |
| `NumberGuesser`         | Domain                  | `nextGuess(Interval interval)` — returns the interval’s midpoint.           |
| `SecretNumberGenerator` | Domain                  | `generate(Interval interval)` — returns a random value within the interval. |
| `Interval`              | Value object (`record`) | `lower()`, `upper()` — returns the range bounds.                            |
| `GameResult`            | Value object (`record`) | `secretNumber()`, `attempts()`, `success()` — returns the game outcome.     |
| `RunnerGuessGame`       | Runner                  | `main(String[] args)` — creates and runs the game.                          |


## Full source of RunnerGuessGame

        var startInterval = Interval.of(1, 100);
        var generator = SecretNumberGenerator.create();
        var guesser = NumberGuesser.create();
        var game = GuessGame.of(startInterval, generator, guesser);
        var result = game.play();
        System.out.println(result);