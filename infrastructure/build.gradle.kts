import com.tuempresa.proyecto.build.ProjectConventions

plugins {
    java
}
java {
    toolchain.languageVersion.set(ProjectConventions.javaVersion)
}