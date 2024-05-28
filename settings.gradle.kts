@file:Suppress("UnstableApiUsage")

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

// mother fucker, WorkQueue error throw in Iguana
gradle.startParameter.excludedTaskNames.addAll(listOf(
    ":buildSrc:testClasses",
))

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.PREFER_PROJECT)
    repositories {
        google()
        mavenCentral()
        maven { setUrl("https://repo.maven.apache.org/maven2")}
        maven(url = "https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}

include(":shared")


include(
    ":samples:android",
    ":samples:multiplatform",

    ":samples:multi-module:app",
    ":samples:multi-module:navigation",
    ":samples:multi-module:feature-home",
    ":samples:multi-module:feature-posts",

    ":voyager-core",
    ":voyager-screenmodel",
    ":voyager-navigator",
    ":voyager-tab-navigator",
    ":voyager-lifecycle-kmp",
    ":voyager-bottom-sheet-navigator",
    ":voyager-transitions",
    ":voyager-hilt",
    ":voyager-kodein",
    ":voyager-koin",
    ":voyager-rxjava",
    ":voyager-livedata"
)


//plugins {
//    id("com.dropbox.focus") version "0.4.0"
//}
//
//configure<com.dropbox.focus.FocusExtension> {
//    allSettingsFileName.set("includes.gradle.kts")
//    focusFileName.set(".focus")
//}
