import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val hopliteVersion = "1.4.16"
val kortCoTestV = "1.6.0"
val coroutinesV = "1.6.0"
val mockk = "1.12.4"

plugins {

    id ("java")
    id ("application")
    kotlin("jvm") version "1.6.21"
    id ("org.jetbrains.kotlin.plugin.serialization") version "1.6.10"
    application
}

group = "com.sytac"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.google.oauth-client:google-oauth-client:1.18.0-rc")
    // serialization
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.3")

    // coroutines
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesV")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-reactive:$coroutinesV")
    // config loader
    implementation ("com.sksamuel.hoplite:hoplite-core:$hopliteVersion")
    implementation ("com.sksamuel.hoplite:hoplite-yaml:$hopliteVersion")
    // Logs
    implementation ("io.github.microutils:kotlin-logging-jvm:2.0.11")
    implementation ("ch.qos.logback:logback-classic:1.2.6")



    testImplementation ("org.jetbrains.kotlinx:kotlinx-coroutines-test:$kortCoTestV")
    testImplementation(kotlin("test"))
    testImplementation("io.mockk:mockk:$mockk")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}


application {
    mainClass.set("com.sytac.excercise.MainKt")
}