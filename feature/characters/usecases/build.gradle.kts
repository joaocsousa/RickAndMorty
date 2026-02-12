plugins {
    id("composite-convention-kmp-library")
}

kotlin {
    androidLibrary {
        namespace = "xyz.aranhapreta.rickAndMorty.feature.characters.usecases"
    }

    sourceSets {
        commonMain {
            dependencies {
                api(projects.feature.characters.entities)
                implementation(projects.feature.characters.repositories)
            }
        }
    }
}