# Guess the Number

This project demonstrates how three subagents collaborate to solve a number guessing game.

## Agents

- `number-creator` generates a secret integer between 1 and 100.
- `number-guesser` proposes the next guess.
- `game-analyst` analyzes the completed game and writes the final report to `log.txt`.
- You are the main agent and coordinate the entire process.

---

## When the user types "start"

### Start the Game

1. Invoke `number-creator` exactly once.
2. Store the returned secret number in your own context.
3. Never reveal the secret number to `number-guesser`.
4. Initialize:

- `lower = 1`
- `upper = 100`
- `attempts = 0`
- `gameHistory = []`

---

## Main Game Loop

Repeat until the correct number is found.

### 1. Invoke the Guesser

Invoke `number-guesser`.

Provide only:

- `lower`
- `upper`

Do not reveal the secret number.

### 2. Receive Guess

Receive the guessed number.

Increase:

`attempts = attempts + 1`

### 3. Evaluate Guess

Compare the guess with the secret number.

Determine whether it is:

- Too low
- Too high
- Correct

Display the result.

Example:

```text
Guess 1: 50 — Too low
Guess 2: 75 — Too high
Guess 3: 63 — Correct
```

### 4. Store Game History

Append one entry to `gameHistory` containing:

- Attempt number
- Guess
- Result
- Lower bound before the guess
- Upper bound before the guess

### 5. Update Interval

If the guess is too low:

```
lower = guess + 1
```

If the guess is too high:

```
upper = guess - 1
```

Repeat until the guess is correct.

---

## Finish the Game

Display:

```text
Correct!

The secret number was X.

number-guesser needed Y attempts.
```

---

## Analyze the Game

Invoke `game-analyst` exactly once.

Provide:

- Secret number
- Number of attempts
- Complete game history

Ask `game-analyst` to:

- Analyze the game.
- List every guessed number in order.
- Display the complete guess history.
- Verify every interval update.
- Verify that every guess was inside the valid interval.
- Evaluate whether binary search was followed correctly.
- Evaluate whether the game was completed efficiently.
- Suggest improvements if applicable.
- Display the complete report in the terminal.
- Create `log.txt` if it does not exist.
- Otherwise overwrite the existing `log.txt`.
- Save the exact same report to `log.txt`.

---

## Important Rules

- Always use all three subagents.
- Invoke `number-creator` exactly once.
- Invoke `number-guesser` until the correct number is found.
- Invoke `game-analyst` exactly once after the game.
- Never reveal the secret number to `number-guesser`.
- `number-guesser` may only receive the current search interval.
- Keep the complete game history in memory.
- Display every guess immediately in the terminal.
- Do not ask the user to make guesses.
- Run the game automatically.
- Only `game-analyst` may create or modify `log.txt`.
- The report displayed in the terminal must be identical to the contents of `log.txt`.
- The main agent is responsible for coordinating all communication between the subagents.