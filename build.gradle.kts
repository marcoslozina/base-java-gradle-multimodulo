plugins {
  jacoco
  id("com.tuempresa.proyecto.java-conventions") apply false
  id("com.github.ben-manes.versions") version "0.53.0"
}

allprojects {
  repositories { mavenCentral() }
}

subprojects {
  apply(plugin = "com.tuempresa.proyecto.java-conventions")
}

// Tarea para verificar actualizaciones de dependencias
tasks.register("checkDependencyUpdates") {
  description = "Verifica actualizaciones disponibles de dependencias"
  group = "verification"
  doLast {
    println("Ejecuta './gradlew dependencyUpdates' para ver las actualizaciones disponibles")
  }
}

// Configuración para el plugin de dependencyUpdates
tasks.named<com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask>("dependencyUpdates") {
  checkForGradleUpdate = true
  outputFormatter = "plain"
  revision = "release"
  outputDir = "build/dependencyUpdates"
}

// (Opcional) override de versión con -PprojectVersion=1.2.3
if (hasProperty("projectVersion")) {
  allprojects { version = property("projectVersion") as String }
}
