package com.tuempresa.proyecto

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.testing.Test
import org.gradle.testing.jacoco.tasks.JacocoReport
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.repositories
import org.gradle.kotlin.dsl.withType

class JavaConventionsPlugin : Plugin<Project> {
  override fun apply(target: Project) = with(target) {
    // Grupo y versión definidos en ProjectConventions
    group = ProjectConventions.group
    version = ProjectConventions.version

    // Repositorios comunes
    repositories { mavenCentral() }

    // Solo aplica cuando el subproyecto tenga plugin "java"
    plugins.withId(Plugins.java) {
      // Configuración de toolchain Java
      extensions.configure(org.gradle.api.plugins.JavaPluginExtension::class.java) {
        toolchain.languageVersion.set(ProjectConventions.javaVersion)
      }

      // Aplica JaCoCo
      pluginManager.apply("jacoco")

      // Dependencias de test
      dependencies {
        add("testImplementation", Dependencies.junit)
      }

      // Configuración de tests
      tasks.withType<Test> {
        useJUnitPlatform()
        finalizedBy("jacocoTestReport")
      }

      // Reportes de JaCoCo
      tasks.withType(JacocoReport::class.java).configureEach {
        reports {
          xml.required.set(true)
          html.required.set(true)
        }
      }

      // Registrar tareas custom (hello, printVersion, testCoverage)
      configureCustomTasks()
    }
  }
}
