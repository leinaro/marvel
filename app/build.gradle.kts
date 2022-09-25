plugins {
  id("leinaro-android-common")
}

android {
  namespace = "com.leinaro.marvel"

  defaultConfig {
    applicationId = "com.leinaro.marvel"
    versionCode = 1
    versionName = "1.0"

  }

  buildTypes {
    release {
      isMinifyEnabled = false
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
    }
  }

  kotlinOptions {
    jvmTarget = "1.8"
  }
}

hilt {
  enableExperimentalClasspathAggregation = true
}

dependencies {

  implementation("com.google.android.material:material:1.6.1")

  implementation(project(":characters-list"))

 // testImplementation("junit:junit:4.13.2")
  androidTestImplementation("androidx.test.ext:junit:1.1.3")
  androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
}