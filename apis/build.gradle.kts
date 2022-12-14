import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
import org.jetbrains.kotlin.konan.properties.propertyString

plugins {
  id("leinaro-kotlin-library")
  id("org.jetbrains.kotlin.android")
}

val apiKey: String = gradleLocalProperties(rootDir).propertyString("marvel.apiKey").orEmpty()
val privateKey: String =
  gradleLocalProperties(rootDir).propertyString("marvel.privateKey").orEmpty()

android {
  namespace = "com.leinaro.apis"

  defaultConfig {
    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    consumerProguardFiles("consumer-rules.pro")
    buildConfigField("String", "marvel_apiKey", apiKey)
    buildConfigField("String", "marvel_privateKey", privateKey)
  }

  buildTypes {
    release {
      isMinifyEnabled = false
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
    }
  }
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
  }
  kotlinOptions {
    jvmTarget = "1.8"
  }
}

dependencies {

  // Retrofit
  implementation("com.squareup.retrofit2:retrofit:2.9.0")
  implementation("com.squareup.retrofit2:converter-gson:2.9.0")
  implementation("com.squareup.okhttp3:okhttp:4.10.0")
  implementation("com.squareup.okhttp3:logging-interceptor:4.10.0")

  androidTestImplementation("androidx.test.ext:junit:1.1.3")
  androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
}

extra.set(
  JacocoCoverage.coverageDataExtra,
  CoverageTaskParam(buildDirectory = buildDir)
)