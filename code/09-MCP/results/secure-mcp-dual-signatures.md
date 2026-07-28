# Secure Model Context Protocol for Large Language Models with Dual Signatures

**Authors:** Shiqiang Li et al.
**Publication year:** 2025
**Journal/Conference:** Proceedings of the Workshop on Mobility in the Evolving Internet Architecture
**DOI/URL:** https://consensus.app/papers/details/029dcdbaacf05ad3b72a1b2f886b69be/?utm_source=claude_desktop
**Citations:** 1

## Abstract / Summary
LLMs deployed in mobile, edge, and cloud-integrated systems rely on MCP to standardize interactions with external tools, but this introduces risks such as tool poisoning attacks and malicious impersonation of the MCP Server. The paper proposes a framework for trustworthy tool invocation based on dual signature verification: a tool can be invoked only after verifying signatures from both a trusted third-party platform and the developer.

## Key Contributions
- A dual signature verification mechanism protecting against MCP Server tampering and tool-based prompt injection.
- Design that requires two independent signature checks (trusted platform + developer) before tool invocation.
- Implementation demonstrating minimal overhead while maintaining full compatibility with existing MCP servers.

## Relevance to Search Requirements
A peer-reviewed workshop paper offering a concrete cryptographic countermeasure for MCP trust and integrity issues, adding a mobile/edge-computing deployment perspective to the collection.
