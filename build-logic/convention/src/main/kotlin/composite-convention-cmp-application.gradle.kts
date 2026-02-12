import utils.configureDependencies
import utils.iosTargets

/**
 * Convention plugin for the main Android application module.
 *
 * Configures:
 * - com.android.application
 * - org.jetbrains.kotlin.multiplatform
 * - org.jetbrains.compose
 * - convention.ktlint
 * - Android application settings
 * - KMP targets (Android, iOS)
 * - Compose common dependencies
 */

plugins {
    id("foundation-convention-android-application")
    id("foundation-convention-cmp")
    id("foundation-convention-ktlint")
}

val cmpApplication = extensions.create("cmpApplication", CmpApplicationExtension::class.java)

kotlin {
    androidTarget()

    iosTargets().forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = cmpApplication.baseName.get()
            isStatic = true
            freeCompilerArgs += "-Xbinary=bundleId=${cmpApplication.iosBundleId.get()}"
        }
    }

    configureDependencies(extension = kotlin, compose = true)
}

abstract class CmpApplicationExtension(project: Project) {
    private val objects = project.objects

    /**
     * The base name for the iOS framework.
     * Defaults to the project name.
     */
    val baseName: Property<String> = objects.property(String::class.java)
        .convention(project.name)

    /**
     * The bundle ID for the iOS application.
     * Defaults to "${project.group}.${project.name}".
     */
    val iosBundleId: Property<String> = objects.property(String::class.java)
        .convention("${project.group}.${project.name}")
}
