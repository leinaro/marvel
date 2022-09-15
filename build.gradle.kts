// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
  repositories {
    google()
    mavenCentral()
    maven("https://plugins.gradle.org/m2/")
  }
  dependencies {
    classpath("com.android.tools.build:gradle:7.2.2")
    classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.21")
    classpath("com.google.dagger:hilt-android-gradle-plugin:2.43.2")
  }
}

allprojects {
  repositories {
    google()
    mavenCentral()
  }
}
/*
fun subprojectsInclude(vararg includes: String): Set<File> {
  return subprojects.flatMap {
    fileTree(it.buildDir) {
      include(*includes)
    }.files
  }.toSet()
}*/
/*
plugins {
  id("com.android.application") version "7.1.3" apply false
  id("com.android.library") version "7.1.3" apply false
  id("org.jetbrains.kotlin.android") version "1.7.10" apply false
  id("org.jetbrains.kotlin.jvm") version "1.7.10" apply false
}*/