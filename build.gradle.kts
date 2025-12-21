import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    kotlin("jvm") version "2.3.0-Beta2"
    id("com.gradleup.shadow") version "8.3.0"
    id("xyz.jpenilla.run-paper") version "2.3.1"
}

group = "com.pulse"
version = "1.2.1"

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/") { name = "papermc-repo" }
    maven("https://repo.tcoded.com/releases") { name = "tcoded" }
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.21.8-R0.1-SNAPSHOT")
    compileOnly("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    compileOnly("net.megavex:scoreboard-library-api:2.4.3")
    compileOnly("dev.jorel:commandapi-paper-shade:11.1.0")
    compileOnly("com.tcoded:FoliaLib:0.5.1")
    implementation("org.reflections:reflections:0.10.2")
}

val targetJavaVersion = 21
kotlin { jvmToolchain(targetJavaVersion) }

tasks.processResources {
    val props = mapOf("version" to version)
    inputs.properties(props)
    filteringCharset = "UTF-8"
    filesMatching("plugin.yml") { expand(props) }
}