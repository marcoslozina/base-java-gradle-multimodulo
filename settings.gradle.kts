rootProject.name = "gradle-profesional-ejemplo"
include("application", "domain", "infrastructure", "config")

pluginManagement {
  repositories {
    gradlePluginPortal()
    mavenCentral()
  }

  // leer las props desde gradle.properties (API recomendada desde Gradle 9.6,
  // "by settings" queda deprecado y se elimina en Gradle 10)
  val springBootVersion = providers.gradleProperty("springBootVersion").get()
  val springDepMgmtVersion = providers.gradleProperty("springDepMgmtVersion").get()

  plugins {
    id("org.springframework.boot") version springBootVersion
    id("io.spring.dependency-management") version springDepMgmtVersion
  }
}

dependencyResolutionManagement {
  repositoriesMode.set(RepositoriesMode.PREFER_PROJECT)
  repositories { mavenCentral() }
}
