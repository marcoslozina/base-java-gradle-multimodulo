import com.tuempresa.proyecto.ProjectConventions

plugins {
  `java`
}

group = ProjectConventions.group
version = ProjectConventions.version

java {
  toolchain.languageVersion.set(ProjectConventions.javaVersion)
}

dependencies {
  testImplementation(Dependencies.junit)
}
