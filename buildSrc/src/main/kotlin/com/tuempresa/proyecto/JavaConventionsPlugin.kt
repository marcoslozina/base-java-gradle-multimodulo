package com.tuempresa.proyecto

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.testing.Test
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.withType
import org.gradle.testing.jacoco.tasks.JacocoReport

class JavaConventionsPlugin : Plugin<Project> {
  override fun apply(target: Project) = with(target) {
    group = ProjectConventions.group
    version = ProjectConventions.version

    // Para módulos Java
    plugins.withId(Plugins.java) {
      // Toolchain
      extensions.configure(org.gradle.api.plugins.JavaPluginExtension::class.java) {
        toolchain.languageVersion.set(ProjectConventions.javaVersion)
      }

      // Jacoco en todos los módulos Java
      pluginManager.apply("jacoco")

      // BOM + deps de test parametrizadas
      dependencies {
        // Si usás Spring en módulos, el BOM alinea versiones
        add("implementation", platform(Dependencies.springBootBom))
        add("testImplementation", Dependencies.junit)
      }

      // Tests + cobertura
      tasks.withType<Test> {
        useJUnitPlatform()
        finalizedBy("jacocoTestReport")
      }
      tasks.withType(JacocoReport::class.java).configureEach {
        reports {
          xml.required.set(true)
          html.required.set(true)
        }
      }

      // Tus tareas DX (hello, printVersion, testCoverage)
      configureCustomTasks()
    }
  }
}
