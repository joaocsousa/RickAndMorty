plugins {
    id("composite-convention-cmp-library")
}

kotlin {
    androidLibrary {
        namespace = "xyz.aranhapreta.rickAndMorty.common.di.compose"
    }

    sourceSets {
        commonMain {
            dependencies {
                implementation(projects.common.di.modules)
                implementation(libs.kermit.koin)
            }
        }
    }
}