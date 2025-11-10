# 📋 Contexto del Proyecto

## 🎯 Propósito y Objetivos

Este proyecto es una **plantilla profesional de backend Java** diseñada para servir como punto de partida para proyectos empresariales modernos. Está directamente relacionado con el eBook **"Java 21 Multimódulo con Gradle 8 y Spring Boot 3"** y representa la implementación práctica de los conceptos explicados en el libro.

### Objetivos principales:

1. **Educativo**: Demostrar las mejores prácticas en la construcción de backends Java modernos
2. **Plantilla**: Servir como base para nuevos proyectos empresariales
3. **Referencia**: Proporcionar ejemplos prácticos de arquitectura hexagonal, CI/CD y testing
4. **Productivo**: Estar listo para producción con estándares de la industria

---

## 🏗️ Arquitectura y Estructura

### Arquitectura Hexagonal (Ports & Adapters)

El proyecto sigue los principios de **Arquitectura Hexagonal**, separando la lógica de negocio de los detalles de implementación:

```
┌─────────────────────────────────────────────────────────┐
│                    APPLICATION                           │
│              (Punto de entrada Spring Boot)              │
└─────────────────────────────────────────────────────────┘
                        │
        ┌───────────────┼───────────────┐
        │               │               │
┌───────▼──────┐ ┌──────▼──────┐ ┌──────▼──────┐
│  INFRASTRUCTURE │ │   DOMAIN    │ │   CONFIG    │
│  (Adapters)     │ │  (Core)     │ │ (Settings)  │
│                 │ │             │ │             │
│ - Controllers   │ │ - Entities  │ │ - Profiles  │
│ - Repositories  │ │ - Services  │ │ - Props     │
│ - External APIs│ │ - Interfaces │ │             │
└─────────────────┘ └─────────────┘ └─────────────┘
```

### Estructura de Módulos

```
base-java-gradle-multimodulo/
├── buildSrc/              # 🔧 Convenciones y lógica de build compartida
│   ├── src/main/kotlin/
│   │   └── com/tuempresa/proyecto/
│   │       ├── Dependencies.kt      # Definición centralizada de dependencias
│   │       ├── Versions.kt          # Versiones de librerías
│   │       ├── ProjectConventions.kt # Convenciones del proyecto
│   │       ├── JavaConventionsPlugin.kt # Plugin personalizado
│   │       └── CustomTasks.kt      # Tareas personalizadas
│   └── build.gradle.kts
│
├── application/           # 🚀 Módulo principal (Spring Boot)
│   ├── src/main/java/
│   │   └── com/tuempresa/proyecto/application/
│   │       └── Application.java    # Clase principal Spring Boot
│   └── build.gradle.kts
│
├── domain/                # 💼 Lógica de negocio (Core)
│   ├── src/main/java/      # Entidades, servicios, interfaces
│   └── build.gradle.kts
│
├── infrastructure/         # 🔌 Adaptadores externos
│   ├── src/main/java/      # Controllers, Repositories, APIs externas
│   └── build.gradle.kts
│
├── config/                 # ⚙️ Configuraciones transversales
│   ├── src/main/resources/
│   │   ├── application.yml
│   │   ├── application-local.yml
│   │   ├── application-dev.yml
│   │   ├── application-prod.yml
│   │   └── application-uat.yml
│   └── build.gradle.kts
│
├── .github/workflows/      # 🔄 CI/CD
│   └── ci.yml
│
├── build.gradle.kts        # Configuración raíz
├── settings.gradle.kts     # Definición de módulos
├── gradle.properties        # Propiedades globales
└── README.md
```

### Responsabilidades por Módulo

| Módulo | Responsabilidad | Dependencias |
|--------|------------------|--------------|
| **buildSrc** | Convenciones de build, versiones centralizadas, plugin personalizado | Ninguna |
| **domain** | Lógica de negocio pura, entidades, interfaces (ports) | Ninguna (core) |
| **infrastructure** | Implementación de adaptadores (repositorios, APIs externas, controllers) | `domain` |
| **application** | Punto de entrada Spring Boot, configuración de la aplicación | `domain`, `infrastructure` |
| **config** | Configuraciones por perfil (local, dev, prod, uat) | Ninguna |

---

## 🛠️ Stack Tecnológico

### Versiones Actuales (Enero 2025)

| Tecnología | Versión | Propósito |
|------------|---------|-----------|
| **Java** | 21 | Lenguaje de programación |
| **Spring Boot** | 3.3.6 | Framework de aplicación |
| **Spring Dependency Management** | 1.1.7 | Gestión de versiones de dependencias |
| **Gradle** | 8.12 | Sistema de build |
| **Gradle Kotlin DSL** | - | Configuración de build en Kotlin |
| **JUnit 5** | 5.11.4 | Framework de testing |
| **JaCoCo** | 0.8.12 | Cobertura de código |
| **Gradle Versions Plugin** | 0.53.0 | Verificación de actualizaciones |

### Dependencias Principales

