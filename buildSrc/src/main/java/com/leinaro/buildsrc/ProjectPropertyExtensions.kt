package com.leinaro.buildsrc

import org.gradle.api.Project

fun Project.getProjectVersionName(): String? {
  val versionName = findProperty("VERSION_NAME")
  return if (versionName is String) versionName else null
}