import java.io.File

object JacocoCoverage {
  val exclusions = listOf<String>()
  val executionDataPath = arrayOf(
    "**/jacoco/*.exec",
  )
  val classDirectories = arrayOf(
    "**/jacoco/*.exec"
  )
  val sourceDirectories = arrayOf("src/main/java")
  const val reportPath = "jacoco/reports/jacocoAndroidTestReport/jacocoAndroidTestReport.xml"
  const val coverageDataExtra = "coverageData"
}

data class Coverage(
  val instructions: Double = 0.0,
  val lines: Double = 0.0,
  val complexity: Double = 0.0,
  val methods: Double = 0.0,
  val classes: Double = 0.0
)

data class CoverageTaskParam(
  val buildDirectory: File,
  val task: String,
  val coverageData: Coverage,
  val filesToInclude: List<String>,
  val filesToExclude: List<String>
)