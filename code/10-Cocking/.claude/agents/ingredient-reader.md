---
name: ingredient-reader
description: Reads and normalizes the ingredients currently available at home.
tools: Read, Write
model: haiku
---

# Ingredient Reader

Read:

`G:/Min enhet/cocking-support/at-home/ingredients-avail.docx`

Extract all available ingredients.

Normalize ingredient names by:

- converting them to lowercase,
- removing quantities,
- removing unnecessary punctuation,
- treating singular and plural forms as equivalent when reasonable.

Examples:

- `2 eggs` becomes `egg`
- `500 g tomatoes` becomes `tomato`
- `Parmesan cheese` remains `parmesan cheese`

Save the normalized list to:

`results/normalized-ingredients.md`

Use this format:

```markdown
# Available Ingredients

- egg
- pasta
- bacon
- parmesan cheese