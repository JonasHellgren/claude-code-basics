$inputJson = [Console]::In.ReadToEnd()

try {
    $data = $inputJson | ConvertFrom-Json
}
catch {
    exit 0
}

$filePath = $data.tool_input.file_path

if (-not $filePath) {
    exit 0
}

$normalizedPath = $filePath.Replace("\", "/").ToLower()

# Trigger only for Markdown files directly inside recipes/
if ($normalizedPath -match "(^|/)recipes/[^/]+\.md$") {
    $output = @{
        hookSpecificOutput = @{
            hookEventName = "PostToolUse"
            additionalContext = @"
A recipe file was created or edited in recipes/.

Immediately run the complete init workflow defined in CLAUDE.md.
Do not ask for confirmation.
Run init only once for this recipe change.
"@
        }
    }

    $output | ConvertTo-Json -Depth 5 -Compress
}

exit 0