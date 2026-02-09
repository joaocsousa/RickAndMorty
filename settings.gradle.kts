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
    id("org.jetbrains.kotlinx.kover.aggregation") version "0.9.6"
}

kover {
    enableCoverage()
}

include(":composeApp")
include(":api:core")

include(":feature:characters:api")
include(":feature:characters:usecases")
include(":feature:characters:repositories")
include(":feature:characters:entities")
include(":feature:characters:presentation")

include(":feature:episodes:api:contract")
include(":feature:episodes:api:impl")
include(":feature:episodes:presentation")
include(":feature:episodes:usecases:contract")
include(":feature:episodes:usecases:impl")
include(":feature:episodes:entities")
include(":feature:locations:api:contract")
include(":feature:locations:api:impl")
include(":feature:locations:presentation")
include(":feature:locations:usecases:contract")
include(":feature:locations:usecases:impl")
include(":feature:locations:entities")
include(":api:models:in")
include(":theme")
include(":database")
include(":common:di:compose")
include(":common:di:modules")
include(":common:database-test")
