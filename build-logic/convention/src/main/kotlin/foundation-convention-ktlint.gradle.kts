import org.jlleitschuh.gradle.ktlint.KtlintExtension
import org.jlleitschuh.gradle.ktlint.reporter.ReporterType
import utils.libs

/**
 * Convention plugin for applying ktlint to a module.
 *
 * Configures:
 * - org.jlleitschuh.gradle.ktlint plugin
 * - ktlint-compose-rules
 * - Standard excludes and reporting settings
 */
plugins {
    id("org.jlleitschuh.gradle.ktlint")
}

dependencies {
    "ktlintRuleset"(libs.findLibrary("ktlint-compose-rules").get())
}

configure<KtlintExtension> {
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
