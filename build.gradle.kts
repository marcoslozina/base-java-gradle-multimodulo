plugins {
  java
  jacoco
}

allprojects {
  repositories {
    mavenCentral()
  }
}

tasks.jacocoTestReport {
  dependsOn("test")
  reports {
    xml.required.set(true)
    html.required.set(true)
  }
}

java {
  toolchain.languageVersion.set(JavaLanguageVersion.of(21))
}
//Usado para el custom task print version
version = findProperty("projectVersion") as String

configureCustomTasks()
