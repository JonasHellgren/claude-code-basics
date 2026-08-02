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

## GuessGame
- `play()` — runs the game: generates a secret, repeatedly narrows the interval via the guesser until it matches or the attempt budget (interval size) is exhausted; returns a success or failure `GameResult`.

## NumberGuesser
- `nextGuess(Interval interval)` — returns the interval's midpoint (binary search).

## SecretNumberGenerator
- `generate(Interval interval)` — returns a random value within the interval.

## Interval (record)
- `lower()`, `upper()` — range bounds.

## GameResult (record)
- `secretNumber()`, `attempts()`, `success()` — outcome of a play-through.

## RunnerGuessGame
- `main(String[] args)` — wires a `[1, 100]` game and prints the result.

## Full source of RunnerGuessGame

        var startInterval = Interval.of(1, 100);
        var generator = SecretNumberGenerator.create();
        var guesser = NumberGuesser.create();
        var game = GuessGame.of(startInterval, generator, guesser);
        var result = game.play();
        System.out.println(result);