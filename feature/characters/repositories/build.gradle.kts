plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidKotlinMultiplatformLibrary)
    alias(libs.plugins.androidLint)
}

kotlin {

    // Target declarations - add or remove as needed below. These define
    // which platforms this KMP module supports.
    // See: https://kotlinlang.org/docs/multiplatform-discover-project.html#targets
    androidLibrary {
        namespace = "xyz.aranhapreta.rickAndMorty.feature.characters.repositories"
        compileSdk = 35
        minSdk = 24
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "featureCharactersRepositoriesKit"
        }
    }

    sourceSets {
        commonMain {
            dependencies {
                implementation(projects.feature.characters.entities)
                implementation(projects.feature.characters.api)
                implementation(projects.database)
                implementation(libs.koin)
                implementation(libs.kermit)
                implementation(libs.coroutines)
            }
        }

        androidMain
        iosMain
    }
}