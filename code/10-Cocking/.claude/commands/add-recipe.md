---

description: Find a recipe online and save it locally
argument-hint: <recipe name>
allowed-tools: WebSearch, WebFetch, Write, Read, Glob
-----------------------------------------------------

# Add Recipe

The requested recipe is:

`$ARGUMENTS`

## Validation

1. Verify that `$ARGUMENTS` contains a recipe name.
2. If no recipe name was provided, stop and show:

   `Usage: /add <recipe name>`

## Search

1. Search the web for a reliable recipe for `$ARGUMENTS`.
2. Prefer established cooking websites or well-known recipe sources.
3. Compare at least two sources when possible.
4. Create one clear and practical recipe based on the sources.
5. Do not copy long passages verbatim.
6. Remove advertising, personal stories and unnecessary introductory text.
7. Use reasonable quantities, cooking times and serving sizes based on the sources.
8. If sources disagree, choose the most common and practical version.

## File Name

Create a lowercase filename from the recipe name.

Rules:

* Replace spaces with hyphens.
* Remove special characters.
* Use only lowercase letters, numbers and hyphens.
* Use the `.md` extension.

Example:

`Pasta Bolognese`

becomes:

`pasta-bolognese.md`

## Save Location

Save the recipe locally in:

`recipes/`

Example target path:

`recipes/pasta-bolognese.md`

Create the folder if it does not exist.

## Duplicate Handling

Before writing, check whether the target file already exists.

If it exists:

* Do not overwrite it.
* Report that the recipe already exists.
* Show the existing file path.
* Stop the command.

## Recipe Format

Create the recipe using exactly this Markdown structure:

```markdown
# Recipe Name

Category: Category name
Time: Total time in minutes
Servings: Number of servings

## Ingredients

* Quantity and ingredient
* Quantity and ingredient
* Ingredient without quantity

## Instructions

1. First instruction.
2. Second instruction.
3. Continue until the recipe is complete.
```

Follow these formatting rules:

* Use exactly one `#` heading for the recipe name.
* Use the fields `Category`, `Time` and `Servings` in that order.
* Leave one empty line between each section.
* Use `*` for every ingredient.
* Include quantities and units when available.
* Use a numbered list for instructions.
* Write one clear action per instruction.
* Do not add a description, introduction, notes, tips or source section.
* Use simple English.
* Preserve this structure for every recipe.

Example:

```markdown
# Cheese Omelette

Category: Breakfast
Time: 10 minutes
Servings: 1

## Ingredients

* 3 eggs
* 50 g cheese
* 1 tablespoon milk
* 10 g butter
* Salt
* Black pepper

## Instructions

1. Crack the eggs into a bowl.
2. Add the milk, salt and black pepper.
3. Whisk until combined.
4. Melt the butter in a frying pan.
5. Pour the egg mixture into the pan.
6. Add the cheese when the eggs begin to set.
7. Fold the omelette and cook until the cheese has melted.
```

## Completion

1. Save the completed recipe in `recipes/`.
2. Verify that the file was created.
3. Read the saved file and verify that it follows the required format.
4. Report:

   * recipe name,
   * created filename,
   * complete file path.

Do not run `init`.

Do not update `results/recipes-summary.md`.

Do not ask for confirmation unless the target file already exists.
