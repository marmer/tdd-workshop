import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

var javaVersion = JavaVersion.VERSION_11
val kotlinVersion = "1.5.10"

plugins {
    java
    kotlin("jvm") version "1.5.10"
    jacoco
    id("io.freefair.lombok") version "5.3.3.3"
    id("com.github.node-gradle.node") version "2.2.4"
    application
    `eclipse-wtp`
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
    testImplementation("org.junit.jupiter", "junit-jupiter", "5.7.2")
    testImplementation("org.assertj", "assertj-core", "3.19.0")
    testImplementation("org.rnorth.visible-assertions", "visible-assertions", "2.1.2")
    testImplementation("org.hamcrest", "hamcrest", "2.2")
    testImplementation("org.mockito", "mockito-core", "3.10.0")
    testImplementation("org.mockito", "mockito-junit-jupiter", "3.10.0")
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
//    dependsOn("npm_run_test")
}


tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions.javaParameters = true
    kotlinOptions.jvmTarget = javaVersion.toString()
}

tasks.findByPath("eclipse")?.dependsOn("createSourceFolders")

tasks.create("createSourceFolders") {
    arrayOf("java", "kotlin", "scripts", "resources")
        .forEach { type -> File("src/main/${type}").mkdirs() }
}

fun sourcePathDirectoriesIn(sourtePathRoot: String): String {
    return (File(rootDir, sourtePathRoot)
        .listFiles(File::isDirectory) ?: emptyArray())
        .map { it.name }
        .joinToString(",") { sourtePathRoot + "/" + it }
}
