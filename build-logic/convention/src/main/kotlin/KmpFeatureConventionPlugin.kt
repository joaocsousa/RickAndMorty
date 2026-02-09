import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * A composite convention plugin for standard Kotlin Multiplatform feature modules.
 *
 * This plugin bundles the common configurations needed for a typical KMP library module
 * that is part of a feature, such as domain, data, or presentation layers.
 *
 * ## What it applies:
 * - `convention.kotlin.multiplatform` for KMP targets (Android, iOS).
 * - `convention.android.kmp.library` for Android library specific setup.
 * - `convention.ktlint` for code style enforcement.
 *
 * ## Usage
 * Apply to your KMP feature module's `build.gradle.kts`:
 * ```kotlin
 * plugins {
 *     id("convention.kmp.feature")
 * }
 *
 * android {
 *     namespace = "xyz.aranhapreta.rickandmorty.feature.myfeature"
 * }
 *
 * kotlin {
 *     sourceSets {
 *         commonMain {
 *             dependencies {
 *                 // ...
 *             }
 *         }
 *     }
 * }
 * ```
 *
 * ## Important Notes
 * - You **must** set `android.namespace` in your module.
 * - For UI modules, you should also apply `convention.compose.multiplatform`.
 */
class KmpFeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("convention.kotlin.multiplatform")
            pluginManager.apply("convention.android.kmp.library")
            pluginManager.apply("convention.ktlint")
        }
    }
}
