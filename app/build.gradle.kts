plugins {
  alias(libs.plugins.android.application)
  alias(libs.plugins.kotlin.android)
}

android {

  compileSdk = 33
  defaultConfig {
    applicationId = "com.leinaro.marvel"
    minSdk = 23
    targetSdk = 32
    versionCode = 1
    versionName = "1.0"

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    vectorDrawables {
      useSupportLibrary = true
    }
  }

  buildTypes {
    release {
      isMinifyEnabled = false
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
    }
  }
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11

  }
  kotlinOptions {
    jvmTarget = "11"
  }
  buildFeatures {
    compose = true
  }
  composeOptions {
//    kotlinCompilerExtensionVersion = "1.2.1"
    kotlinCompilerExtensionVersion = "1.3.1"
  }
  packagingOptions {
    resources {
      // excludes += '/META-INF/{AL2.0,LGPL2.1}'
    }
  }
}

dependencies {
  implementation(libs.android.core)
  implementation(libs.bundles.compose)
  implementation(libs.lifecycle)
  testImplementation(libs.junit)
  androidTestImplementation(libs.android.junit)
  androidTestImplementation(libs.espresso)
  androidTestImplementation(libs.bundles.compose.android.test)
  debugImplementation(libs.bundles.compose.debug.test)
}