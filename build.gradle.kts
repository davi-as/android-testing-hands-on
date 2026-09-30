plugins {
    kotlin("jvm") version "1.9.10"
}

repositories {
    mavenCentral()
}

dependencies {
    // Appium
    implementation("io.appium:java-client:9.2.0")

    // Kotlin
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.9.10")

    // JUnit 5
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.10.0")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.10.0")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.10.0")

    // Cucumber
    testImplementation("io.cucumber:cucumber-junit-platform-engine:7.14.0")
    testImplementation("io.cucumber:cucumber-java:7.14.0")
    testImplementation("org.junit.platform:junit-platform-suite:1.10.0")

    // Logging
    testImplementation("org.slf4j:slf4j-simple:2.0.9")

    // Assertions
    testImplementation("org.assertj:assertj-core:3.24.1")
}

tasks.test {
    useJUnitPlatform()

    // Testes de UI dependem do estado do emulador: nunca reaproveitar resultado anterior
    outputs.upToDateWhen { false }

    // Parallelization config
    systemProperties["junit.jupiter.execution.parallel.enabled"] = "false" // set to true for parallel runs
    systemProperties["junit.jupiter.execution.parallel.mode.default"] = "concurrent"
    systemProperties["junit.jupiter.execution.parallel.mode.classes.default"] = "concurrent"
}

kotlin {
    jvmToolchain(17)
}
