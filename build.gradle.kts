buildscript {
  repositories {
    google()
    mavenCentral()
  }
  dependencies {
    classpath("com.android.tools.build:gradle:7.2.2")
  }
} // Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
  alias(libs.plugins.android.application) apply false
  alias(libs.plugins.android.library) apply false
  alias(libs.plugins.kotlin.android) apply false
  alias(libs.plugins.kotlin.kapt) apply false
  //alias(libs.plugins.kotlin.kotlin - jvm) apply false
  id("org.jetbrains.kotlin.jvm") version "1.7.10" apply false
}