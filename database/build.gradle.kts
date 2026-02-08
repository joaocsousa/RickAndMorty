plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidKotlinMultiplatformLibrary)
    alias(libs.plugins.sqldelight)
    alias(libs.plugins.androidLint)
}

kotlin {

    jvmToolchain(17)

    androidLibrary {
        namespace = "xyz.aranhapreta.rickAndMorty.database"
        compileSdk = 35
        minSdk = 24
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "databaseKit"
        }
    }

    sourceSets {
        androidUnitTest
        commonMain {
            dependencies {
                implementation(libs.coroutines)
                implementation(libs.sqlite.coroutines)
                implementation(libs.koin)
            }
        }

        androidMain {
            dependencies {
                implementation(libs.sqlite.android)
            }
        }

        iosTest
        iosMain {
            dependencies {
                implementation(libs.sqlite.native)
            }
        }
    }

    sqldelight {
        databases {
            create("Database") {
                packageName = "xyz.aranhapreta.rickAndMorty.database"
            }
        }
    }
}
