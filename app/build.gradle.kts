import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
import org.jetbrains.kotlin.konan.properties.propertyString

plugins {
  id("leinaro-android-common")
  id("jacoco")
  id("plugins.jacoco-report")
  id("com.google.gms.google-services")
  id("com.google.firebase.crashlytics")
}

val localProperties = gradleLocalProperties(rootDir)

android {
  namespace = "com.leinaro.marvel"

  signingConfigs {
    create("release") {
      keyAlias = localProperties.propertyString("release.alias").orEmpty()
      keyPassword = localProperties.propertyString("release.keyPassword").orEmpty()
      storeFile = file(localProperties.propertyString("release.storeFile").orEmpty())
      storePassword = localProperties.propertyString("release.storePassword").orEmpty()
    }
  }

  defaultConfig {
    applicationId = "com.leinaro.marvel"
    versionCode = 1
    versionName = "1.0"
  }

  buildTypes {
    release {
      signingConfig = signingConfigs.getByName("release")
      isMinifyEnabled = true
      proguardFiles(
        getDefaultProguardFile("proguard-android-optimize.txt"),
        "proguard-rules.pro"
      )
      isDebuggable = false
    }
    debug {
      isMinifyEnabled = false
      isDebuggable = true
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
  implementation(project(":core"))
  implementation(project(":characters-list"))
  implementation(project(":character-search"))
  implementation(project(":character-details"))

  implementation(platform("com.google.firebase:firebase-bom:30.4.1"))
  implementation("com.google.firebase:firebase-crashlytics-ktx")
  implementation("com.google.firebase:firebase-analytics-ktx")

  androidTestImplementation("androidx.test.ext:junit:1.1.3")
  androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
}

tasks.withType<Test> {
  configure<JacocoTaskExtension> {
    isIncludeNoLocationClasses = true
    excludes = listOf("jdk.internal.*", "**/*ScreenKt")
  }
}

extra.set(
  JacocoCoverage.coverageDataExtra,
  CoverageTaskParam(buildDirectory = buildDir)
)