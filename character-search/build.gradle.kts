plugins {
  id("leinaro-android-library")
  kotlin("kapt")
  id("org.jetbrains.kotlin.android")
}

hilt {
  enableExperimentalClasspathAggregation = true
}

android {
  namespace = "com.leinaro.character_search"

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
    freeCompilerArgs += "-Xopt-in=kotlin.RequiresOptIn"
  }
}

dependencies {
  implementation(project(":core"))
  implementation(project(":domain"))

  androidTestImplementation("androidx.test.ext:junit:1.1.3")
  androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
}

extra.set(
  JacocoCoverage.coverageDataExtra,
  CoverageTaskParam(buildDirectory = buildDir)
)