import utils.configureDependencies
import utils.libs

/**
 * Convention plugin for Android KMP libraries with Compose Multiplatform UI.
 *
 * Configures:
 * - foundation.convention.cmp
 * - foundation.convention.kmp.library
 * - foundation.convention.ktlint
 */

plugins {
    id("foundation-convention-cmp")
    id("foundation-convention-kmp-library")
    id("foundation-convention-ktlint")
}

kotlin {
    configureDependencies(extension = this, compose = true)
    sourceSets.commonMain.dependencies {
        implementation(libs.findLibrary("koin-compose").get())
        implementation(libs.findLibrary("coil").get())
    }
}

dependencies {
    androidRuntimeClasspath(libs.findLibrary("compose-tooling").get())
}

