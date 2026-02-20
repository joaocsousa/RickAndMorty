import utils.configureAndroidApplication

plugins {
    id("composite-convention-cmp-application")
}

private val BundleId = "xyz.aranhapreta.rickAndMorty"

cmpApplication {
    baseName.set("RickAndMorty")
    iosBundleId.set(BundleId)
}

kotlin {
    sourceSets {
        androidMain.dependencies {
            implementation(libs.androidx.activity.compose)
        }
        commonMain.dependencies {
            implementation(projects.common.theme)
            implementation(projects.common.di.compose)
            implementation(projects.common.database)
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

android {
    namespace = BundleId
    configureAndroidApplication(this)
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}
