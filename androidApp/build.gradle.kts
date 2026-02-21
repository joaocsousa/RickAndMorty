plugins {
    id("composite-convention-android-app")
    alias(libs.plugins.composeCompiler)
}

android {
    namespace = "xyz.aranhapreta.rickAndMorty"
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(libs.androidx.core)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.activity.compose)
    implementation(libs.material)
    implementation(projects.common.database)
    implementation(projects.composeApp)
}