import org.gradle.nativeplatform.platform.internal.DefaultNativePlatform
import java.util.Properties

plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("org.jetbrains.compose")
    id("org.jetbrains.kotlin.plugin.atomicfu")
    id("org.jetbrains.kotlin.plugin.compose")
}

setupModuleForComposeMultiplatform(fullyMultiplatform = true)

android {
    namespace = "cafe.adriel.voyager.core"
    defaultConfig {
        consumerProguardFiles("consumer-rules.pro")
    }
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            compileOnly(compose.runtime)
            compileOnly(compose.runtimeSaveable)
            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.atomicfu)
        }
        jvmTest.dependencies {
            implementation(libs.junit.api)
            runtimeOnly(libs.junit.engine)
        }
        androidMain.dependencies {
            implementation(libs.androidx.activity.compose)

            implementation(libs.lifecycle.runtime)
            implementation(libs.lifecycle.savedState)
            implementation(libs.lifecycle.viewModelKtx)
            implementation(libs.lifecycle.viewModelCompose)
        }
        val commonWebMain by getting {
            dependencies {
                implementation(libs.multiplatformUuid)
            }
        }
    }
}

kotlin {
    @Suppress("OPT_IN_USAGE")
    compilerOptions {
        freeCompilerArgs = listOf(
            "-Xexpect-actual-classes", // remove warnings for expect classes
            "-Xskip-prerelease-check",
            "-opt-in=kotlinx.cinterop.ExperimentalForeignApi",
            "-Xjsr305=strict",
            "-Xuse-k2",
            "-Xuse-k2=true",
        )
    }
    jvmToolchain {
        languageVersion.set(JavaLanguageVersion.of(libs.versions.jvmTarget.get()))
    }
}
