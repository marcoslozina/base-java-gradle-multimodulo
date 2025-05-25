# 📘 Proyecto de Referencia – Plantilla Java Multimódulo con Gradle y Spring Boot

**Una guía práctica para construir backends modernos, modulares y mantenibles**

Este repositorio acompaña al eBook **"Plantilla Java Multimódulo con Gradle y Spring Boot"** y representa una implementación práctica de los conceptos explicados en el libro. Aplica una arquitectura escalable y mantenible basada en Java 21, Spring Boot y Gradle con Kotlin DSL.

> 💡 **Objetivo**: Servir como punto de partida profesional para proyectos backend modernos, con una estructura modular, limpia y extensible.

---

## 📦 Características principales

- ✅ Java 21 + Spring Boot 3.2
- ✅ Configuración con Gradle Kotlin DSL
- ✅ Arquitectura multimódulo limpia y escalable
- ✅ Centralización de dependencias en `buildSrc`
- ✅ Uso de `spring-dependency-management` y BOM
- ✅ Tareas personalizadas (`printVersion`, `lintAll`, etc.)
- ✅ Compatible con CI/CD y buenas prácticas profesionales

---

## 📂 Estructura del Proyecto

```
base-java-gradle-multimodulo/
├── buildSrc/           # Lógica y convenciones de build compartidas
├── application/        # Punto de entrada de la aplicación (Spring Boot)
├── domain/             # Lógica de negocio y modelo de dominio
├── infrastructure/     # Adaptadores externos, controladores, repositorios
├── config/             # Configuraciones transversales del sistema
├── gradle.properties   # Parámetros globales como versión de Java y app
└── settings.gradle.kts # Inclusión de módulos
```

---

## 📘 Relación con el eBook

Cada capítulo del eBook está vinculado con elementos concretos de este proyecto. Podés seguir el desarrollo del repositorio a medida que avanza la lectura del libro, replicando cada concepto explicado:

| Capítulo | Área aplicada | Tema |
|----------|----------------|------|
| Introducción | Raíz del proyecto | Setup inicial con Gradle Kotlin DSL |
| Modularización | application/, domain/ | Separación por capas |
| `buildSrc` | buildSrc/ | Centralización de versiones y lógica de build |
| Propiedades globales | gradle.properties | Gestión de versiones y metadatos |
| Tareas personalizadas | build.gradle.kts | Automatización |
| Toolchain y compilación | java.toolchain | JDK configurable desde propiedades |
| Pruebas y cobertura | test/, jacoco | Test unitarios y métricas de calidad |

---

## 🛠️ Compilación y ejecución

Este proyecto se compila siguiendo la configuración centralizada definida en `gradle.properties`. El primer módulo en compilarse es `buildSrc`, ya que define convenciones y dependencias usadas por los demás módulos.

```bash
./gradlew clean build
```

Para ejecutar la app principal:

```bash
./gradlew :application:bootRun
```

---

## 🧪 Comandos útiles

```bash
./gradlew test
./gradlew jacocoTestReport
./gradlew printVersion
./gradlew :application:test
./gradlew dependencies
./gradlew projects
```

---

## 🗂 Requisitos

- Java 21 (configurable desde `gradle.properties`)
- Gradle 8+
- Docker (opcional)
- IDE recomendada: IntelliJ IDEA

---

## 🤝 Contribuciones

Este proyecto está diseñado como plantilla educativa y punto de partida para desarrollos backend reales. Podés adaptarlo libremente a tus necesidades.

---

## 📜 Licencia

Todos los derechos reservados © 2025 Marcos Lozina  
Obra registrada en ISBN, DNDA y Safe Creative.

---
