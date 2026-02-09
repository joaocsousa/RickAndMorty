# Build Logic

This directory contains Gradle convention plugins that standardize build configuration across all modules in the RickAndMorty project.

## Quick Reference

### Available Convention Plugins

| Plugin ID                          | Purpose                                      | Common Usage                                     |
|------------------------------------|----------------------------------------------|--------------------------------------------------|
| `convention.android.application`   | Android app modules (KMP)                    | Main app module only (`:composeApp`)             |
| `convention.android.library`       | Android-only library modules                 | For Android-specific modules without KMP.        |
| `convention.android.kmp.library`   | Android library configuration for KMP modules| Used by other plugins; rarely applied directly.  |
| `convention.kotlin.multiplatform`  | KMP targets & base configuration             | Used by other plugins; rarely applied directly.  |
| `convention.compose.multiplatform` | Compose Multiplatform for KMP UI modules     | Shared UI modules (`:feature:*:presentation`).   |
| `convention.ktlint`                | Code style enforcement                       | Auto-applied by `kmp.feature`.                   |
| `convention.kmp.feature`           | **Standard KMP feature module**              | **Most common plugin for KMP library modules.**  |

### Most Common Configurations

**KMP Feature Module (e.g., Domain, Data):**
```kotlin
plugins {
    id("convention.kmp.feature")
}
```

**KMP UI Module (Compose Multiplatform):**
```kotlin
plugins {
    id("convention.kmp.feature")
    id("convention.compose.multiplatform")
}
```

**Main Android App:**
```kotlin
plugins {
    id("convention.android.application")
    id("convention.kotlin.multiplatform") // Or convention.kmp.feature
    id("convention.compose.multiplatform")
}
```

## Full Documentation

For a detailed description of each plugin and its purpose, see the Javadoc comments within each plugin's source file or refer to the complete documentation.

📖 **[Complete Convention Plugins Documentation](CONVENTION_PLUGINS.md)**

## Using Convention Plugins

To use a plugin, simply add it to the `plugins { ... }` block in your module's `build.gradle.kts`.

### KMP Library Module Example (`:feature:characters:data`)
```kotlin
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
                // Your module-specific dependencies here
            }
        }
    }
}
```

## Quick Tips

✅ **DO:**
- Use `convention.kmp.feature` for most KMP library modules.
- Add `convention.compose.multiplatform` for UI modules.
- Always set the `android.namespace` in your module's `build.gradle.kts`.
- Let convention plugins handle shared configuration like SDK versions and ktlint.

❌ **DON'T:**
- Manually apply plugins that are already included in a composite plugin (like `ktlint` when using `kmp.feature`).
- Hardcode versions or plugin IDs; use the `libs` version catalog.
- Override convention defaults without a specific reason.
