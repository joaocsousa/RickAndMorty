import utils.libs

plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(libs.findLibrary("kotlin-serialization").get())
            }
        }
    }
}