plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidKotlinMultiplatformLibrary)
    alias(libs.plugins.androidLint)
}

kotlin {

    androidLibrary {
        namespace = "xyz.aranhapreta.rickAndMorty.feature.characters.api"
        compileSdk = libs.versions.android.compileSdk.get().toInt()
        minSdk = libs.versions.android.minSdk.get().toInt()
    }

    listOf(
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "featureCharactersApiKit"
        }
    }

    sourceSets {
        commonMain {
            dependencies {
                implementation(projects.api.core)
                api(projects.api.models.`in`)
                implementation(libs.ktor.client.core)
                implementation(libs.koin)
                implementation(libs.kermit)
            }
        }

        androidMain
        iosMain
    }
}