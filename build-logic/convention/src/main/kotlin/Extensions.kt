import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType

/**
 * Provides a type-safe accessor for the `libs` version catalog.
 *
 * This extension property allows convention plugins to access the version catalog
 * in a concise and readable way.
 *
 * ## Example Usage
 * ```kotlin
 * // In a convention plugin
 * val composeBom = libs.findLibrary("androidx-compose-bom").get()
 * dependencies {
 *     "implementation"(platform(composeBom))
 * }
 * ```
 */
internal val Project.libs: VersionCatalog
    get() = extensions.getByType<VersionCatalogsExtension>().named("libs")

/**
 * Retrieves a version string from the version catalog by its alias.
 *
 * @param alias The alias of the version in the `[versions]` section of `libs.versions.toml`.
 * @return The required version string.
 * @throws java.util.NoSuchElementException if the alias is not found.
 *
 * ## Example Usage
 * ```kotlin
 * // In a convention plugin
 * val compileSdk = libs.version("android-compileSdk").toInt()
 * ```
 */
internal fun VersionCatalog.version(alias: String): String =
    findVersion(alias).get().requiredVersion

/**
 * Retrieves a plugin ID from the version catalog by its alias.
 *
 * @param alias The alias of the plugin in the `[plugins]` section of `libs.versions.toml`.
 * @return The fully qualified plugin ID.
 * @throws java.util.NoSuchElementException if the alias is not found.
 *
 * ## Example Usage
 * ```kotlin
 * // In a convention plugin
 * pluginManager.apply(libs.pluginId("android-library"))
 * ```
 */
internal fun VersionCatalog.pluginId(alias: String): String =
    findPlugin(alias).get().get().pluginId
