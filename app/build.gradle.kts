plugins {
  id("leinaro-android-common")
  id("jacoco")
  id("plugins.jacoco-report")
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
  implementation(project(":architecture-tools"))
  implementation(project(":characters-list"))
  implementation(project(":character-search"))
  implementation(project(":character-details"))

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
  CoverageTaskParam(
    buildDir,
    "testDebugUnitTest",
    Coverage(
      instructions = 20.0,
      lines = 20.0,
      complexity = 20.0,
      methods = 20.0,
      classes = 20.0
    ),
    emptyList(),
    listOf("**/*Screen.*")
  )
)