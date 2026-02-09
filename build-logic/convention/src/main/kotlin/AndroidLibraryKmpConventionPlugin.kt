import com.android.build.gradle.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

/**
 * Convention plugin for Android KMP library modules.
 *
 * ## What it configures:
 * - Applies the `com.android.library` plugin.
 * - Applies the `org.jetbrains.kotlin.multiplatform` plugin.
 * - Applies the `com.android.lint` plugin.
 * - Configures common Android settings using a shared function `configureAndroidLibrary`.
 *   - Sets `compileSdk` and `minSdk`.
 *   - Configures Java 11 compatibility.
 *
 * ## Usage
 * Apply to a KMP library module's `build.gradle.kts`:
 * ```kotlin
 * plugins {
 *     id("convention.android.kmp.library")
 * }
 *
 * android {
 *     namespace = "xyz.aranhapreta.rickandmorty.feature.mylib"
 * }
 * ```
 *
 * ## Important Notes
 * - This plugin is often used in combination with `convention.kotlin.multiplatform`.
 * - You **must** set `android.namespace` in your module.
 *
 * @see AndroidConfiguration.kt for the shared Android configuration.
 */
class AndroidLibraryKmpConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply(libs.pluginId("androidLibrary"))
                apply(libs.pluginId("kotlinMultiplatform"))
            }

            extensions.configure<LibraryExtension> {
                configureAndroidLibrary(this)
            }
        }
    }
}
