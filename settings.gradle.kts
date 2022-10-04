/*pluginManagement {
  repositories {
    gradlePluginPortal()
    google()
    mavenCentral()
  }
}
dependencyResolutionManagement {
  repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
  repositories {
    google()
    mavenCentral()
  }
}*/
include(":app")
include(":architecture-tools")
include(":characters-list")
include(":character-search")
include(":domain")
include(":apis")
include(":data")

rootProject.name = "Marvel"
include(":character-details")
