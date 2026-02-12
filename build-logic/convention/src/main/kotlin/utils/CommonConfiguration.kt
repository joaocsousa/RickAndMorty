package utils

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.KotlinMultiplatformAndroidLibraryTarget
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

internal fun KotlinMultiplatformExtension.configureJavaVersion() {
    jvmToolchain(17)
}

internal fun Project.configureDependencies(
    extension: KotlinMultiplatformExtension,
    compose: Boolean
) {
    extension.apply {
        sourceSets.commonMain.dependencies {
            implementation(libs.findLibrary("kermit").get())
            implementation(libs.findLibrary("koin").get())
            implementation(libs.findLibrary("coroutines").get())
            if (compose) {
                implementation(libs.findLibrary("koin-compose").get())
            }
        }
    }
}

internal fun KotlinMultiplatformExtension.iosTargets() = listOf(
    iosArm64(),
    iosSimulatorArm64()
)

internal fun Project.configureAndroidKmpLibrary(extension: KotlinMultiplatformAndroidLibraryTarget) {
    extension.apply {
        compileSdk = libs.version("android-compileSdk").toInt()
        minSdk = libs.version("android-minSdk").toInt()
    }
}

/**
 * Configures the common Android settings for application modules.
 */
fun Project.configureAndroidApplication(extension: ApplicationExtension) {
    extension.apply {
        compileSdk = libs.version("android-compileSdk").toInt()

        defaultConfig {
            applicationId = "xyz.aranhapreta.rickAndMorty"
            minSdk = libs.version("android-minSdk").toInt()
            targetSdk = libs.version("android-targetSdk").toInt()
            versionCode = 1
            versionName = "1.0"
        }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_17
            targetCompatibility = JavaVersion.VERSION_17
        }

        packaging {
            resources {
                excludes += "/META-INF/{AL2.0,LGPL2.1}"
            }
        }
    }
}
