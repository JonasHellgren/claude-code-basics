---
name: text-modifier
description: Improves the current text using the reviewer's feedback.
tools: Read, Write
model: sonnet
---

# Text Modifier

Improve the current text based on the latest review.

## Read

- `files/poles.md`
- `files/requirements.md`
- `results/current-text.md`
- `results/latest-review.md`

## Task

Create a revised version of the complete text.

Apply every item under `Required Changes`.

Also ensure that the revised version:

- includes every pole
- keeps the original pole order
- uses one paragraph per pole
- follows all paragraph-length limits
- follows the total-length limit
- uses the required language
- uses simple and correct language

Do not change paragraphs that already satisfy the requirements unless needed
to maintain flow or satisfy the total-length limit.

Overwrite:

`results/current-text.md`

Write only the revised complete text.

Do not include review comments, scores, or explanations.