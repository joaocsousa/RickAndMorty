plugins {
    id("composite-convention-cmp-library")
}

kotlin {

    androidLibrary {
        namespace = "xyz.aranhapreta.rickAndMorty.feature.characters.presentation"
    }

    sourceSets {
        commonMain {
            dependencies {
                implementation(projects.feature.characters.usecases)
                implementation(projects.common.theme)
            }
        }
    }
}