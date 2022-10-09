# buildSrc
This module contains plugins files, jacoco coverage task and dependencies manager files.
All modules includes jacoco plugins.

## How to use plugins
There are 3 plugins: 
- LeinaroAndroidCommonPlugin 
It is a application level presentation plugin, it should be applied on modules that need `com.android.application` plugins.
This contains hilt, compose, coil, unit tests and android test dependencies. 
To use the plugin add `id("leinaro-android-common")` on the `build.gradle` file as follow:

```
plugins {
  id("leinaro-android-common")
  ...
}
```

- LeinaroAndroidLibraryPlugin
  It is a library level presentation plugin, it should be applied on modules that need `com.android.library` plugins.
  This contains hilt, compose, coil, unit tests and android test dependencies.
  To use the plugin add `id("leinaro-android-library")` on the `build.gradle` file as follow:

```
plugins {
  id("leinaro-android-library")
  ...
}
```

- LeinaroKotlinLibraryPlugin
  It is a domain/data level domain/data plugin, it should be applied on modules that need `com.android.library` plugins.
  This contains hilt and unit tests dependencies.
  To use the plugin add `id("leinaro-kotlin-library")` on the `build.gradle` file as follow:

 ```
plugins {
  id("leinaro-kotlin-library")
  ...
}
```

## Jacoco Coverage pluglin
- jacoco-report: It is a JacocoPluginExtension, includes checkCoverage task, you could run task with:
```
./gradlew checkCoverage
```
- JacocoCoverage: Includes default coverage data

## Add dependencies to modules

- Add dependency version to Versions file `const val hilt = "2.44"`
- Add dependency to to Dependencies file `val hiltAndroid by lazy { "com.google.dagger:hilt-android:${Versions.hilt}" }`
- Include dependency on modules `add("implementation", Dependencies.hiltAndroid)`


