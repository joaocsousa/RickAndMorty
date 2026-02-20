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
                implementation(projects.feature.characters.domain)
                implementation(projects.common.theme)
                implementation(libs.androidx.paging.compose)
                implementation(libs.compose.icons.fontAwesome)
            }
        }
    }
}