
Guess the Number demonstrates collaboration between three AI subagents. This project introduces basic agent orchestration, communication, and task delegation in Claude Code.

Main Agent
- Coordinates the entire game.
- Stores the secret number.
- Manages the search interval.
- Invokes all subagents.
- Maintains the complete game history.

number-creator
- Generates one random secret number between 1 and 100.

number-guesser
- Guesses the secret number using the current search interval.

game-analyst
- Analyzes the completed game.
- Validates the guessing strategy and interval updates.
- Reports all guesses and performance.
- Displays the analysis.
- Saves the analysis to log.txt.