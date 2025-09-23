import org.gradle.api.tasks.testing.Test
import org.gradle.testing.jacoco.tasks.JacocoReport

plugins {
  jacoco
  id("com.tuempresa.proyecto.java-conventions") apply false
}

allprojects { repositories { mavenCentral() } }

subprojects {
  apply(plugin = "com.tuempresa.proyecto.java-conventions")
  apply(plugin = "jacoco")

  plugins.withId("java") {
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
  }
}
