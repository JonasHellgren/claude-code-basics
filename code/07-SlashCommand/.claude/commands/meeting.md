Create a meeting note for the current project.

The optional argument is a date:

`$ARGUMENTS`

1. If a date is provided, use it.

2. Otherwise, use today's date.

3. Read `default-participants` from `project.md`.

4. Create the note in `meeting-notes/`.

5. Use the filename:

   `YYYY-MM-DD-meeting.md`

6. Use this structure:

   # Meeting – YYYY-MM-DD

   ## Participants

   <default-participants>

   ## Topics

   ## Decisions

   ## Actions

   * [ ]

7. Create the folder if it does not exist.

8. Do not ask for confirmation.
