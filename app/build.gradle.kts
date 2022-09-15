plugins {
  id("com.android.application")
  id("org.jetbrains.kotlin.android")
}

android {
  namespace = "com.leinaro.marvel"
  compileSdk = 33

  defaultConfig {
    applicationId = "com.leinaro.marvel"
    minSdk = 23
    targetSdk = 33
    versionCode = 1
    versionName = "1.0"

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
  buildFeatures {
    compose = true
  }
  composeOptions {
    kotlinCompilerExtensionVersion = "1.2.0-beta01"
    // kotlinCompilerExtensionVersion = "1.3.1"
  }
}

dependencies {

  implementation("androidx.core:core-ktx:1.7.0")
  implementation("androidx.appcompat:appcompat:1.5.1")
  implementation("com.google.android.material:material:1.6.1")

  //Compose
  implementation("androidx.activity:activity-compose:1.5.0")
  implementation("androidx.compose.material:material:1.1.1")
  implementation("androidx.compose.ui:ui-tooling:1.1.1")
  implementation("androidx.compose.ui:ui:1.1.1")
  implementation("androidx.compose.ui:ui-tooling-preview:1.1.1")
  //implementation("com.google.accompanist:accompanist-swiperefresh:0.26.3-beta")

  testImplementation("junit:junit:4.13.2")
  androidTestImplementation("androidx.test.ext:junit:1.1.3")
  androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
}