/**
 * To define dependencies
 */
object Dependencies {
  val androidxCoreKtx by lazy { "androidx.core:core-ktx:${Versions.androidxCoreKtx}" }
  val androidxAppCompat by lazy { "androidx.appcompat:appcompat:${Versions.androidxAppCompat}" }

  // region Compose
  val androidxActivityCompose by lazy { "androidx.activity:activity-compose:${Versions.activityCompose}" }
  val androidxComposeMaterial by lazy { "androidx.compose.material:material:${Versions.compose}" }
  val androidxComposeUiTooling by lazy { "androidx.compose.ui:ui-tooling:${Versions.compose}" }
  val androidxComposeUi by lazy { "androidx.compose.ui:ui:${Versions.compose}" }
  val androidxComposeToolingPreview by lazy { "androidx.compose.ui:ui-tooling-preview:${Versions.compose}" }
  val accompanistSwipeRefresh by lazy { "com.google.accompanist:accompanist-swiperefresh:${Versions.accompanistSwipeRefresh}" }
  val viewModelCompose by lazy { "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.viewModelCompose}" }
  // endregion

  // region Ktx
  val androidxActivityKtx by lazy { "androidx.activity:activity-ktx:${Versions.androidxActivityKtx}" }
  //val androidxFragmentKtx by lazy { "androidx.fragment:fragment-ktx:${Versions.androidxFragmentKtx}" }
  //endregion

  // region Hilt
  val hiltAndroid by lazy { "com.google.dagger:hilt-android:${Versions.hilt}" }
  val hiltCompiler by lazy { "com.google.dagger:hilt-compiler:${Versions.hilt}" }
//  androidx.hilt:hilt-navigation-compose:1.0.0-alpha02
  // endregion

  val timber by lazy { "com.jakewharton.timber:timber:${Versions.timber}" }
  val kotlin by lazy { "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}" }
  val materialDesign by lazy { "com.google.android.material:material:${Versions.material}" }
  val constraintLayout by lazy { "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}" }
  val junit by lazy { "junit:junit:${Versions.jUnit}" }
}

