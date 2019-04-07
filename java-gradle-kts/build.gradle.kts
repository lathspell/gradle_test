import org.gradle.kotlin.dsl.codegen.generateApiExtensionsJar

plugins {
    java                    // https://docs.gradle.org/current/userguide/java_plugin.html
    application             // https://docs.gradle.org/current/userguide/application_plugin.html
    id("com.github.ben-manes.versions") version "0.21.0"        // https://github.com/ben-manes/gradle-versions-plugin for ":  dependencyUpdates"
    id("com.github.johnrengelman.shadow") version "5.0.0"       // https://imperceptiblethoughts.com/shadow/getting-started/ for ":shadowJar"
    // id("com.palantir.docker") version "0.21.0"                   // https://github.com/palantir/gradle-docker/ for ":docker"
    id("com.bmuschko.docker-java-application") version "4.6.2"  // https://github.com/palantir/gradle-docker/ for ":docker"
}

group = "de.lathspell"

repositories {
    mavenCentral()
}

dependencies {
    val lombokVersion = "1.18.6"

    annotationProcessor(group = "org.projectlombok", name = "lombok", version = lombokVersion)
    compileOnly(group = "org.projectlombok", name = "lombok", version = lombokVersion)

    testImplementation("junit:junit:4.12")
    testImplementation("org.assertj:assertj-core:3.11.1")

    implementation("ch.qos.logback:logback-core:1.2.3")
}

// From https://docs.gradle.org/current/userguide/java_plugin.html
java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

/*
configure<ApplicationPluginConvention> {
    mainClassName = "de.lathspell.App"
}
*/
application {
    mainClassName = "de.lathspell.App"
}

docker {
    javaApplication {
        baseImage.set("openjdk:8-jdk-alpine")
        maintainer.set("Nobody <nobody@example.com>")
    }
}