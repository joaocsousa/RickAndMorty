plugins {
    id("composite-convention-kmp-library")
    alias(libs.plugins.sqldelight)
}

kotlin {

    androidLibrary {
        namespace = "xyz.aranhapreta.rickAndMorty.database"
    }

    sourceSets {
        commonMain {
            dependencies {
                implementation(libs.sqlite.coroutines)
                implementation(libs.androidx.paging.common)
            }
        }

        androidMain {
            dependencies {
                implementation(libs.sqlite.android)
            }
        }

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
