plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")

    id("com.android.library")
    id("org.jetbrains.compose")
}

object Metadata {
    const val javaVersion = "11"
    val asJavaVersionEnum = JavaVersion.values().find { it.name.endsWith(javaVersion) }
    const val minSDK = 21
    const val maxSDK = 33

    const val iosDeploymentTarget = "14.1"

    const val namespace = "com.deathsdoor.colorfulsliders"
    const val module = "colorfulsliders"
    const val version = "0.1.0"
    const val description = "Elevate default sliders using Jetpack Compose with customizable dimensions, gradients, borders,and dynamic labels. Also, enjoy Kotlin Multiplatform compatibility for ColorfulIconSlider featuring emoji or any Composable thumbs."
    const val url = "https://github.com/Deaths-Door/colorfulsliders-compose-kmp"
}

kotlin {
    android {
        compilations.all {
            kotlinOptions {
                jvmTarget = Metadata.javaVersion
            }
        }
    }

    js(IR) {
        browser()
        nodejs()
        binaries.executable()
    }

    iosX64()
    iosArm64()
    iosSimulatorArm64()


    cocoapods {
        summary = Metadata.description
        homepage = Metadata.url
        version = Metadata.version
        ios.deploymentTarget = Metadata.iosDeploymentTarget
        framework {
            baseName = Metadata.module
        }
    }
    
    sourceSets {
        val commonMain by getting {
            dependencies {
                //Basic Jetpack compose
                implementation(compose.ui)
                implementation(compose.foundation)
                implementation(compose.material3)
                implementation(compose.runtime)
            }
        }
    }
}

android {
    namespace = Metadata.namespace
    compileSdk = Metadata.maxSDK

    defaultConfig.minSdk = Metadata.minSDK
    defaultConfig.targetSdk = Metadata.maxSDK

    compileOptions.sourceCompatibility = Metadata.asJavaVersionEnum
    compileOptions.targetCompatibility = Metadata.asJavaVersionEnum
}