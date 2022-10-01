repositories {
  mavenCentral()
  gradlePluginPortal()
  google()
  maven {
    url = uri("https://plugins.gradle.org/m2/")
  }
}
plugins {
  `kotlin-dsl`
}

dependencies {
  implementation(kotlin("gradle-plugin", "1.7.10"))
  implementation(kotlin("reflect", "1.7.10"))
  implementation(kotlin("stdlib-jdk8", "1.7.10"))
  implementation("com.android.tools.build:gradle:7.3.0")
  implementation(gradleApi())
  implementation(localGroovy())
  implementation("com.squareup:javapoet:1.13.0") // <-- added this
}