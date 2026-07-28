
A skill is more than just a short prompt. It can include detailed instructions, scripts, and additional resources that Claude automatically loads whenever the task requires them.

Run the following command inside Claude Code:

```
/plugin marketplace add anthropics/skills
```

Then install the document skills package:

```
/plugin install document-skills@anthropic-agent-skills
```

Alternatively, open `/plugin`, select **Browse and install plugins**, choose **anthropic-agent-skills**, and then install **document-skills**.

After installation, you normally do not need to invoke a specific slash command. Simply describe the task in natural language, and it is often helpful to explicitly mention the relevant skill (for example, the PDF, DOCX, PPTX, or XLSX skill). Claude will automatically load and use the appropriate skill when needed.


## Example skill


The skill *document-skills* enable Claude Code to generate structured output in common file formats instead of plain text. Examples include Word documents (.docx), PDF files (.pdf), presentations (.pptx), and spreadsheets (.xlsx). These skills handle formatting, layout, and file generation automatically, allowing agents to focus on the content while producing professional, ready-to-use artifacts.