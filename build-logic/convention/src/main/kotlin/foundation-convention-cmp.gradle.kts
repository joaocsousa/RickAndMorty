/**
 * Convention plugin for Android KMP libraries with Compose Multiplatform UI.
 */
import org.jetbrains.compose.ComposeExtension
import utils.configureJavaVersion

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
    id("org.jetbrains.kotlin.plugin.compose")
}

val composeDeps = extensions.getByType<ComposeExtension>().dependencies

kotlin {
    configureJavaVersion()
    sourceSets.commonMain.dependencies {
        implementation(composeDeps.runtime)
        implementation(composeDeps.foundation)
        implementation(composeDeps.material3)
        implementation(composeDeps.ui)
        implementation(composeDeps.components.resources)
        implementation(composeDeps.components.uiToolingPreview)
    }
}
