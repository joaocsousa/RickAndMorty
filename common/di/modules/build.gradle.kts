plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidKotlinMultiplatformLibrary)
    alias(libs.plugins.androidLint)
}

kotlin {

    androidLibrary {
        namespace = "xyz.aranhapreta.rickAndMorty.common.di.modules"
        compileSdk = 35
        minSdk = 24
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "commonDiModulesKit"
        }
    }

    sourceSets {
        commonMain {
            dependencies {
                implementation(libs.koin)
                implementation(projects.api.core)
                implementation(projects.database)
                implementation(projects.feature.locations.api.impl)
                implementation(projects.feature.episodes.api.impl)
                implementation(projects.feature.characters.api)
                implementation(projects.feature.characters.presentation)
                implementation(projects.feature.characters.repositories)
                implementation(projects.feature.characters.usecases)
                implementation(projects.feature.locations.presentation)
                implementation(projects.feature.episodes.presentation)
            }
        }

        androidMain
        iosMain
    }
}