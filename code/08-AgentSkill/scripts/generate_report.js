const {
  Document,
  Packer,
  Paragraph,
  TextRun,
  HeadingLevel,
  PageBreak,
} = require("docx");
const fs = require("fs");
const path = require("path");

// Splits `text` into TextRun segments, bolding any run that matches one of `boldPhrases`.
function buildRuns(text, boldPhrases) {
  if (!boldPhrases.length) return [new TextRun(text)];

  let remaining = text;
  const runs = [];
  while (remaining.length > 0) {
    let earliestIdx = -1;
    let earliestPhrase = null;
    for (const phrase of boldPhrases) {
      const idx = remaining.indexOf(phrase);
      if (idx !== -1 && (earliestIdx === -1 || idx < earliestIdx)) {
        earliestIdx = idx;
        earliestPhrase = phrase;
      }
    }
    if (earliestIdx === -1) {
      runs.push(new TextRun(remaining));
      break;
    }
    if (earliestIdx > 0) {
      runs.push(new TextRun(remaining.slice(0, earliestIdx)));
    }
    runs.push(new TextRun({ text: earliestPhrase, bold: true }));
    remaining = remaining.slice(earliestIdx + earliestPhrase.length);
  }
  return runs;
}

function sectionForFile(fileName, original, correctedText, changes, isLast) {
  const children = [];

  children.push(
    new Paragraph({ text: fileName, heading: HeadingLevel.HEADING_1 })
  );

  children.push(
    new Paragraph({ text: "Original Text", heading: HeadingLevel.HEADING_2 })
  );
  const boldPhrases = changes.map((c) => c.original);
  original.split("\n").forEach((line) => {
    children.push(new Paragraph({ children: buildRuns(line, boldPhrases) }));
  });

  children.push(
    new Paragraph({ text: "Corrected Text", heading: HeadingLevel.HEADING_2 })
  );
  correctedText.split("\n").forEach((line) => {
    children.push(new Paragraph({ children: [new TextRun(line)] }));
  });

  children.push(
    new Paragraph({ text: "Changes", heading: HeadingLevel.HEADING_2 })
  );
  changes.forEach((c) => {
    children.push(
      new Paragraph({
        text: `"${c.original}" → "${c.corrected}" (${c.reason})`,
        bullet: { level: 0 },
      })
    );
  });

  if (!isLast) {
    children.push(
      new Paragraph({ children: [new PageBreak()] })
    );
  }

  return children;
}

const fileData = [
  {
    fileName: "file1.md",
    original: "This is the first line. The secoond line are wrong.",
    corrected: "This is the first line. The second line is wrong.",
    changes: [
      { original: "secoond", corrected: "second", reason: "Spelling" },
      {
        original: "are",
        corrected: "is",
        reason: "Grammar – subject–verb agreement",
      },
    ],
  },
  {
    fileName: "file2.md",
    original:
      "The moonlite whispers softly threw the silent trees,\nAnd every star remembrees the dreams we never sead.",
    corrected:
      "The moonlight whispers softly through the silent trees,\nAnd every star remembers the dreams we never said.",
    changes: [
      { original: "moonlite", corrected: "moonlight", reason: "Spelling" },
      { original: "threw", corrected: "through", reason: "Word choice" },
      {
        original: "remembrees",
        corrected: "remembers",
        reason: "Spelling",
      },
      { original: "sead", corrected: "said", reason: "Spelling" },
    ],
  },
];

const bodyChildren = [
  new Paragraph({
    text: "Academic English Review",
    heading: HeadingLevel.TITLE,
  }),
];

fileData.forEach((f, idx) => {
  bodyChildren.push(
    ...sectionForFile(
      f.fileName,
      f.original,
      f.corrected,
      f.changes,
      idx === fileData.length - 1
    )
  );
});

const doc = new Document({
  sections: [
    {
      properties: {},
      children: bodyChildren,
    },
  ],
});

const outDir = path.join(__dirname, "..", "results");
Packer.toBuffer(doc).then((buffer) => {
  fs.mkdirSync(outDir, { recursive: true });
  fs.writeFileSync(path.join(outDir, "report.docx"), buffer);
  console.log(`Processed files: ${fileData.length}`);
  const totalChanges = fileData.reduce((sum, f) => sum + f.changes.length, 0);
  console.log(`Total corrections: ${totalChanges}`);
});
