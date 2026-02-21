plugins {
    id("composite-convention-cmp-library")
}

kotlin {
    androidLibrary {
        namespace = "xyz.aranhapreta.rickAndMorty.composeApp"
    }
    sourceSets {
        commonMain.dependencies {
            implementation(projects.common.theme)
            implementation(projects.common.di.compose)
            implementation(projects.feature.characters.presentation)
            implementation(projects.feature.locations.presentation)
            implementation(projects.feature.episodes.presentation)
            implementation(libs.androidx.navigation)
            implementation(libs.coil)
            implementation(libs.coil.ktor)
            implementation(libs.icons)
            implementation(libs.haze)
            implementation(libs.haze.materials)
        }
    }
}
