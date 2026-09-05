# gpt_mobile

Modern Android GPT & LLM client built with Jetpack Compose, featuring MCP (Model Context Protocol) tool integration and multi-provider agent workflows.

## Features

- **Multi-Provider AI & Chat**: Support for multiple model providers with rich markdown streaming and chat history.
- **DeepSeek Reasoner Support**: Real-time parsing of `<think>` reasoning thoughts with collapsible accordion UI showing duration, token metrics, and thought processes.
- **Background Agent Execution**: Foreground service execution (`AgentForegroundService`) allowing multi-step autonomous agent runs with persistent notification status.
- **Native Tools**:
  - Native Android Location Tool (`ACCESS_FINE_LOCATION` / `ACCESS_COARSE_LOCATION`) for real-time geolocation context.
  - Device info, web access, and custom tool calling capabilities.
- **MCP (Model Context Protocol) Marketplace & Presets**:
  - Built-in catalog hub for discovering and installing community MCP servers (GitHub, Brave Search, Fetch, Filesystem, Google Maps, Memory).
  - Single-click server installation and environment variable configuration.
- **Per-Chat Tool Configuration**:
  - Customizable tool enablement per conversation thread via an interactive bottom sheet.
  - Pin favorites, filter active tools, and grant individual tool permissions.
- **Chat Management**: Chat favorites, pin-to-top, search, and SQLite/Room migrations.

## Releases & Downloads

Pre-built APK artifacts are automatically generated for each release and attached to [GitHub Releases](https://github.com/tailscale-signin/gpt_mobile/releases).

### Building Locally

To build the APK locally:

```bash
# Debug build
./gradlew assembleDebug

# Release build
./gradlew assembleRelease
```

The generated APK will be located at:
`app/build/outputs/apk/debug/app-debug.apk` or `app/build/outputs/apk/release/app-release.apk`.