```kotlin
// Spring Boot BOM (Bill of Materials)
platform("org.springframework.boot:spring-boot-dependencies:3.3.6")

// Spring Boot Starters
- spring-boot-starter-web
- spring-boot-starter-test

// Testing
- junit-jupiter:5.11.4

// Herramientas de Build
- com.github.ben-manes.versions:0.53.0
```

---

## ⚙️ Configuración y Convenciones

### buildSrc: Centralización de Configuración

El proyecto utiliza `buildSrc` para centralizar toda la configuración de build:

#### `ProjectConventions.kt`
```kotlin
object ProjectConventions {
  const val group: String = "com.tuempresa.proyecto"
  const val version: String = "1.0.0"
  val javaVersion: JavaLanguageVersion = JavaLanguageVersion.of(21)
}
```

#### `Versions.kt`
```kotlin
object Versions {
  const val springBoot = "3.3.6"
  const val junit = "5.11.4"
}
```

#### `Dependencies.kt`
```kotlin
object Dependencies {
  const val springBootBom = "org.springframework.boot:spring-boot-dependencies:${Versions.springBoot}"
  const val junit = "org.junit.jupiter:junit-jupiter:${Versions.junit}"
  // ...
}
```

### Plugin Personalizado: `JavaConventionsPlugin`

El plugin `com.tuempresa.proyecto.java-conventions` aplica automáticamente:

- ✅ **Java Toolchain**: Configura Java 21 para todos los módulos
- ✅ **Spring Boot BOM**: Alinea versiones de dependencias Spring
- ✅ **JaCoCo**: Configura cobertura de código
- ✅ **JUnit Platform**: Configura testing con JUnit 5
- ✅ **Tareas personalizadas**: Agrega tareas útiles (hello, printVersion, testCoverage, etc.)

### gradle.properties

```properties
# Optimizaciones de Gradle
org.gradle.parallel=true
org.gradle.configuration-cache=true
org.gradle.caching=true
org.gradle.jvmargs=-Xmx2g -XX:MaxMetaspaceSize=512m -Dfile.encoding=UTF-8

# Versiones de plugins
springBootVersion=3.3.6
springDepMgmtVersion=1.1.7
```

---

## 🧪 Testing y Calidad

### Estrategia de Testing

1. **Tests Unitarios**: En cada módulo (`src/test/java`)
2. **Cobertura con JaCoCo**: Reportes HTML y XML generados automáticamente
3. **JUnit 5**: Framework de testing moderno
4. **Spring Boot Test**: Integración con Spring Boot para tests de contexto

### Tareas de Testing

```bash
./gradlew test              # Ejecuta todos los tests
./gradlew jacocoTestReport  # Genera reportes de cobertura
./gradlew testCoverage      # Tests + cobertura
./gradlew verifyBuild       # Build completo + cobertura
```

### Reportes Generados

- **Tests**: `**/build/reports/tests/test/`
- **JaCoCo HTML**: `**/build/reports/jacoco/test/html/`
- **JaCoCo XML**: `**/build/reports/jacoco/test/`

---

## 🔄 CI/CD

### GitHub Actions Workflow

El proyecto incluye un workflow completo de CI/CD (`.github/workflows/ci.yml`):

#### Características:

1. **Validación de Gradle Wrapper**: Verifica integridad del wrapper
2. **Setup de Java 21**: Usa Temurin JDK 21
3. **Caché de Gradle**: Optimiza tiempos de build
4. **Build con Reintentos**: Maneja fallos de red ocasionales
5. **Upload de Reportes**: Sube reportes de tests y cobertura como artefactos
6. **Concurrency Control**: Evita builds simultáneos de la misma rama

#### Triggers:

- Push a cualquier rama
- Pull requests a cualquier rama

#### Artefactos Generados:

- `test-reports`: Reportes de tests
- `jacoco-reports`: Reportes de cobertura JaCoCo
- `html-reports`: Reportes HTML navegables

---

## 📦 Perfiles de Configuración

El proyecto soporta múltiples perfiles de Spring Boot:

| Perfil | Archivo | Propósito |
|--------|---------|-----------|
| **local** | `application-local.yml` | Desarrollo local |
| **dev** | `application-dev.yml` | Entorno de desarrollo |
| **uat** | `application-uat.yml` | Entorno de pruebas |
| **prod** | `application-prod.yml` | Producción |

### Configuración Base (`application.yml`)

```yaml
spring:
  application:
    name: mi-aplicacion
  profiles:
    active: local

server:
  port: 8080

logging:
  level:
    root: INFO
```

---

## 🎯 Tareas Personalizadas

El proyecto incluye varias tareas personalizadas útiles:

| Tarea | Grupo | Descripción |
|--------|-------|-------------|
| `hello` | demo | Imprime un saludo desde el módulo |
| `printVersion` | versioning | Imprime la versión del proyecto |
| `testCoverage` | verification | Ejecuta tests y genera cobertura |
| `lintAll` | verification | Ejecuta todos los linters configurados |
| `verifyBuild` | verification | Build completo + cobertura |
| `dependencyUpdates` | help | Verifica actualizaciones de dependencias |

