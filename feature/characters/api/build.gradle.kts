plugins {
    id("composite-convention-kmp-library")
}

kotlin {
    androidLibrary {
        namespace = "xyz.aranhapreta.rickAndMorty.feature.characters.api"
    }
    sourceSets {
        commonMain {
            dependencies {
                implementation(projects.common.api.core)
                api(projects.common.api.models.`in`)
                implementation(libs.ktor.client.core)
            }
        }
    }
}