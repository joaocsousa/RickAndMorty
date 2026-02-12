import utils.configureDependencies

/**
 * Convention plugin for Kotlin Multiplatform libraries.
 *
 * Configures:
 * - foundation.convention.android.kmp.library (Android + KMP)
 * - convention.ktlint
 * - Android library settings
 * - iOS targets
 * - Common dependencies (Kermit, Koin)
 *
 * Note: Consuming modules must specify `android.namespace`.
 */
plugins {
    id("foundation-convention-kmp-library")
    id("foundation-convention-ktlint")
}

configureDependencies(extension = kotlin, compose = false)
