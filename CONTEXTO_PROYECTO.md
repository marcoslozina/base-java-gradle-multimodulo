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

### Version Catalog: Evolución Moderna (Recomendado)

> **Nota**: Aunque este proyecto usa `buildSrc`, **Gradle Version Catalog** es la evolución moderna recomendada por Gradle para gestionar dependencias y versiones.

**Version Catalog** (disponible desde Gradle 7.0) ofrece ventajas significativas:

#### Ventajas sobre buildSrc

1. **Mejor rendimiento**: No requiere compilación previa como `buildSrc`
2. **Soporte IDE superior**: Autocompletado nativo en IntelliJ IDEA y Android Studio
3. **Formato declarativo**: Archivo TOML legible y mantenible (`gradle/libs.versions.toml`)
4. **Compartible entre proyectos**: Fácil de compartir entre múltiples repositorios
5. **Type-safe en Kotlin DSL**: Acceso type-safe con `libs`

#### Ejemplo de Version Catalog

```toml
# gradle/libs.versions.toml
[versions]
spring-boot = "3.3.6"
junit = "5.11.4"

[libraries]
spring-boot-starter-web = { 
    module = "org.springframework.boot:spring-boot-starter-web", 
    version.ref = "spring-boot" 
}
junit-jupiter = { 
    module = "org.junit.jupiter:junit-jupiter", 
    version.ref = "junit" 
}

[bundles]
spring-web = ["spring-boot-starter", "spring-boot-starter-web"]
testing = ["junit-jupiter", "spring-boot-starter-test"]

[plugins]
spring-boot = { 
    id = "org.springframework.boot", 
    version.ref = "spring-boot" 
}
```

**Uso en build.gradle.kts:**
```kotlin
plugins {
    alias(libs.plugins.spring.boot)
}

dependencies {
    implementation(libs.spring.boot.starter.web)
    implementation(libs.bundles.spring.web)
    testImplementation(libs.bundles.testing)
}
```

> 💡 **Recomendación**: Para proyectos nuevos, considera usar **Version Catalog** en lugar de `buildSrc`. Para proyectos existentes con convention plugins, `buildSrc` sigue siendo válido.

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
| **Version Catalog** | *Recomendado para proyectos nuevos* (ver sección Version Catalog) |
| Propiedades globales | `gradle.properties` |
| Tareas personalizadas | `CustomTasks.kt` |
| Toolchain | `ProjectConventions.kt` |
| Pruebas y cobertura | Configuración de JaCoCo y JUnit |
| **IA para optimización** | *Sección sobre IA en desarrollo con Gradle* |
| CI/CD | `.github/workflows/ci.yml` |
| Arquitectura hexagonal | Separación de módulos |
| Configuración por perfiles | `config/src/main/resources/` |

### Contenido del eBook

El eBook incluye capítulos específicos sobre:

- **Capítulo 5.5**: Version Catalog - La evolución moderna de buildSrc
- **Capítulo 8**: Optimización de builds - Incluye sección sobre IA para optimización y análisis de builds

Estos temas están documentados en este contexto del proyecto para referencia completa.

---

## 🤖 Inteligencia Artificial en el Desarrollo con Gradle

Las herramientas de **Inteligencia Artificial** están transformando cómo desarrollamos y optimizamos proyectos Gradle, ofreciendo asistencia inteligente para generar código, detectar problemas y optimizar configuraciones.

### Asistentes de IA para Generar Código Gradle

Herramientas como **GitHub Copilot**, **Cursor**, **Codeium** o **IntelliJ AI Assistant** pueden generar código Gradle de forma efectiva con los prompts adecuados.

#### Prompts Efectivos para IA

**Generar build.gradle.kts básico:**
```
Genera un build.gradle.kts para Java 21 con Spring Boot 3.3.6, 
usando Kotlin DSL, con soporte para tests con JUnit 5 y Lombok.
```

**Generar libs.versions.toml:**
```
Crea un libs.versions.toml con Spring Boot 3.3.6, JUnit 5.11.4, 
Jackson 2.16.1 y Lombok 1.18.30. Incluye bundles para spring-web 
y testing.
```

**Generar task personalizada:**
```
Crea una task Gradle en Kotlin DSL que genere reportes de análisis 
estático, usando inputs y outputs para incremental builds.
```

### Análisis de Builds con IA

Las herramientas de IA pueden analizar configuraciones de Gradle y detectar problemas comunes:

#### Detección de Problemas Comunes

**Problemas que IA puede detectar:**
- ✅ Versiones dinámicas (`+`) que afectan reproducibilidad
- ✅ Dependencias duplicadas o conflictos de versiones
- ✅ Tasks no optimizadas (faltan inputs/outputs)
- ✅ Configuraciones que impiden cacheo
- ✅ Uso ineficiente de recursos (workers, memoria)

**Ejemplo de análisis con IA:**
```
Analiza este build.gradle.kts y detecta:
1. Problemas de performance
2. Dependencias duplicadas
3. Oportunidades de optimización
4. Mejores prácticas no aplicadas
```

### Optimización Automática de Dependencias

Las herramientas de IA pueden sugerir optimizaciones en la gestión de dependencias:

#### Casos de Uso Prácticos

**1. Migración Maven → Gradle asistida por IA**

**Prompt:**
```
Convierte este pom.xml a build.gradle.kts usando Kotlin DSL, 
con Version Catalog para dependencias.
```

**2. Optimizar libs.versions.toml**

**Prompt:**
```
Analiza este libs.versions.toml y sugiere:
- Agrupar dependencias relacionadas en bundles
- Detectar versiones desactualizadas
- Identificar dependencias no utilizadas
```

**3. Generar tasks optimizadas**

**Prompt:**
```
Genera una task Gradle para ejecutar análisis estático de código 
que sea incremental, cacheable y use parallel execution.
```

### Mejores Prácticas para Usar IA con Gradle

1. **Prompts específicos y contextuales**
   - Incluir versión de Gradle, Java y Spring Boot
   - Especificar si usas Kotlin DSL o Groovy
   - Mencionar si usas Version Catalog o buildSrc

2. **Validar siempre el código generado**
   - Probar en proyecto de prueba primero
   - Verificar que compila correctamente
   - Revisar que sigue mejores prácticas

3. **Usar IA como asistente, no como reemplazo**
   - Entender el código generado
   - Ajustar según necesidades específicas
   - Aprender de las sugerencias

4. **Iterar y refinar**
   - Mejorar prompts basándose en resultados
   - Combinar múltiples sugerencias
   - Adaptar a tu contexto específico

### Herramientas Recomendadas

- **GitHub Copilot**: Integrado en VS Code, IntelliJ IDEA
- **Cursor**: Editor con IA integrada
- **Codeium**: Alternativa gratuita a Copilot
- **IntelliJ AI Assistant**: Integrado en IntelliJ IDEA Ultimate
- **ChatGPT/Claude**: Para análisis y consultas complejas

> ⚠️ **Nota importante:** Las herramientas de IA son asistentes poderosos, pero siempre debes revisar y validar el código generado. El conocimiento de Gradle sigue siendo esencial para tomar decisiones correctas.

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

5. **Migración a Version Catalog**:
   - Considerar migrar de `buildSrc` a Version Catalog
   - Aprovechar mejor rendimiento y soporte IDE
   - Mantener `buildSrc` solo para convention plugins

6. **Integración con IA**:
   - Usar asistentes de IA para generar código Gradle
   - Aprovechar IA para análisis y optimización de builds
   - Validar siempre el código generado por IA

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

