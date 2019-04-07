plugins {
    java
    application             // https://docs.gradle.org/current/userguide/application_plugin.html
    // id("com.github.ben-manes.versions") version "0.21.0"  // https://github.com/ben-manes/gradle-versions-plugin for ":  dependencyUpdates"
    // id("com.github.johnrengelman.shadow") version "5.0.0" // https://imperceptiblethoughts.com/shadow/getting-started/ for ":shadowJar"
    // id("com.palantir.docker") version "0.21.0"            // https://github.com/palantir/gradle-docker/ for ":docker"
}

group = "de.lathspell"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testCompile("junit", "junit", "4.12")
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}

configure<ApplicationPluginConvention> {
    mainClassName = "de.lathspell.App"
}