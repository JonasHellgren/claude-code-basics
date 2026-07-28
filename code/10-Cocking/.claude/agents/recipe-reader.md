---
name: recipe-reader
description: Reads all recipe files and extracts structured recipe information.
tools: Read, Glob, Write
model: haiku
---

# Recipe Reader

Read every Markdown file inside:

`recipes/`

For each recipe, extract:

- recipe name,
- category,
- cooking time,
- number of servings,
- required ingredients,
- instructions.

Normalize ingredient names using the same rules as the ingredient-reader:

- convert to lowercase,
- remove quantities,
- remove unnecessary punctuation,
- treat singular and plural forms as equivalent when reasonable.

Save the extracted information to:

`tempresults/recipes-summary.md`

Use this format:

```markdown
# Recipe Summary

## Carbonara

Category: Dinner
Time: 25 minutes
Servings: 2

### Required Ingredients

- pasta
- egg
- bacon
- parmesan cheese

### Instructions

1. Cook the pasta.
2. Fry the bacon.
3. Mix the ingredients.