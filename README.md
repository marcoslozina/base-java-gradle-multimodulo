# 📘 Java 21 Multimódulo con Gradle 8 y Spring Boot 3

**Guía práctica para construir backends modernos con arquitectura hexagonal, CI/CD, testing y seguridad empresarial**

Este repositorio acompaña al eBook **"Java 21 Multimódulo con Gradle 8 y Spring Boot 3"**, una guía práctica pensada para desarrolladores que quieren llevar sus backends al siguiente nivel.

El libro explica paso a paso cómo estructurar proyectos backend profesionales con **Java 21, Spring Boot 3.3.6 y Gradle 8 (Kotlin DSL)**, y este repositorio representa la implementación práctica de esos conceptos.

> 💡 **Objetivo**: Servir como punto de partida profesional para proyectos backend modernos, con una arquitectura modular, limpia y extensible.

---

## 📦 Características principales

- ✅ **Java 21** + **Spring Boot 3.3.6**
- ✅ Configuración con **Gradle 8** (Kotlin DSL)
- ✅ Arquitectura multimódulo limpia y escalable
- ✅ Centralización de dependencias en `buildSrc`
- ✅ Uso de `spring-dependency-management` y BOM
- ✅ Tareas personalizadas (`printVersion`, `lintAll`, etc.)
- ✅ Compatible con CI/CD y buenas prácticas profesionales
- ✅ Testing automatizado con JUnit 5.11.4
- ✅ Cobertura de código con JaCoCo
- ✅ Verificación de actualizaciones de dependencias

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

Cada capítulo del eBook está vinculado con elementos concretos de este proyecto. Podés seguir el repositorio mientras avanzás en la lectura, replicando cada concepto explicado:

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

## 📖 Obtener el eBook

Este repositorio está directamente relacionado con el eBook **"Java 21 Multimódulo con Gradle 8 y Spring Boot 3"**. Podés adquirirlo en cualquiera de estos canales:

<div align="center">

[![Amazon](https://img.shields.io/badge/Amazon-Kindle-orange?style=for-the-badge&logo=amazon&logoColor=white)](https://www.amazon.com/dp/B0FRR8P9KP)
[![Hotmart](https://img.shields.io/badge/Hotmart-Digital-blue?style=for-the-badge&logo=hotjar&logoColor=white)](https://go.hotmart.com/Y102830298M)
[![Gumroad](https://img.shields.io/badge/Gumroad-Download-black?style=for-the-badge&logo=gumroad&logoColor=white)](https://marcoslozina.gumroad.com/l/lnifg)

</div>

### 📚 ¿Qué encontrarás en el libro?

- ✅ Cómo configurar proyectos **multimódulo** en **Gradle 8** con buenas prácticas desde el inicio
- ✅ Integración real con **Spring Boot 3** y librerías de uso frecuente en entornos corporativos
- ✅ Ejemplos de **arquitectura hexagonal** aplicados a proyectos modernos
- ✅ Estrategias de **CI/CD**, testing automatizado y seguridad empresarial
- ✅ Consejos para optimizar builds, organizar dependencias y mejorar la productividad de los equipos

👉 **Ideal para**: Desarrolladores Java que quieran actualizarse a **Java 21**, líderes técnicos que busquen proyectos **escalables y mantenibles**, y profesionales que quieran preparar su backend para **producción** con estándares de la industria.

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
./gradlew :application:test
./gradlew dependencies
./gradlew projects
```

### 📌 Tareas personalizadas

```bash
./gradlew printVersion
./gradlew testCoverage
./gradlew lintAll
./gradlew hello
./gradlew verifyBuild
```

---

## ⚙️ Tareas personalizadas disponibles

| Tarea           | Grupo         | Descripción                                                                 |
|-----------------|---------------|-----------------------------------------------------------------------------|
| `printVersion`  | versioning    | Imprime la versión actual del proyecto                                      |
| `testCoverage`  | verification  | Ejecuta los tests y genera el reporte de cobertura con JaCoCo               |
| `lintAll`       | verification  | Ejecuta todos los linters configurados (check, ktlint, etc.)                |
| `hello`         | demo          | Imprime un mensaje simple para probar ejecución de tareas                   |
| `verifyBuild`   | verification  | Ejecuta `build` y genera cobertura                                          |

---

## 🗂 Requisitos

- **Java 21** (configurable desde `gradle.properties`)
- **Gradle 8.12+**
- Docker (opcional)
- IDE recomendada: **IntelliJ IDEA**

---

## ☕ Donaciones

Si este proyecto o el libro te fueron útiles, podés apoyar el desarrollo con una donación. Tu apoyo ayuda a mantener y mejorar este tipo de contenido educativo y a seguir creando recursos de calidad para la comunidad.

<div align="center">

[![Buy Me A Coffee](https://img.shields.io/badge/Buy%20Me%20A%20Coffee-FFDD00?style=for-the-badge&logo=buy-me-a-coffee&logoColor=black)](https://buymeacoffee.com/codefuel)
[![PayPal](https://img.shields.io/badge/PayPal-00457C?style=for-the-badge&logo=paypal&logoColor=white)](https://www.paypal.com/donate/?hosted_button_id=4TYGJ5S8CLX8J)

</div>

### 💝 Formas de apoyar

- ☕ **[Buy Me a Coffee](https://buymeacoffee.com/codefuel)** - Apoyo rápido y sencillo
- 💳 **[PayPal Donate](https://www.paypal.com/donate/?hosted_button_id=4TYGJ5S8CLX8J)** - Donación directa
- 📖 **Comprar el eBook** - Adquirir el libro en [Amazon](https://www.amazon.com/dp/B0FRR8P9KP), [Hotmart](https://go.hotmart.com/Y102830298M) o [Gumroad](https://marcoslozina.gumroad.com/l/lnifg)
- ⭐ **Dar una estrella** - Si te gustó el proyecto, ¡dale una estrella en GitHub!
- 📝 **Dejar una reseña** - Tu opinión en Amazon es muy valiosa

¡Gracias por tu apoyo! 🙌

---

## 🤝 Contribuciones

Este proyecto está diseñado como plantilla educativa y punto de partida para desarrollos backend reales. Podés adaptarlo libremente a tus necesidades.

### 📝 ¿Cómo contribuir?

- ⭐ **Dar una estrella** al proyecto si te resultó útil
- 🐛 **Reportar bugs** o sugerir mejoras abriendo un issue
- 📖 **Compartir el proyecto** con otros desarrolladores
- ⭐ **Dejar una reseña** del libro en [Amazon](https://www.amazon.com/dp/B0FRR8P9KP), [Hotmart](https://go.hotmart.com/Y102830298M) o [Gumroad](https://marcoslozina.gumroad.com/l/lnifg)

¡Tu feedback es muy valioso para seguir mejorando y compartiendo conocimiento con la comunidad! 🙌

---

## 📜 Licencia y Registro Legal

| Registro             | Detalle                                                                 |
|----------------------|-------------------------------------------------------------------------|
| **ISBN**             | 978-631-00-9065-8                                                       |
| **DNDA (Argentina)** | EX-2025-53087447--APN-DNDA – Obra inédita registrada a nombre de *Marcos Raimundo Lozina* |
| **Safe Creative**    | Código: 2505211801388 – [Certificado en línea](https://www.safecreative.org/work/2505211801388) |
| **Licencia**         | Todos los derechos reservados                                           |

Todos los derechos reservados © 2025 Marcos Lozina.  
Este material no puede ser reproducido, distribuido ni transmitido en ninguna forma o por ningún medio sin autorización expresa del autor.
