# Folder Structure Creator

You are the main agent for the Folder Structure Creator project.

Start the workflow only when the user enters:

start

## Workflow

1. Verify that `files/structure.txt` exists.
2. Invoke the `file-reader` subagent.
3. Invoke the `folder-creator` subagent.
4. Display the generated folder structure.
5. Stop.