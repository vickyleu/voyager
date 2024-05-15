@file:OptIn(ExperimentalKotlinGradlePluginApi::class)

import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    kotlin("multiplatform")
    id("com.android.application")
    id("org.jetbrains.compose")
    id("org.jetbrains.kotlin.plugin.compose")
}

kotlin {
    androidTarget {
        compilerOptions{
            jvmTarget.set(JvmTarget.fromTarget(libs.versions.jvmTarget.get()))
        }
    }
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
            isStatic = true
        }
    }

    sourceSets {
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.animation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.uiToolingPreview)
            implementation(compose.components.resources)



            //put your multiplatform dependencies here
            implementation(projects.voyagerCore)
            implementation(projects.voyagerNavigator)
            implementation(projects.voyagerScreenmodel)
            implementation(projects.voyagerTabNavigator)
//
//            implementation(libs.voyager.navigator)
//            implementation(libs.voyager.screenmodel)
//            implementation(libs.voyager.navigator.bottomsheet)
//            implementation(libs.voyager.navigator.tab)
//            implementation(libs.voyager.transitions)

            implementation(project.dependencies.platform(libs.compose.bom))
            implementation(project.dependencies.platform(libs.koin.bom))
            implementation(libs.koin.core)

            implementation(libs.compose.views)

        }
        androidMain.dependencies {
            implementation(libs.koin.android)
            implementation(libs.compose.material3)
            implementation(libs.androidx.activity.compose )
        }
    }
}

android {
    namespace = "com.example.voyager_example"
    compileSdk = 34
    defaultConfig {
        applicationId = "com.example.voyager_example.android"
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        compose = true
    }
//    composeOptions {
//        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
//    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.toVersion(libs.versions.jvmTarget.get())
        targetCompatibility = JavaVersion.toVersion(libs.versions.jvmTarget.get())
    }
    dependencies{
    }
}
