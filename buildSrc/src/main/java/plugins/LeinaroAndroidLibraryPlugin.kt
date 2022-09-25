package plugins

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
    target.configureAndroidDependencies()

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
  plugins.apply("org.jetbrains.kotlin.android")
  plugins.apply("dagger.hilt.android.plugin")
  plugins.apply("kotlin-android")
  plugins.apply("kotlin-kapt")
}

private fun Project.configureAndroidDependencies() = dependencies {
  add("implementation", Dependencies.androidxCoreKtx)
  add("implementation", Dependencies.androidxAppCompat)
  addComposeDependencies()

  // Ktx
  add("implementation", Dependencies.androidxActivityKtx)

  addHiltDependencies()

  // Coil
  add("implementation", Dependencies.coil)

  addTestDependencies()
}