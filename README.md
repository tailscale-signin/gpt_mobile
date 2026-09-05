# 🤖 gpt_mobile

[![CI Build](https://github.com/tailscale-signin/gpt_mobile/actions/workflows/build-apk.yml/badge.svg)](https://github.com/tailscale-signin/gpt_mobile/actions/workflows/build-apk.yml)
[![Latest Release](https://img.shields.io/github/v/release/tailscale-signin/gpt_mobile?include_prereleases&color=blue&label=release)](https://github.com/tailscale-signin/gpt_mobile/releases)
[![Kotlin](https://img.shields.io/badge/Kotlin-2.0+-purple.svg)](https://kotlinlang.org)
[![Android](https://img.shields.io/badge/Platform-Android%208.0%2B%20%28API%2026%2B%29-green.svg)](https://developer.android.com)
[![License](https://img.shields.io/badge/License-Apache%202.0-lightgrey.svg)](LICENSE)

A modern, high-performance Android AI assistant client built from scratch using **Jetpack Compose** and **Material 3**. Engineered for power users, developers, and autonomous agent workflows with first-class **Model Context Protocol (MCP)** integration.

---

## ✨ Key Highlights & Features

### 🧠 DeepSeek Reasoner & Thought Streaming
- Real-time extraction and streaming of model `<think>` reasoning traces.
- Elegant, collapsible thought accordions with elapsed thinking time, token counts, and step inspection.
- Full markdown formatting, syntax-highlighted code blocks, and mathematical notation.

### ⚡ Background Agent Service
- Autonomous multi-step agent runs powered by `AgentForegroundService`.
- Persistent foreground notifications displaying live progress, execution steps, and cancellation controls.
- Resilient background task execution even when the screen turns off or the app is minimized.

### 🔌 Model Context Protocol (MCP) Ecosystem
- **Built-in Marketplace**: Discover, search, and 1-click install standard MCP servers (GitHub, Brave Search, Fetch, Filesystem, Google Maps, Memory).
- **Custom MCP Transports**: Connect to remote HTTP/SSE servers or local bridges with configurable headers and environment variables.
- **Per-Chat Tool Scoping**: Configure and toggle distinct tool sets on individual conversations via a dedicated tools drawer/bottom sheet.

### 📱 Native Device Capabilities
- **Location Integration**: Privacy-respecting native Android location provider (`ACCESS_FINE_LOCATION` / `ACCESS_COARSE_LOCATION`) for real-time geographic queries.
- **Device & Sensor APIs**: Extensible architecture to expose native Android sensors, battery status, and device telemetry to agents.

### 💬 Conversation & Storage Management
- Fully local storage powered by Android **Room** and SQLite with automatic schema migrations.
- Pin chats to top, star favorites, instant search, and export conversation histories.
- Multiple workspace profiles and custom system prompt overrides.

---

## 🏗️ Architecture & Tech Stack

| Layer | Technologies |
| :--- | :--- |
| **UI & Presentation** | Jetpack Compose, Material 3, Navigation Compose, Compose Animation |
| **Architecture** | MVVM / MVI Pattern, Clean Architecture, Kotlin Coroutines & Flow |
| **Local Persistence** | Android Room Database, DataStore Preferences |
| **Networking & Streaming**| OkHttp 4, Retrofit, Server-Sent Events (SSE) streaming |
| **AI Protocols** | OpenAI API compatible schema, Anthropic API, Model Context Protocol (MCP) |
| **Background Execution**| Android Foreground Services, NotificationManagerCompat |

---

## 🚀 Getting Started & Downloads

### Download Pre-built APKs
Pre-compiled debug and release APKs are automatically built on every update. Download the latest APK directly from the [GitHub Releases](https://github.com/tailscale-signin/gpt_mobile/releases) page.

### Building from Source

Ensure you have Android Studio Ladybug (or newer) and JDK 17+ installed:

```bash
# Clone the repository
git clone https://github.com/tailscale-signin/gpt_mobile.git
cd gpt_mobile

# Build Debug APK
./gradlew assembleDebug

# Run unit tests
./gradlew test
```

Generated APKs will be located at:
`app/build/outputs/apk/debug/app-debug.apk`

---

## 🤝 Contributing

Contributions, issue reports, and feature suggestions are welcome! Feel free to open an issue or submit a pull request.
