plugins {
    id("composite-convention-kmp-library")
}

kotlin {
    androidLibrary {
        namespace = "xyz.aranhapreta.rickAndMorty.feature.characters.data"
    }
    sourceSets {
        commonMain {
            dependencies {
                implementation(projects.common.api)
                implementation(projects.common.database)
                implementation(projects.feature.characters.domain)
                implementation(libs.androidx.paging.common)
            }
        }
    }
}