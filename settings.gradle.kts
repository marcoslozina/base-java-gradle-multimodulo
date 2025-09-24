rootProject.name = "gradle-profesional-ejemplo"
include("application", "domain", "infrastructure", "config")

pluginManagement {
  repositories {
    gradlePluginPortal()
    mavenCentral()
  }

  // leer las props desde gradle.properties
  val springBootVersion: String by settings
  val springDepMgmtVersion: String by settings

  plugins {
    id("org.springframework.boot") version springBootVersion
    id("io.spring.dependency-management") version springDepMgmtVersion
  }
}

dependencyResolutionManagement {
  repositoriesMode.set(RepositoriesMode.PREFER_PROJECT)
  repositories { mavenCentral() }
}
