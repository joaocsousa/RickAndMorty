import com.android.build.api.dsl.ApplicationExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

/**
 * Convention plugin for Android application modules.
 *
 * This plugin applies a standard configuration for Android application modules.
 *
 * ## What it configures:
 * - Applies the `com.android.application` plugin.
 * - Applies the `com.android.lint` plugin.
 * - Configures Android settings using a shared function `configureAndroidApplication`.
 *   - Sets `compileSdk`, `minSdk`, `targetSdk`.
 *   - Sets default `versionCode` and `versionName`.
 *   - Configures Java 11 compatibility.
 *   - Configures packaging options.
 *
 * ## Usage
 * Apply to the main Android app module's `build.gradle.kts`:
 * ```kotlin
 * plugins {
 *     id("convention.android.application")
 * }
 *
 * android {
 *     namespace = "xyz.aranhapreta.rickandmorty.app"
 *     defaultConfig {
 *         applicationId = "xyz.aranhapreta.rickandmorty.app"
 *     }
 * }
 * ```
 *
 * ## Important Notes
 * - You **must** set `android.namespace` in your module.
 * - You **must** set `android.defaultConfig.applicationId` in your module.
 *
 * @see AndroidConfiguration.kt for the shared Android configuration.
 */
class AndroidApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply(libs.pluginId("androidApplication"))
            }

            extensions.configure<ApplicationExtension> {
                configureAndroidApplication(this)
            }
        }
    }
}
