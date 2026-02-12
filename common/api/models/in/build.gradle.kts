plugins {
    id("composite-convention-kmp-library")
    id("foundation-convention-serialization")
}

kotlin {

    androidLibrary {
        namespace = "xyz.aranhapreta.rickAndMorty.api.models.in"
    }

    sourceSets {
        commonMain {
            dependencies {
                implementation(libs.kotlin.serialization)
            }
        }
    }
}