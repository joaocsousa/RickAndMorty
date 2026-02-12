import utils.configureAndroidKmpLibrary
import utils.configureJavaVersion
import utils.iosTargets

/**
 * Convention plugin for Android Kotlin Multiplatform libraries.
 *
 * Configures:
 * - com.android.kotlin.multiplatform.library (Android + KMP)
 * - Android library settings
 * - iOS targets
 *
 * Note: Consuming modules must specify `kotlin.androidLibrary.namespace`.
 */

plugins {
    kotlin("multiplatform")
    id("com.android.kotlin.multiplatform.library")
}

kotlin {
    configureJavaVersion()
    applyDefaultHierarchyTemplate()

    androidLibrary {
        configureAndroidKmpLibrary(this)
        experimentalProperties["android.experimental.kmp.enableAndroidResources"] = true
    }

    iosTargets().forEach {
        it.binaries.framework {
            baseName = project.name
            isStatic = true
        }
    }
}
