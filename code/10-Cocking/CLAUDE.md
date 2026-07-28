# Recipe App – Main Agent

You are the main agent for the Recipe App project.

The project supports two commands:

* `init`
* `propose`

## Command: init

Use this command when recipes have been added, removed or changed.

### Workflow

1. Check that `files/recipes/` exists.
2. Check that the folder contains at least one Markdown recipe.
3. Create the `results/` folder if it does not exist.
4. Invoke the `recipe-reader` subagent.
5. Verify that `results/recipes-summary.md` was created.
6. Report how many recipes were processed.

### Rules

* Read every Markdown file in `G:/Min enhet/cocking-support/recipes/`.
* Do not invoke `ingredient-reader`.
* Do not invoke `recipe-matcher`.
* Overwrite `results/recipes-summary.md` without asking.
* Do not modify the original recipe files.
* Keep the final response short.

## Command: propose

Use this command to recommend recipes based on the ingredients currently available at home.

### Workflow

1. Check that `results/recipes-summary.md` exists.
2. If it does not exist, tell the user to run `init`.
3. Check that `G:/Min enhet/cocking-support/at-home/ingredients-avail.docx` exists.
4. Invoke the `ingredient-reader` subagent.
5. Invoke the `recipe-matcher` subagent.
6. Verify that `G:/Min enhet/cocking-support/results/recommendations.docx` was created.
7. Display the recommendations to the user.

### Rules

* Do not read the original recipe files.
* Use `results/recipes-summary.md` as the recipe source.
* Overwrite temporary and result files without asking.
* Do not modify `G:/Min enhet/cocking-support/at-home/ingredients-avail.docx`.
* Do not modify `results/recipes-summary.md`.
* Keep the final response short.

## General Rules

* Use only files inside this project.
* Do not search the web.
* Do not ask for confirmation before reading or writing project files.
* If a required file is missing, clearly report which file is missing.
* Do not run both commands unless explicitly requested.
* The file ingredients-avail must be in docx format to enable smooth mobile ios editing
* The file and recommendations is in docx to enable smooth mobile ios reading
