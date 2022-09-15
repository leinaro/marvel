package com.leinaro.buildsrc.plugins

import AndroidConfig
import Dependencies
import Versions
import com.android.build.gradle.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.the

class LeinaroAndroidLibraryPlugin : Plugin<Project> {
  override fun apply(target: Project) {
    target.configureAndroidPlugins()

    target.the<LibraryExtension>().apply {
      compileSdk = AndroidConfig.compileSDK
      buildFeatures {
        compose = true
      }
      composeOptions {
        kotlinCompilerExtensionVersion = Versions.kotlinCompilerExtension
      }
      defaultConfig {
        minSdk = AndroidConfig.minSdk
        targetSdk = AndroidConfig.targerSdk
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
      }
    }
  }
}

private fun Project.configureAndroidPlugins() {
  plugins.apply("com.android.library")
}

private fun Project.configureAndroidDependencies() = dependencies {
  add("implementation", Dependencies.androidxCoreKtx)
  add("implementation", Dependencies.androidxAppCompat)
  add("implementation", Dependencies.androidxCoreKtx)
}