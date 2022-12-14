/**
 * To define plugins
 */
object BuildPlugins {
  val android by lazy { "com.android.tools.build:gradle:${Versions.gradlePlugin}" }
  val kotlin by lazy { "org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.10" }
}