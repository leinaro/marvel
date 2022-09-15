/**
 * To define dependencies
 */
object Dependencies {
  val androidxCoreKtx by lazy { "androidx.core:core-ktx:${Versions.androidxCoreKtx}" }
  val androidxAppCompat by lazy { "androidx.appcompat:appcompat:${Versions.androidxAppCompat}" }

  val timber by lazy { "com.jakewharton.timber:timber:${Versions.timber}" }
  val kotlin by lazy { "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}" }
  val materialDesign by lazy { "com.google.android.material:material:${Versions.material}" }
  val constraintLayout by lazy { "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}" }
  val junit by lazy { "junit:junit:${Versions.jUnit}" }
}

