# Build Logic

This directory contains Gradle convention plugins that standardize build configuration across all modules in the RickAndMorty project.

## Quick Reference

### Available Convention Plugins

| Plugin ID                          | Purpose                                      |
|------------------------------------|----------------------------------------------|
| `ComposeMultiPlatformApplication`  | Android app modules (KMP + Compose)          |
| `PureKotlinMultiPlatformLibrary`   | KMP libraries without Android dependencies   |
| `AndroidKotlinMultiPlatformLibrary`| KMP libraries with Android dependencies      |
| `AndroidComposeMultiPlatformLibrary`| KMP libraries with Android + Compose UI     |
| `convention.ktlint`                | Code style enforcement                       |

### Most Common Configurations

**KMP Feature Module (Pure Kotlin):**
```kotlin
plugins {
    id("PureKotlinMultiPlatformLibrary")
}
```

**KMP Feature Module (Android Dependencies):**
```kotlin
plugins {
    id("AndroidKotlinMultiPlatformLibrary")
}

android {
    namespace = "xyz.aranhapreta.rickandmorty.feature.mylib"
}
```

**KMP UI Module (Android + Compose):**
```kotlin
plugins {
    id("AndroidComposeMultiPlatformLibrary")
}

android {
    namespace = "xyz.aranhapreta.rickandmorty.feature.ui"
}
```

**Main Android App:**
```kotlin
plugins {
    id("ComposeMultiPlatformApplication")
}

android {
    namespace = "xyz.aranhapreta.rickandmorty.android"
}
```

## Full Documentation

For a detailed description of each plugin and its purpose, see the documentation file.

📖 **[Complete Convention Plugins Documentation](CONVENTION_PLUGINS.md)**
