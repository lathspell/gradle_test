import org.gradle.api.tasks.testing.logging.TestLogEvent

plugins {
    java                                                            // https://docs.gradle.org/current/userguide/java_plugin.html
    application                                                     // https://docs.gradle.org/current/userguide/application_plugin.html

    id("com.github.johnrengelman.shadow") version "5.0.0"       // https://imperceptiblethoughts.com/shadow/getting-started/ for ":shadowJar"
    id("com.bmuschko.docker-java-application") version "4.6.2"  // https://github.com/bmuschko/gradle-docker-plugin for ":docker"
    id("com.gorylenko.gradle-git-properties") version "2.0.0"   // https://github.com/n0mer/gradle-git-properties

    id("com.github.ben-manes.versions") version "0.21.0"        // https://github.com/ben-manes/gradle-versions-plugin for ":dependencyUpdates"
    id("checkstyle")                                            // https://docs.gradle.org/current/userguide/checkstyle_plugin.html
    id("pmd")                                                   // https://docs.gradle.org/current/userguide/pmd_plugin.html
}

repositories {
    mavenCentral()
}

dependencies {
    val lombokVersion = "1.18.8"

    annotationProcessor("org.projectlombok:lombok:$lombokVersion")
    compileOnly("org.projectlombok:lombok:$lombokVersion")

    testImplementation("junit:junit:4.12")
    testImplementation("org.assertj:assertj-core:3.12.2")

    implementation("ch.qos.logback:logback-core:1.2.3")
}

// See https://docs.gradle.org/current/userguide/java_plugin.html (org.gradle.api.plugins.JavaPlugin)
java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

configure<JavaPluginConvention> {
    manifest {
        attributes("Main-Class" to "de.lathspell.App")
    }
}

// See https://docs.gradle.org/current/dsl/org.gradle.api.plugins.JavaApplication.html
application {
    mainClassName = "de.lathspell.App"
    // applicationName = "template-java"
    // applicationDefaultJvmArgs = listOf("-Dgreeting.language=en")
}

// See gradle-git-properties plugin
gitProperties {
    keys = listOf("git.commit.id.abbrev", "git.build.time")
    extProperty = "gitProps" // git properties will be put in a map at project.ext.gitProps
}

tasks.withType<Jar>() {
    manifest {
        attributes(mapOf("Main-Class" to application.mainClassName))
    }
}

tasks.test {
    testLogging.showStandardStreams = true
    testLogging.minGranularity = 3
    testLogging.events = setOf(TestLogEvent.PASSED, TestLogEvent.SKIPPED, TestLogEvent.FAILED)
}

docker {
    javaApplication {
        baseImage.set("openjdk:8-jdk-alpine")
        maintainer.set("Nobody <nobody@example.com>")
    }
}
