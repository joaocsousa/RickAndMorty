plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidKotlinMultiplatformLibrary)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.androidLint)
}

kotlin {
    androidLibrary {
        namespace = "xyz.aranhapreta.rickAndMorty.common.di.compose"
        compileSdk = 35
        minSdk = 24
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "commonDiComposeKit"
        }
    }

    sourceSets {
        commonMain {
            dependencies {
                implementation(projects.common.di.modules)
                implementation(libs.koin.compose)
                implementation(libs.kermit)
                implementation(libs.kermit.koin)
                implementation(compose.runtime)
            }
        }
        androidMain
        iosMain
    }
}