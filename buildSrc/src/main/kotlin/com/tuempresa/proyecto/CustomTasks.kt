package com.tuempresa.proyecto

import org.gradle.api.Project
import org.gradle.kotlin.dsl.register

fun Project.configureCustomTasks() {

  tasks.register("printVersion") {
    group = "versioning"
    description = "Imprime la versión actual del proyecto"
    doLast { println(project.version) }
  }

  tasks.register("testCoverage") {
    group = "verification"
    description = "Ejecuta tests y genera reporte de cobertura (JaCoCo)"
    dependsOn("test", "jacocoTestReport")
  }

  tasks.register("lintAll") {
    group = "verification"
    description = "Ejecuta los linters configurados (check/ktlint/spotless si aplica)"
    dependsOn("check")
  }

  tasks.register("hello") {
    group = "demo"
    description = "Imprime un mensaje simple de prueba"
    doLast { println("¡Hola desde ${project.path}!") }
  }

  tasks.register("verifyBuild") {
    group = "verification"
    description = "Build + cobertura"
    dependsOn("build", "jacocoTestReport")
  }
}
