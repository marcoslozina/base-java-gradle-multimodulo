package com.tuempresa.proyecto

import org.gradle.jvm.toolchain.JavaLanguageVersion

object ProjectConventions {
  const val group: String = "com.tuempresa.proyecto"
  const val version: String = "1.0.0"
  val javaVersion: JavaLanguageVersion = JavaLanguageVersion.of(21)
}
