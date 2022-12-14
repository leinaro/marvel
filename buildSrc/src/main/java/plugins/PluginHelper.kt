package plugins

import Dependencies
import org.gradle.kotlin.dsl.DependencyHandlerScope

fun DependencyHandlerScope.addCoilDependencies() {
// Coil
  add("implementation", Dependencies.coil)
}

fun DependencyHandlerScope.addHiltDependencies() {
  // Hilt
  add("implementation", Dependencies.hiltAndroid)
  add("kapt", Dependencies.hiltCompiler)
  add("implementation", Dependencies.hiltNavigationCompose)
}

fun DependencyHandlerScope.addComposeDependencies() {
  // Compose
  add("implementation", Dependencies.androidxActivityCompose)
  add("implementation", Dependencies.androidxComposeMaterial)
  add("implementation", Dependencies.androidxComposeUiTooling)
  add("implementation", Dependencies.androidxComposeUi)
  add("implementation", Dependencies.androidxComposeToolingPreview)
  add("implementation", Dependencies.accompanistSwipeRefresh)
  add("implementation", Dependencies.viewModelCompose)
  add("implementation", Dependencies.pagingCompose)
  add("implementation", Dependencies.pagingRuntime)
  add("implementation", "androidx.navigation:navigation-compose:2.5.2")
}

fun DependencyHandlerScope.addTestDependencies() {
  // Test
  add("testImplementation", Dependencies.mockk)
  // flows
  add("testImplementation", Dependencies.turbine)
  add("testImplementation", Dependencies.truth)
  add("testImplementation", Dependencies.kotlinCoroutinesTest)
}

fun DependencyHandlerScope.addAndroidTestDependencies() {
  // Test
  add("androidTestImplementation", Dependencies.composeJunit4)
  add("androidTestImplementation", Dependencies.hiltAndroidTest)
  add("kaptAndroidTest", Dependencies.hiltAndroidTestCompiler)
  add("debugImplementation", Dependencies.composeUiTest)
}