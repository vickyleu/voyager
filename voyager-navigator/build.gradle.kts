import org.gradle.nativeplatform.platform.internal.DefaultNativePlatform
import java.util.Properties

plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("org.jetbrains.compose")
    id("org.jetbrains.kotlin.plugin.compose")
}

setupModuleForComposeMultiplatform(fullyMultiplatform = true)

android {
    namespace = "cafe.adriel.voyager.navigator"
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            api(detectProject(rootProject,":voyager-core"))
//            api(projects.voyagerCore)
            compileOnly(compose.runtime)
            compileOnly(compose.runtimeSaveable)
        }

        jvmTest.dependencies {
            implementation(libs.junit.api)
            runtimeOnly(libs.junit.engine)
        }

        androidMain.dependencies {
            implementation(libs.androidx.activity.compose)
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
        )
    }
    jvmToolchain {
        languageVersion.set(JavaLanguageVersion.of(libs.versions.jvmTarget.get()))
    }
}
