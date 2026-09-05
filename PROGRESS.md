# Project Progress & Build Roadmap

## 🚀 Latest Releases & Builds

| Version | Status | Release Notes / Download | CI Workflow |
|---------|--------|--------------------------|-------------|
| **v1.1.0** | 🚀 **Building / Latest** | [Download APK & Release v1.1.0](https://github.com/tailscale-signin/gpt_mobile/releases/tag/v1.1.0) | [![Build and Release APK](https://github.com/tailscale-signin/gpt_mobile/actions/workflows/build-apk.yml/badge.svg)](https://github.com/tailscale-signin/gpt_mobile/actions/workflows/build-apk.yml) |
| **v1.0.0-8** | ✅ Stable | [Release v1.0.0-8](https://github.com/tailscale-signin/gpt_mobile/releases/tag/v1.0.0-8) | Completed |

---

## 📋 Changelog & Features Added in v1.1.0

### 1. 🧠 DeepSeek Reasoner & Thought Accordion
- **Stream Parser**: Real-time parsing of `<think>` and `</think>` tags during streaming responses.
- **Collapsible UI**: Expandable thinking accordion with duration calculation, token metrics, and reasoning traces.
- **Test Suite**: Dedicated unit tests verifying multiline thoughts and edge cases (`ThinkingParserTest.kt`).

### 2. ⚡ Background Agent Execution
- **Foreground Service**: `AgentForegroundService` keeping multi-step MCP agent loops alive when app goes to background.
- **System Notification**: Interactive status updates, progress indicators, and cancellation actions.

### 3. 📍 Native Device Location Tool
- **Device Location**: `DeviceLocationTool` MCP-compatible tool exposing lat/lng and accuracy to the model.
- **Permissions**: Runtime permission check and fallback mechanisms.

### 4. 🏪 MCP Tool Marketplace & Catalog
- **Preset Catalog**: Built-in repository of popular MCP servers (Fetch, Memory, Filesystem, GitHub, Brave Search).
- **One-Click Setup**: Install presets directly into local configuration without manual JSON entry.

### 5. 🎯 Per-Chat Tool Configuration
- **Scoped Tools**: Conversation-level tool enabling/disabling via bottom sheet.
- **Context Optimization**: Keep token contexts lean by only enabling relevant tools per chat.

---

## 🛠️ Verification & Build Instructions

To build locally from source:
```bash
./gradlew clean assembleDebug
```
Output location:
`app/build/outputs/apk/debug/app-debug.apk`

To run unit tests:
```bash
./gradlew test
```
