import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

/**
 * Convention plugin for setting up Kotlin Multiplatform targets.
 *
 * ## What it configures:
 * - Applies the `org.jetbrains.kotlin.multiplatform` plugin.
 * - Applies the default KMP hierarchy template.
 * - Configures Android and iOS targets.
 *   - The Android target is configured reactively when an Android plugin is applied.
 *   - iOS targets include `iosArm64` and `iosSimulatorArm64`.
 * - Configures a static framework for iOS targets with a base name derived from the module name.
 *
 * ## Usage
 * This plugin is typically applied as part of a composite plugin like `convention.kmp.feature`.
 * However, it can be used directly:
 * ```kotlin
 * plugins {
 *     id("convention.kotlin.multiplatform")
 *     // You must also apply an Android plugin (e.g., convention.android.kmp.library)
 * }
 * ```
 *
 * ## Important Notes
 * - The `androidTarget` is only configured if an Android plugin (application or library) is present.
 * - The iOS framework `baseName` defaults to the module's name.
 */
class KotlinMultiplatformConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply(libs.pluginId("kotlinMultiplatform"))
            }

            extensions.configure<KotlinMultiplatformExtension> {
                applyDefaultHierarchyTemplate()

                sourceSets.getByName("commonMain") {
                    dependencies {
                        api(libs.findLibrary("kermit").get())
                        api(libs.findLibrary("koin").get())
                    }
                }

                // Reactively configure androidTarget if/when android plugin is applied
                pluginManager.withPlugin(libs.pluginId("androidKotlinMultiplatformLibrary")) {
                    androidTarget {
                        compilerOptions {
                            jvmTarget.set(JvmTarget.JVM_11)
                        }
                    }
                }
                
                pluginManager.withPlugin(libs.pluginId("androidApplication")) {
                     androidTarget {
                        compilerOptions {
                            jvmTarget.set(JvmTarget.JVM_11)
                        }
                    }
                }

                listOf(
                    iosArm64(),
                    iosSimulatorArm64()
                ).forEach {
                    it.binaries.framework {
                        baseName = target.name
                        isStatic = true
                    }
                }
            }
        }
    }
}
