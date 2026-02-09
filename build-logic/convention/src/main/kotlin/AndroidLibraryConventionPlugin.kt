import com.android.build.gradle.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

/**
 * Convention plugin for **pure Android** library modules (i.e., not KMP).
 *
 * ## What it configures:
 * - Applies the `com.android.library` plugin.
 * - Applies the `com.android.lint` plugin.
 * - Configures common Android settings using a shared function `configureAndroidLibrary`.
 *   - Sets `compileSdk` and `minSdk`.
 *   - Configures Java 11 compatibility.
 *
 * ## Usage
 * Apply to an Android-only library module's `build.gradle.kts`:
 * ```kotlin
 * plugins {
 *     id("convention.android.library")
 * }
 *
 * android {
 *     namespace = "xyz.aranhapreta.rickandmorty.feature.myandroidlib"
 * }
 * ```
 *
 * @see AndroidConfiguration.kt for the shared Android configuration.
 */
class AndroidLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply(libs.pluginId("androidLibrary"))
            }

            extensions.configure<LibraryExtension> {
                configureAndroidLibrary(this)
            }
        }
    }
}
