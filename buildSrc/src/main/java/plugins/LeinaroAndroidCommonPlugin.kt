package plugins

import AndroidConfig
import Dependencies
import Versions
import com.android.build.gradle.BaseExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.the

class LeinaroAndroidCommonPlugin : Plugin<Project> {
  override fun apply(target: Project) {
    target.configureAndroidPlugins()
    target.configureAndroidDependencies()

    target.the<BaseExtension>().apply {
      compileSdkVersion(AndroidConfig.compileSDK)

      buildFeatures.compose = true
      defaultConfig {
        minSdk = AndroidConfig.minSdk
        targetSdk = AndroidConfig.targerSdk
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
      }
      compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
      }
      composeOptions.kotlinCompilerExtensionVersion = Versions.kotlinCompilerExtension
    }
  }
}

private fun Project.configureAndroidPlugins() {
  plugins.apply("com.android.application")
  plugins.apply("org.jetbrains.kotlin.android")
  plugins.apply("dagger.hilt.android.plugin")
  plugins.apply("kotlin-android")
  plugins.apply("kotlin-kapt")
  plugins.apply("de.mannodermaus.android-junit5")
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