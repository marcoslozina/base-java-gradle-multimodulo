import com.tuempresa.proyecto.ProjectConventions
import com.tuempresa.proyecto.Dependencies

plugins {
  id("org.springframework.boot")
  // id("io.spring.dependency-management") // opcional si ya aplicás BOM en tu plugin de convención
  java
}

group = ProjectConventions.group
version = ProjectConventions.version

java { toolchain.languageVersion.set(ProjectConventions.javaVersion) }

dependencies {
  implementation(project(":domain"))
  implementation(project(":infrastructure"))

  implementation("org.springframework.boot:spring-boot-starter-web")
  testImplementation(Dependencies.junit)
  testImplementation("org.springframework.boot:spring-boot-starter-test")
}

// Configurá explícitamente el main si tu paquete no es el raíz
tasks.named<org.springframework.boot.gradle.tasks.bundling.BootJar>("bootJar") {
  mainClass.set("com.tuempresa.proyecto.Application")
}
