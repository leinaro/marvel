plugins {
  id("leinaro-kotlin-library")
  id("org.jetbrains.kotlin.android")
}

hilt {
  enableExperimentalClasspathAggregation = true
}

android {
  namespace = "com.leinaro.apis"
  compileSdk = 32

  defaultConfig {
    minSdk = 23
    targetSdk = 32

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

  // Retrofit
  implementation("com.squareup.retrofit2:retrofit:2.9.0")
  implementation("com.squareup.retrofit2:converter-gson:2.9.0")

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