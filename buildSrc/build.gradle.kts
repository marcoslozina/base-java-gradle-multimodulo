plugins {
  `kotlin-dsl`
}

gradlePlugin {
  plugins {
    create("javaConventions") {
      id = "com.tuempresa.proyecto.java-conventions"
      implementationClass = "com.tuempresa.proyecto.JavaConventionsPlugin"
    }
  }
}

repositories { mavenCentral() }
