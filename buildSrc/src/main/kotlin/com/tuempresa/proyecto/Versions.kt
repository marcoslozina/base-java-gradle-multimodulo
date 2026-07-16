package com.tuempresa.proyecto

object Versions {
  const val springBoot = "4.1.0"
  // JUnit ya no se fija a mano: el BOM de Spring Boot (spring-boot-dependencies)
  // importa junit-bom y gestiona la version de junit-jupiter (evita mismatches
  // entre jupiter y el platform engine, ver Dependencies.kt).
}
