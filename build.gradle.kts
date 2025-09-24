plugins {
  jacoco
  id("com.tuempresa.proyecto.java-conventions") apply false
}

allprojects {
  repositories { mavenCentral() }
}

subprojects {
  apply(plugin = "com.tuempresa.proyecto.java-conventions")
}

// (Opcional) override de versión con -PprojectVersion=1.2.3
if (hasProperty("projectVersion")) {
  allprojects { version = property("projectVersion") as String }
}
