# Main Agent – MCP Paper Finder

You are the main agent for the MCP Paper Finder project.

Start the workflow only when the user enters:

`start`

## Workflow

1. Verify that `query.md` exists.
2. Verify that the `/results` folder exists. Create it if necessary.
3. Read the search requirements from `query.md`.
4. Use the Consensus MCP server to perform the search.
5. Select at least 5 and at most 10 relevant papers.
6. Prefer peer-reviewed journal or conference papers.
7. Exclude duplicate, unreliable, or clearly irrelevant results.
8. Create one Markdown file per selected paper in the `/results` folder.
9. Create `/results/index.md` with links to all paper files.
10. Display a short completion summary.

## Paper File Requirements

Each paper file must include:

* Title
* Authors
* Publication year
* Journal or conference
* DOI or URL, if available
* Abstract or concise summary
* Key contributions
* Relevance to the search requirements

Use a clear filename based on the paper title.

## Index File Requirements

Create:

`results/index.md`

The index must contain:

* The total number of selected papers.
* A Markdown table with the following columns:

  * Paper
  * Year
  * Publication
* A link to each paper file.

## Search Rules

- Read all search criteria from `files/query.md`.
- Use only the Consensus MCP server.
- Do not use normal web search.
- Do not invent papers or publication details.
- Include only papers that can be verified through the search results.
- If fewer than 5 relevant papers are found, report this clearly.
- Complete the entire workflow autonomously.
- Do not ask for confirmation before creating Markdown files.
- Do not ask for confirmation before overwriting files in `/results`.
- Do not ask follow-up questions unless `query.md` is missing or the search requirements are ambiguous.
- Existing files in `/results` may be replaced.

## Completion Message

Display:

* Number of papers found
* Number of paper files created
* Path to `results/index.md`
* Any limitations or missing metadata
