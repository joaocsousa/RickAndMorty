# Gradle Convention Plugins

This document describes the Gradle convention plugins available in the `:build-logic` module. These plugins standardize build configuration across all modules in the RickAndMorty project.

## Overview

Convention plugins encapsulate common build configuration patterns. They are defined as precompiled script plugins in `build-logic/convention/src/main/kotlin/`.

---

## Available Plugins

### 1. `ComposeMultiPlatformApplication`
- **Purpose:** Configures the main Android application module with Kotlin Multiplatform and Compose Multiplatform support.
- **Applies:**
  - `com.android.application`
  - `org.jetbrains.kotlin.multiplatform`
  - `org.jetbrains.compose`
  - `org.jetbrains.kotlin.plugin.compose`
  - `convention.ktlint`
- **What it does:** Sets up the Android application, KMP targets (Android, iOS), and Compose dependencies.

### 2. `PureKotlinMultiPlatformLibrary`
- **Purpose:** Configures a pure Kotlin Multiplatform library (no Android dependencies).
- **Applies:**
  - `org.jetbrains.kotlin.multiplatform`
  - `convention.ktlint`
- **What it does:** Sets up KMP targets (iOS) and adds core dependencies like Kermit and Koin.

### 3. `AndroidKotlinMultiPlatformLibrary`
- **Purpose:** Configures a Kotlin Multiplatform library that has Android dependencies.
- **Applies:**
  - `com.android.kotlin.multiplatform.library`
  - `convention.ktlint`
- **What it does:** Sets up Android library configuration, KMP targets (Android, iOS), and adds core dependencies like Kermit and Koin.

### 4. `AndroidComposeMultiPlatformLibrary`
- **Purpose:** Configures a Kotlin Multiplatform library with Android dependencies and Compose Multiplatform UI.
- **Applies:**
  - `AndroidKotlinMultiPlatformLibrary`
  - `org.jetbrains.compose`
  - `org.jetbrains.kotlin.plugin.compose`
- **What it does:** Builds upon `AndroidKotlinMultiPlatformLibrary` and adds Compose Multiplatform configuration and dependencies.

### 5. `convention.ktlint`
- **Purpose:** Enforces code style using ktlint.
- **Applies:** `org.jlleitschuh.gradle.ktlint`
- **What it does:** Configures ktlint with standard rules and excludes.
