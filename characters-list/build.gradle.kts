plugins {
  id("leinaro-android-library")
  kotlin("kapt")
  id("org.jetbrains.kotlin.android")
}

hilt {
  enableExperimentalClasspathAggregation = true
}

android {
  namespace = "com.leinaro.characters_list"

  defaultConfig {
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
  implementation(project(":architecture-tools"))
  implementation(project(":domain"))
  implementation(project(":data"))

//  implementation("com.google.android.material:material:1.6.1")
  //implementation("androidx.lifecycle:lifecycle-runtime-compose:2.6.0-alpha02")

//  implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.1")
//  implementation("androidx.paging:paging-common-ktx:3.1.1")
//  testImplementation("androidx.arch.core:core-testing:2.1.0")

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