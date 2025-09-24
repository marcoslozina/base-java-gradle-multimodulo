import com.tuempresa.proyecto.ProjectConventions
import com.tuempresa.proyecto.Dependencies

plugins { `java-library` }

group = ProjectConventions.group
version = ProjectConventions.version

java { toolchain.languageVersion.set(ProjectConventions.javaVersion) }

dependencies {
  api(project(":domain"))
  // Ejemplo: drivers, repos, http client, etc.
  testImplementation(Dependencies.junit)
}
