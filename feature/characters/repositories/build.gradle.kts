plugins {
    id("composite-convention-kmp-library")
}

kotlin {

    androidLibrary {
        namespace = "xyz.aranhapreta.rickAndMorty.feature.characters.repositories"
    }

    sourceSets {
        commonMain {
            dependencies {
                implementation(projects.feature.characters.entities)
                implementation(projects.feature.characters.api)
                implementation(projects.common.database)
                implementation(libs.androidx.paging.common)
            }
        }
    }
}