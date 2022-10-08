package plugins

import AndroidConfig
import Dependencies
import com.android.build.gradle.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.the

class LeinaroKotlinLibraryPlugin : Plugin<Project> {
  override fun apply(target: Project) {
    target.configureAndroidPlugins()
    target.configureAndroidDependencies()

    target.the<LibraryExtension>().apply {
      compileSdk = AndroidConfig.compileSDK

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
  plugins.apply("jacoco")
  plugins.apply("plugins.jacoco-report")
}

private fun Project.configureAndroidDependencies() = dependencies {
  add("implementation", Dependencies.androidxCoreKtx)
  add("implementation", Dependencies.androidxAppCompat)

  // Ktx
  add("implementation", Dependencies.androidxActivityKtx)

  // Loggin Interceptors
  //add("implementation", "com.squareup.okhttp3:logging-interceptor:4.2.1")
  add("debugImplementation", "com.github.chuckerteam.chucker:library:3.5.2")
  add("releaseImplementation", "com.github.chuckerteam.chucker:library-no-op:3.5.2")

  addHiltDependencies()
  addTestDependencies()
}