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
                implementation(projects.common.api)
                implementation(projects.common.database)
                implementation(projects.feature.characters.data)
                implementation(projects.feature.characters.domain)
                implementation(projects.feature.characters.presentation)
                implementation(projects.feature.locations.presentation)
                implementation(projects.feature.episodes.presentation)
            }
        }
    }
}