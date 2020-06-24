import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

var javaVersion = JavaVersion.VERSION_12
val kotlinVersion = "1.3.72"

plugins {
    java
    kotlin("jvm") version "1.3.72"
    jacoco
    id("io.freefair.lombok") version "5.0.1"
    id("com.github.node-gradle.node") version "2.2.4"
    application
}

//Can be run wich gradle run --args="..."
application {
    mainClass.set("io.github.marmer.ApplicationKt")
}

group = "io.github.marmer.kata"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8", kotlinVersion))
    implementation(kotlin("reflect", kotlinVersion))
    testImplementation(kotlin("test", kotlinVersion))
    testImplementation(kotlin("test-junit5", kotlinVersion))
    testImplementation("org.junit.jupiter", "junit-jupiter", "5.6.2")
    testImplementation("org.assertj", "assertj-core", "3.16.1")
    testImplementation("org.hamcrest", "hamcrest-core", "2.2")
    testImplementation("org.mockito", "mockito-core", "3.3.3")
    testImplementation("org.mockito", "mockito-junit-jupiter", "3.3.3")
    testImplementation("com.nhaarman.mockitokotlin2", "mockito-kotlin", "2.2.0")
}

configure<JavaPluginConvention> {
    sourceCompatibility = javaVersion
    targetCompatibility = javaVersion
}

tasks.jacocoTestReport {
    reports {
        xml.isEnabled = true
        csv.isEnabled = false
        html.destination = file("${buildDir}/jacocoHtml")
    }
}

tasks.withType<Test>() {
    useJUnitPlatform()
    testLogging {
        events(
            TestLogEvent.FAILED,
            TestLogEvent.PASSED,
            TestLogEvent.SKIPPED,
            TestLogEvent.STANDARD_OUT,
            TestLogEvent.STANDARD_ERROR
        )

        exceptionFormat = TestExceptionFormat.FULL
        showExceptions = true
        showCauses = true
        showStackTraces = true
    }
    dependsOn("npm_run_test")
}


tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions.javaParameters = true
    kotlinOptions.jvmTarget = javaVersion.toString()
}

fun sourcePathDirectoriesIn(sourtePathRoot: String): String {
    return (File(rootDir, sourtePathRoot)
        .listFiles(File::isDirectory) ?: emptyArray())
        .map { it.name }
        .joinToString(",") { sourtePathRoot + "/" + it }
}
