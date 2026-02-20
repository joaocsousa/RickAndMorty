plugins {
    id("composite-convention-kmp-library")
    id("foundation-convention-serialization")
}

kotlin {
    androidLibrary {
        namespace = "xyz.aranhapreta.rickAndMorty.api"
    }
    sourceSets {
        commonMain {
            dependencies {
                implementation(libs.ktor.client.core)
                implementation(libs.ktor.client.logging)
                implementation(libs.ktor.client.content.negotiation)
                implementation(libs.ktor.serialization.kotlinx.json)
            }
        }

        androidMain {
            dependencies {
                implementation(libs.ktor.client.okhttp)
            }
        }

        iosMain {
            dependencies {
                implementation(libs.ktor.client.darwin)
            }
        }
    }
}
