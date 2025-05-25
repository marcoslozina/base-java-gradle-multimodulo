import org.gradle.api.Project
import org.gradle.kotlin.dsl.register

fun Project.configureCustomTasks() {

  tasks.register("testCoverage") {
    group = "verification"
    description = "Ejecuta tests y genera reporte de cobertura"
    dependsOn("test", "jacocoTestReport")
  }

  tasks.register("printVersion") {
    group = "versioning"
    description = "Imprime la versión actual del proyecto"
    doLast {
      println("Versión del proyecto: ${project.version}")
    }
  }

  tasks.register("lintAll") {
    group = "verification"
    description = "Ejecuta todos los linters configurados"
    dependsOn("check") // o tareas específicas de linter si las tenés
  }

  tasks.register("hello") {
    group = "demo"
    description = "Imprime un mensaje simple"
    doLast {
      println("¡Hola desde Gradle!")
    }
  }

  tasks.register("verifyBuild") {
    group = "verification"
    description = "Ejecuta build, test y cobertura"
    dependsOn("build", "jacocoTestReport")
  }
}
