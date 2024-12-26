plugins {
    id("java")
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "pl.tomasz_kicikici"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.apache.httpcomponents:httpclient:4.5.13")
    implementation("net.java.dev.jna:jna-platform:5.10.0")
    implementation("com.github.sarxos:webcam-capture:0.3.12")
    implementation("org.xerial:sqlite-jdbc:3.41.2.1")
    implementation("org.json:json:20210307")
}

tasks.test {
    useJUnitPlatform()
}
tasks.jar {
    manifest {
        attributes["Main-Class"] = "ru.xyz.test.Testy"
    }
}


tasks.withType<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar> {
    archiveClassifier.set("")
    archiveFileName.set("${project.name}-${project.version}.jar")
    manifest {
        attributes["Main-Class"] = "ru.xyz.test.Testy"
    }
}