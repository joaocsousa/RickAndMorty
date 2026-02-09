plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidKotlinMultiplatformLibrary)
    alias(libs.plugins.serialization)
    alias(libs.plugins.androidLint)
}

kotlin {

    androidLibrary {
        namespace = "xyz.aranhapreta.rickAndMorty.api.models.in"
        compileSdk = 35
        minSdk = 24
    }

    listOf(
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "apiModelsInKit"
        }
    }

    sourceSets {
        commonMain {
            dependencies {
                implementation(libs.kotlin.serialization)
            }
        }
        androidMain
        iosMain
    }
}