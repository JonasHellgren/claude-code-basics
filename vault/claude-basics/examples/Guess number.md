
Guess the Number demonstrates collaboration between three AI subagents. It is one of many [[Examples]]. This project introduces basic agent orchestration, communication, and task delegation in Claude Code. 


## Agents

| Agent          | Responsibility                                                                                                                  |
| -------------- | ------------------------------------------------------------------------------------------------------------------------------- |
| Main Agent     | Coordinates the game, stores the secret number, manages the search interval, invokes subagents, and maintains the game history. |
| number-creator | Generates one random secret number (1–100).                                                                                     |
| number-guesser | Guesses the secret number using the current search interval.                                                                    |
| game-analyst   | Analyzes the completed game, validates the strategy, reports the results, and saves the analysis to `log.txt`.                  |

## Concepts introduced

[[Subagent]]

## Execution logic

![[guess-num-flow.png]]