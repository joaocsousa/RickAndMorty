plugins {
    id("composite-convention-kmp-library")
}

kotlin {
    androidLibrary {
        namespace = "xyz.aranhapreta.rickAndMorty.common.di.modules"
    }

    sourceSets {
        commonMain {
            dependencies {
                implementation(projects.common.api.core)
                implementation(projects.common.database)
                implementation(projects.feature.characters.api)
                implementation(projects.feature.characters.presentation)
                implementation(projects.feature.characters.repositories)
                implementation(projects.feature.characters.usecases)
                implementation(projects.feature.locations.presentation)
                implementation(projects.feature.episodes.presentation)
            }
        }
    }
}