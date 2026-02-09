# Gradle Convention Plugins

This document describes all Gradle convention plugins available in the `:build-logic` module. These plugins standardize build configuration across all modules in the RickAndMorty project, ensuring consistency and reducing boilerplate.

## Overview

Convention plugins encapsulate common build configuration patterns, allowing modules to apply standardized settings with minimal code. All plugins are defined in `build-logic/convention/` and use the version catalog (`gradle/libs.versions.toml`) for dependency versions.

**Benefits of using convention plugins:**
- Reduced boilerplate in module `build.gradle.kts` files.
- Centralized configuration management.
- Consistent SDK versions, Java compatibility, and plugin versions.
- Easier project-wide updates.

---

## Available Plugins

### 1. `convention.android.application`
- **Implementation:** `AndroidApplicationConventionPlugin.kt`
- **Purpose:** Configures the main Android application module (`:composeApp`).
- **What it does:** Applies the `com.android.application` plugin and sets up standard Android configuration using a shared function. This includes SDK versions, Java 11 compatibility, and default packaging options.

---

### 2. `convention.android.library`
- **Implementation:** `AndroidLibraryConventionPlugin.kt`
- **Purpose:** Configures a **pure Android** library module (not KMP).
- **What it does:** Applies the `com.android.library` plugin and configures standard Android library settings.

---

### 3. `convention.android.kmp.library`
- **Implementation:** `AndroidLibraryKmpConventionPlugin.kt`
- **Purpose:** Configures the Android-specific parts of a KMP library module.
- **What it does:** Applies `com.android.library` and `org.jetbrains.kotlin.multiplatform`, and sets up standard Android library configuration. It is a building block for the `convention.kmp.feature` plugin.

---

### 4. `convention.kotlin.multiplatform`
- **Implementation:** `KotlinMultiplatformConventionPlugin.kt`
- **Purpose:** Configures the KMP targets for a module.
- **What it does:** Applies `org.jetbrains.kotlin.multiplatform` and sets up targets for `android` and `ios`. This is a core building block for KMP modules.

---

### 5. `convention.compose.multiplatform`
- **Implementation:** `ComposeMultiplatformConventionPlugin.kt`
- **Purpose:** Configures Compose Multiplatform for a KMP UI module.
- **What it does:** Applies the necessary Compose plugins and adds a default set of `commonMain` dependencies for Compose (`runtime`, `foundation`, `material3`, `ui`, etc.).

---

### 6. `convention.ktlint`
- **Implementation:** `KtlintConventionPlugin.kt`
- **Purpose:** Enforces code style using ktlint.
- **What it does:** Applies the `ktlint` plugin, adds Compose-specific rules, and configures a standard set of excluded paths. It is included automatically by `convention.kmp.feature`.

---

### 7. `convention.kmp.feature`
- **Implementation:** `KmpFeatureConventionPlugin.kt`
- **Purpose:** **The most common plugin for KMP library modules.**
- **What it does:** This is a composite plugin that applies `convention.kotlin.multiplatform`, `convention.android.kmp.library`, and `convention.ktlint`. It provides the standard setup for any KMP module that is part of a feature (domain, data, presentation, etc.).

#### Example: KMP Data Layer Module
```kotlin
// :feature:characters:data/build.gradle.kts
plugins {
    id("convention.kmp.feature")
}

android {
    namespace = "xyz.aranhapreta.rickandmorty.feature.characters.data"
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                // Module-specific dependencies
            }
        }
    }
}
```

#### Example: KMP Presentation (UI) Module
```kotlin
// :feature:characters:presentation/build.gradle.kts
plugins {
    id("convention.kmp.feature")
    id("convention.compose.multiplatform") // Add this for UI modules
}

android {
    namespace = "xyz.aranhapreta.rickandmorty.feature.characters.presentation"
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                // Voyager, Coil, etc.
            }
        }
    }
}
```
