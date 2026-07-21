# Guess the Number

This project demonstrates how three subagents collaborate to solve a simple problem.

## Agents

- `number-creator` generates a secret integer between 1 and 100.
- `number-guesser` proposes the next guess.
- `game-analyst` analyzes the completed game.
- You are the main agent and coordinate the entire process.

## When the user types "Start the game"

### Start the game

1. Invoke `number-creator` exactly once.
2. Store the secret number in your own context.
3. Never reveal the secret number to `number-guesser`.
4. Initialize:
   - `lower = 1`
   - `upper = 100`
   - `attempts = 0`
   - `gameHistory = []`

### Main Game Loop

Repeat until the correct number is found:

1. Invoke `number-guesser`.
2. Provide only:
   - `lower`
   - `upper`
3. Receive the next guess.
4. Increment `attempts`.
5. Compare the guess with the secret number.
6. Determine whether the guess is:
   - Too low
   - Too high
   - Correct
7. Display the result.

Example:

```text
Guess 1: 50 — Too low
Guess 2: 75 — Too high
Guess 3: 63 — Correct
```

8. Append the following information to `gameHistory`:
   - Attempt number
   - Guess
   - Result
   - Current lower limit
   - Current upper limit

9. Update the search interval:
   - If the guess is too low:
     `lower = guess + 1`
   - If the guess is too high:
     `upper = guess - 1`

10. Continue until the guess is correct.

## Finish the Game

Display:

```text
Correct!

The secret number was X.

number-guesser needed Y attempts.
```

## Analyze the Game

Invoke `game-analyst` exactly once.

Provide:

- Secret number
- Total number of attempts
- Complete game history

Ask `game-analyst` to produce a report containing:

- Secret number
- Number of attempts
- All guessed numbers
- Complete guess history
- Validation of every guess
- Validation of every interval update
- Binary search evaluation
- Efficiency evaluation
- Suggestions for improvement

Display the report.

## Important Rules

- Always use all three subagents.
- Invoke `number-creator` exactly once.
- Invoke `game-analyst` exactly once after the game.
- Never reveal the secret number to `number-guesser`.
- `number-guesser` may only receive the current search interval.
- Keep the complete game history in memory.
- Do not ask the user for guesses.
- Run the game automatically.
- Display every guess as it occurs.
- Do not create or modify files.
- The main agent is responsible for coordinating all communication between the subagents.