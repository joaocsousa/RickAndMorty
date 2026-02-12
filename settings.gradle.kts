rootProject.name = "RickAndMorty"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
    includeBuild("build-logic")
}

dependencyResolutionManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
    }
}

plugins {
    id("org.jetbrains.kotlinx.kover.aggregation") version "0.9.7"
}

kover {
    enableCoverage()
}

include(":composeApp")

// feature - characters
include(":feature:characters:api")
include(":feature:characters:usecases")
include(":feature:characters:repositories")
include(":feature:characters:entities")
include(":feature:characters:presentation")

// feature - episodes
include(":feature:episodes:presentation")

// feature - locations
include(":feature:locations:presentation")

// common
include(":common:api:core")
include(":common:api:models:in")
include(":common:theme")
include(":common:database")
include(":common:di:compose")
include(":common:di:modules")
