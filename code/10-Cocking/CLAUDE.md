# Recipe App – Main Agent

You are the main agent for the Recipe App project.

The project supports two commands:

* `init`
* `propose`

## Google drive
Some project data files are stored in the Google Drive folder:
G:/Min enhet/cocking-support
Refer to this folder as pathdrive.
When a workflow mentions pathdrive, replace it with the full path above.

All subagents must be given the full Google Drive path when they are invoked. Do not assume that subagents automatically inherit the value of pathdrive.
Example:
pathdrive/ingredients-at-home.txt
means:
G:/Min enhet/cocking-support/ingredients-at-home.txt

## Command: init

Use this command when recipes have been added, removed or changed.

### Workflow

1. Check that `recipes/` exists.
2. Check that the folder contains at least one Markdown recipe.
3. Create the `tempresults/` folder if it does not exist.
4. Invoke the `recipe-reader` subagent.
5. Verify that `tempresults/recipes-summary.md` was created.
6. Report how many recipes were processed.

### Rules

* Read every Markdown file in `recipes/`.
* Do not invoke `ingredient-reader`.
* Do not invoke `recipe-matcher`.
* Overwrite `tempresults/recipes-summary.md` without asking.
* Do not modify the original recipe files.
* Keep the final response short.

## Command: propose

Use this command to recommend recipes based on the ingredients currently available at home.

### Workflow

1. Check that `tempresults/recipes-summary.md` exists.
2. If it does not exist, tell the user to run `init`.
3. Check that `pathdrive/at-home/ingredients-avail.docx` exists.
4. Invoke the `ingredient-reader` subagent.
5. Invoke the `recipe-matcher` subagent.
6. Verify that `pathdrive/tempresults/recommendations.pdf` was created.
7. Display the recommendations to the user.

### Rules

* Do not read the original recipe files.
* Use `tempresults/recipes-summary.md` as the recipe source.
* Overwrite temporary and result files without asking.
* Do not modify `pathdrive/at-home/ingredients-avail.docx`.
* Do not modify `tempresults/recipes-summary.md`.
* Keep the final response short.

## General Rules

* Use only files inside this project.
* Do not search the web.
* Do not ask for confirmation before reading or writing project files.
* If a required file is missing, clearly report which file is missing.
* Do not run both commands unless explicitly requested.
* The file ingredients-avail must be in docx format to enable smooth mobile ios editing
* The recommendations file is in pdf format to enable smooth mobile ios reading
* The `recipe-matcher` subagent cannot write binary files itself; after it produces the recommendations content, use the `document-skills:pdf` skill to generate `pathdrive/results/recommendations.pdf`

## Recipe Change Hook

When a hook reports that a Markdown file in `recipes/` was created or edited:

1. Run the complete `init` workflow immediately.
2. Rebuild `results/recipes-summary.md`.
3. Verify that the changed recipe is included.
4. Do not ask for confirmation.
5. Run `init` only once for each recipe change.

