plugins {
    kotlin("jvm") version "2.3.0-Beta2"
    id("com.gradleup.shadow") version "8.3.0"
    `maven-publish`
}

group = "com.pulse"
version = "2.0.1"

val targetJavaVersion = 21

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

kotlin { jvmToolchain(targetJavaVersion) }

tasks {
    shadowJar {
        relocate("nonapi.io.github.classgraph", "${project.group}.utilslib.shadow.classgraph.nonapi")
        relocate("io.github.classgraph", "${project.group}.utilslib.shadow.classgraph")
        archiveClassifier.set("")
    }

    build {
        dependsOn(shadowJar)
    }

    jar {
        enabled = false
    }
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = project.group.toString()
            artifactId = "utilslib"
            version = project.version.toString()

            artifact(tasks.shadowJar.get()) {
                builtBy(tasks.shadowJar)
            }

            pom {
                name.set("UtilsLib")
                description.set("A shared code library for Paper plugins")
                url.set("https://github.com/justorl/UtilsLib")

                licenses {
                    license {
                        name.set("MIT License")
                        url.set("https://opensource.org/licenses/MIT")
                    }
                }
            }
        }
    }
}