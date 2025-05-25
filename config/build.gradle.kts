import com.tuempresa.proyecto.build.ProjectConventions

plugins {
    id("org.springframework.boot") version "3.2.0"
    id("io.spring.dependency-management") version "1.1.3"
    java
}

group = ProjectConventions.group
version = ProjectConventions.version

java {
    toolchain.languageVersion.set(ProjectConventions.javaVersion)
}

tasks.withType<org.springframework.boot.gradle.tasks.bundling.BootJar> {
    mainClass.set("com.tuempresa.proyecto.config.Application")
}

dependencies {
    implementation(project(":application"))
    implementation(project(":domain"))
    implementation(project(":infrastructure"))

    implementation("org.springframework.boot:spring-boot-starter")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.test {
    useJUnitPlatform()
}
