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

  androidTestImplementation("androidx.test.ext:junit:1.1.3")
  androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
}

tasks.withType<Test> {
  configure<JacocoTaskExtension> {
    isIncludeNoLocationClasses = true
    excludes = listOf("jdk.internal.*")
  }
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