import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.jlleitschuh.gradle.ktlint.KtlintExtension
import org.jlleitschuh.gradle.ktlint.reporter.ReporterType

/**
 * Convention plugin for applying ktlint to a module.
 *
 * ## What it configures:
 * - Applies the `org.jlleitschuh.gradle.ktlint` plugin.
 * - Adds the `ktlint-compose-rules` as a dependency.
 * - Configures ktlint with the following settings:
 *   - Version: 1.8.0
 *   - Android mode enabled.
 *   - Reports in PLAIN and CHECKSTYLE formats.
 *   - Ignores failures are disabled (will fail the build).
 * - Sets up a filter to exclude common non-source directories.
 *
 * ## Usage
 * This plugin is typically applied as part of `convention.kmp.feature`.
 * To apply it manually:
 * ```kotlin
 * plugins {
 *     id("convention.ktlint")
 * }
 * ```
 */
class KtlintConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("org.jlleitschuh.gradle.ktlint")

            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

            dependencies {
                add("ktlintRuleset", libs.findLibrary("ktlint-compose-rules").get())
            }

            extensions.configure<KtlintExtension> {
                version.set("1.8.0")
                debug.set(true)
                android.set(true)
                ignoreFailures.set(false)
                reporters {
                    reporter(ReporterType.PLAIN)
                    reporter(ReporterType.CHECKSTYLE)
                }
                filter {
                    exclude("**/build/**")
                    exclude("**/generated/**")
                    exclude("**/.gradle/**")
                    exclude("**/gradle/wrapper/**")
                    exclude("*.gradle.kts")
                }
            }
        }
    }
}
