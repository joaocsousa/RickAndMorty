/**
 * Convention plugin for the main Android application module.
 *
 * Configures:
 * - com.android.application
 * - Android application settings
 */
import utils.configureAndroidApplication

plugins {
    id("com.android.application")
}

android {
    configureAndroidApplication(this)
}