### Ejemplos de Uso

```bash
# Ver versión del proyecto
./gradlew printVersion

# Ejecutar tests con cobertura
./gradlew testCoverage

# Verificar actualizaciones disponibles
./gradlew dependencyUpdates

# Build completo de verificación
./gradlew verifyBuild
```

---

## 📊 Estado Actual del Proyecto

### Versiones Actualizadas (Enero 2025)

✅ **Spring Boot**: 3.3.6 (última versión estable)  
✅ **Spring Dependency Management**: 1.1.7  
✅ **JUnit 5**: 5.11.4  
✅ **Gradle**: 8.12  
✅ **Gradle Versions Plugin**: 0.53.0  

### Estado de Seguridad

- ✅ Todas las dependencias actualizadas a versiones estables
- ✅ Plugin de verificación de actualizaciones configurado
- ✅ No se encontraron vulnerabilidades conocidas
- ✅ Build y tests pasan correctamente

### Compatibilidad

- ✅ **Java 21**: Totalmente compatible
- ✅ **Gradle 8.12**: Compatible
- ✅ **Spring Boot 3.3.6**: Compatible
- ✅ **CI/CD**: GitHub Actions funcionando correctamente

---

## 🚀 Comandos Principales

### Build y Ejecución

```bash
# Compilar el proyecto
./gradlew clean build

# Ejecutar la aplicación
./gradlew :application:bootRun

# Generar JAR ejecutable
./gradlew :application:bootJar
```

### Testing

```bash
# Ejecutar todos los tests
./gradlew test

# Tests con cobertura
./gradlew testCoverage

# Ver reportes de cobertura
open application/build/reports/jacoco/test/html/index.html
```

### Verificación

```bash
# Verificar actualizaciones de dependencias
./gradlew dependencyUpdates

# Build completo de verificación
./gradlew verifyBuild

# Ejecutar todos los linters
./gradlew lintAll
```

---

## 📚 Relación con el eBook

Este proyecto es la implementación práctica del eBook **"Java 21 Multimódulo con Gradle 8 y Spring Boot 3"**.

### Mapeo Capítulo → Código

| Capítulo del eBook | Implementación en el Proyecto |
|-------------------|------------------------------|
| Introducción | `build.gradle.kts`, `settings.gradle.kts` |
| Modularización | Estructura de módulos (`application/`, `domain/`, etc.) |
| buildSrc | `buildSrc/` completo |
| Propiedades globales | `gradle.properties` |
| Tareas personalizadas | `CustomTasks.kt` |
| Toolchain | `ProjectConventions.kt` |
| Pruebas y cobertura | Configuración de JaCoCo y JUnit |
| CI/CD | `.github/workflows/ci.yml` |
| Arquitectura hexagonal | Separación de módulos |
| Configuración por perfiles | `config/src/main/resources/` |

---

## 🔮 Próximos Pasos y Mejoras

### Mejoras Sugeridas

1. **Agregar más módulos de ejemplo**:
   - Módulo de seguridad
   - Módulo de persistencia (JPA/Hibernate)
   - Módulo de mensajería

2. **Integración con herramientas**:
   - SonarCloud (ya preparado en CI)
   - Docker y Docker Compose
   - Kubernetes manifests

3. **Documentación adicional**:
   - Guías de desarrollo
   - Ejemplos de uso
   - Troubleshooting

4. **Testing avanzado**:
   - Tests de integración
   - Tests de contrato
   - Tests de rendimiento

---

## 📝 Notas Importantes

### Convenciones del Proyecto

- **Paquete base**: `com.tuempresa.proyecto`
- **Versión actual**: `1.0.0`
- **Java Version**: 21
- **Encoding**: UTF-8

### Estructura de Paquetes

```
com.tuempresa.proyecto
├── application/     # Punto de entrada
├── domain/          # Lógica de negocio
└── infrastructure/  # Adaptadores
```

### Buenas Prácticas Aplicadas

✅ Separación de responsabilidades  
✅ Inversión de dependencias  
✅ Configuración centralizada  
✅ Testing automatizado  
✅ CI/CD configurado  
✅ Cobertura de código  
✅ Gestión de versiones  
✅ Documentación completa  

---

## 📞 Información del Proyecto

- **Autor**: Marcos Lozina
- **Repositorio**: [GitHub](https://github.com/marcoslozina/base-java-gradle-multimodulo)
- **eBook**: Disponible en [Amazon](https://www.amazon.com/dp/B0FRR8P9KP), [Hotmart](https://go.hotmart.com/Y102830298M), [Gumroad](https://marcoslozina.gumroad.com/l/lnifg)
- **Licencia**: Todos los derechos reservados
- **ISBN**: 978-631-00-9065-8

---

**Última actualización**: Enero 2025  
**Versión del documento**: 1.0.0

