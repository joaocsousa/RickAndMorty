import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.compose.ComposeExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

/**
 * Convention plugin for Compose Multiplatform UI modules.
 *
 * ## What it configures:
 * - Applies the `org.jetbrains.compose` plugin.
 * - Applies the `org.jetbrains.kotlin.plugin.compose` compiler plugin.
 * - Adds a default set of Compose dependencies to the `commonMain` source set.
 * - Adds the Android-specific `ui-tooling` dependency for debug builds.
 *
 * ## Usage
 * Apply to your shared UI modules:
 * ```kotlin
 * plugins {
 *     id("convention.kmp.feature") // Includes KMP and Android setup
 *     id("convention.compose.multiplatform")
 * }
 * ```
 */
class ComposeMultiplatformConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply(libs.pluginId("composeMultiplatform"))
                apply(libs.pluginId("composeCompiler"))
            }

            val compose = extensions.getByType<ComposeExtension>()
            extensions.configure<KotlinMultiplatformExtension> {
                sourceSets.getByName("commonMain").dependencies {
                    implementation(compose.dependencies.runtime)
                    implementation(compose.dependencies.foundation)
                    implementation(compose.dependencies.material3)
                    implementation(compose.dependencies.ui)
                    implementation(compose.dependencies.components.resources)
                    implementation(compose.dependencies.components.uiToolingPreview)
                }
            }

            dependencies {
                "debugImplementation"(compose.dependencies.uiTooling)
            }
        }
    }
}
