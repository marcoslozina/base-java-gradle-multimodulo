// buildSrc/src/main/kotlin/com/tuempresa/proyecto/CustomTasks.kt
package com.tuempresa.proyecto

import org.gradle.api.DefaultTask
import org.gradle.api.Project
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction
import org.gradle.kotlin.dsl.register

// -------- Tasks compatibles con Configuration Cache --------

abstract class HelloTask : DefaultTask() {
  @get:Input abstract val modulePath: Property<String>
  @TaskAction fun run() { println("¡Hola desde ${modulePath.get()}!") }
}

abstract class PrintVersionTask : DefaultTask() {
  @get:Input abstract val versionText: Property<String>
  @TaskAction fun run() { println(versionText.get()) }
}

fun Project.configureCustomTasks() {
  // hello
  tasks.register<HelloTask>("hello") {
    group = "demo"
    description = "Imprime un saludo desde el módulo"
    modulePath.set(this@configureCustomTasks.path)
  }

  // printVersion
  tasks.register<PrintVersionTask>("printVersion") {
    group = "versioning"
    description = "Imprime la versión del proyecto"
    versionText.set(this@configureCustomTasks.version.toString())
  }

  // testCoverage (agrega cobertura al flujo de test)
  tasks.register("testCoverage") {
    group = "verification"
    description = "Ejecuta tests y genera reporte de cobertura Jacoco"
    dependsOn("test", "jacocoTestReport")
  }

  // lintAll (agrega condicionalmente los linters que existan)
  tasks.register("lintAll") {
    group = "verification"
    description = "Ejecuta todos los linters/configurados si están disponibles"

    // Lista de tareas de lint/comprobación comunes (se agregan solo si existen)
    val candidateTasks = listOf(
      "check",                 // Gradle check (agrega varias verificaciones)
      "spotlessCheck",         // Spotless
      "ktlintCheck",           // ktlint
      "detekt",                // detekt
      "checkstyleMain",        // Checkstyle
      "checkstyleTest",
      "pmdMain",               // PMD
      "pmdTest",
      "spotbugsMain",          // SpotBugs
      "spotbugsTest"
    )

    // Depender solo de las que realmente existan en este subproyecto
    candidateTasks.forEach { name ->
      if (tasks.findByName(name) != null) {
        dependsOn(name)
      }
    }
  }

  // verifyBuild (build + cobertura, sin lógica en ejecución)
  tasks.register("verifyBuild") {
    group = "verification"
    description = "Build completo + cobertura (modo verificación de CI)"
    dependsOn("clean", "build", "jacocoTestReport")
  }
}
