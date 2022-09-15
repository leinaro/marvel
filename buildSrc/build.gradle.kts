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
  implementation(kotlin("gradle-plugin", "1.6.21"))
  implementation(kotlin("reflect", "1.6.21"))
  implementation(kotlin("stdlib-jdk8", "1.6.21"))
  implementation("com.android.tools.build:gradle:7.2.2")
  implementation(gradleApi())
  implementation(localGroovy())
}