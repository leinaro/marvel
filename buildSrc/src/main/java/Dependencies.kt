/**
 * To define dependencies
 */
object Dependencies {
  val androidxCoreKtx by lazy { "androidx.core:core-ktx:${Versions.androidxCoreKtx}" }
  val androidxAppCompat by lazy { "androidx.appcompat:appcompat:${Versions.androidxAppCompat}" }

  // region Compose
  val androidxComposeUi by lazy { "androidx.compose.ui:ui:${Versions.compose}" }
  val androidxComposeUiTooling by lazy { "androidx.compose.ui:ui-tooling:${Versions.compose}" }
  val androidxComposeToolingPreview by lazy { "androidx.compose.ui:ui-tooling-preview:${Versions.compose}" }
  val androidxComposeMaterial by lazy { "androidx.compose.material:material:${Versions.compose}" }

  val viewModelCompose by lazy { "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.viewModelCompose}" }

  val androidxActivityCompose by lazy { "androidx.activity:activity-compose:${Versions.activityCompose}" }
  val accompanistSwipeRefresh by lazy { "com.google.accompanist:accompanist-swiperefresh:${Versions.accompanistSwipeRefresh}" }
  // val runtimeLifecycleCompose by lazy { "androidx.lifecycle:lifecycle-runtime-compose:2.6.0-alpha01" }

  val pagingRuntime by lazy { "androidx.paging:paging-runtime:3.1.1" }
  val pagingCompose by lazy { "androidx.paging:paging-compose:1.0.0-alpha16" }
  // endregion

  // region Ktx
  val androidxActivityKtx by lazy { "androidx.activity:activity-ktx:${Versions.androidxActivityKtx}" }
  //endregion

  // region Hilt
  val hiltAndroid by lazy { "com.google.dagger:hilt-android:${Versions.hilt}" }
  val hiltCompiler by lazy { "com.google.dagger:hilt-compiler:${Versions.hilt}" }
  val hiltNavigationCompose by lazy { "androidx.hilt:hilt-navigation-compose:${Versions.hiltCompose}" }
  // endregion

  // region images
  val coil by lazy { "io.coil-kt:coil-compose:${Versions.coil}" }
  // endregion

  val timber by lazy { "com.jakewharton.timber:timber:${Versions.timber}" }
  val kotlin by lazy { "org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.7.10" }
  val materialDesign by lazy { "com.google.android.material:material:${Versions.material}" }
  val constraintLayout by lazy { "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}" }

  // Tests
  val jUnit5 by lazy { "org.junit.jupiter:junit-jupiter-api:${Versions.jUnit5}" }
  val mockk by lazy { "io.mockk:mockk:${Versions.mockk}" }
  val turbine by lazy { "app.cash.turbine:turbine:0.11.0" }
  val truth by lazy { "com.google.truth:truth:1.1.3" }
  val kotlinCoroutinesTest by lazy { "org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.4" }

  // AndroidTests
  val composeJunit4 by lazy { "androidx.compose.ui:ui-test-junit4:${Versions.compose}" }
  val hiltAndroidTest by lazy { "com.google.dagger:hilt-android-testing:2.38.1" }
  val hiltAndroidTestCompiler by lazy { "com.google.dagger:hilt-android-compiler:2.38.1" }
  val composeUiTest by lazy { "androidx.compose.ui:ui-test-manifest:${Versions.compose}" }
}

