plugins {
  id("leinaro-android-library")
  kotlin("kapt")
  id("org.jetbrains.kotlin.android")

}

android {
  namespace = "com.leinaro.android_architecture_tools"

  defaultConfig {
    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    consumerProguardFiles("consumer-rules.pro")
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

  //implementation("androidx.core:core-ktx:1.7.0")
  //implementation("androidx.appcompat:appcompat:1.5.1")
 // implementation("com.google.android.material:material:1.6.1")

  //implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")
  //implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.5.1")
  //implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.5.1")
  //implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.1")
  //implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.5.1")

  //implementation("androidx.navigation:navigation-fragment-ktx:2.5.2")
  //implementation("androidx.navigation:navigation-ui-ktx:2.5.2")

  // testImplementation("junit:junit:4.13.2")
  androidTestImplementation("androidx.test.ext:junit:1.1.3")
  androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
}


extra.set(
  JacocoCoverage.coverageDataExtra,
  CoverageTaskParam(
    buildDir,
    "testDebugUnitTest",
    Coverage(
      instructions = 80.0,
      lines = 60.0,
      complexity = 60.0,
      methods = 60.0,
      classes = 60.0
    ),
    emptyList(),
    emptyList()
  )
)