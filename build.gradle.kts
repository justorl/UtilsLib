import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    kotlin("jvm") version "2.3.0-Beta2"
    id("com.gradleup.shadow") version "8.3.0"
}

group = "com.pulse"
version = "1.2.2"

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
    implementation("io.github.classgraph:classgraph:4.8.180")
}

val targetJavaVersion = 21
kotlin { jvmToolchain(targetJavaVersion) }

tasks {
    shadowJar {
        relocate("nonapi.io.github.classgraph", "${project.group}.utilsLib.shadow.classgraph.nonapi")
        relocate("io.github.classgraph", "${project.group}.utilsLib.shadow.classgraph")
    }

    build {
        dependsOn(shadowJar)
    }
}