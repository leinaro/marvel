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
  }
}

dependencies {
  implementation(project(":architecture-tools"))
  implementation(project(":domain"))
  implementation(project(":data"))

  implementation("com.google.android.material:material:1.6.1")
  testImplementation("junit:junit:4.13.2")
  androidTestImplementation("androidx.test.ext:junit:1.1.3")
  androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
}