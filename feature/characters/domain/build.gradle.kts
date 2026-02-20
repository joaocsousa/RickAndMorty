plugins {
    id("composite-convention-kmp-library")
}

kotlin {
    androidLibrary {
        namespace = "xyz.aranhapreta.rickAndMorty.feature.characters.domain"
    }
    sourceSets {
        commonMain {
            dependencies {
                implementation(libs.androidx.paging.common)
            }
        }
    }
}