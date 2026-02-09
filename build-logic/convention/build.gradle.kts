plugins {
    `kotlin-dsl`
}

group = "xyz.aranhapreta.rickAndMorty.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.android.tools.common)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.compose.gradlePlugin)
    compileOnly(libs.compose.compiler.gradlePlugin)
    compileOnly(libs.ksp.gradlePlugin)

    implementation(libs.ktlint.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("android-application") {
            id = "convention.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("androidKmpLibrary") {
            id = "convention.android.kmp.library"
            implementationClass = "AndroidLibraryKmpConventionPlugin"
        }
        register("androidLibrary") {
            id = "convention.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("kotlinMultiplatform") {
            id = "convention.kotlin.multiplatform"
            implementationClass = "KotlinMultiplatformConventionPlugin"
        }
        register("composeMultiplatform") {
            id = "convention.compose.multiplatform"
            implementationClass = "ComposeMultiplatformConventionPlugin"
        }
        register("composeAndroid") {
            id = "convention.compose.android"
            implementationClass = "ComposeAndroidConventionPlugin"
        }
        register("ktlint") {
            id = "convention.ktlint"
            implementationClass = "KtlintConventionPlugin"
        }
        register("kmpFeature") {
            id = "convention.kmp.feature"
            implementationClass = "KmpFeatureConventionPlugin"
        }
    }
}
