import org.gradle.api.tasks.testing.logging.TestLogEvent
import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.kotlin.dsl.register

fun Project.configureCustomTasks() {
    tasks.register("testCoverage") {
        group = "verification"
        description = "Ejecuta tests y genera reporte de cobertura"
        dependsOn("test", "jacocoTestReport")
    }
}
