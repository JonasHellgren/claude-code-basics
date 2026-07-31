The example starts with rough bullet points in `poles.md` and writing rules in `requirements.md`. The `text-writer` converts the bullet points into a complete first draft. The `text-reviewer` checks the draft against requirements such as paragraph structure, length, language, clarity, and style. If the text does not pass, the `text-modifier` applies the feedback and creates a new version. The loop continues until the text is approved or the maximum number of iterations is reached.

## Agents

| Agent           | Role                                                                                                                                        |
| --------------- | ------------------------------------------------------------------------------------------------------------------------------------------- |
| Main Agent      | Coordinates the workflow, reads the maximum iteration argument, invokes the subagents, manages the review loop, and saves the final result. |
| `text-writer`   | Reads `poles.md` and `requirements.md`, then creates the first complete text in `results/current-text.md`.                                  |
| `text-reviewer` | Reviews the current text against all requirements, assigns a score, and writes specific feedback to `results/latest-review.md`.             |
| `text-modifier` | Revises `results/current-text.md` using the latest review and prepares the text for another review iteration.                               |
## Concepts introduced

[[Iterative improvement]]

## Execution logic

![[textloop.png]]