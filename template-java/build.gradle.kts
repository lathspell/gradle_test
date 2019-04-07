plugins {
    java                    // https://docs.gradle.org/current/userguide/java_plugin.html
    application             // https://docs.gradle.org/current/userguide/application_plugin.html
    id("com.github.ben-manes.versions") version "0.21.0"  // https://github.com/ben-manes/gradle-versions-plugin for ":  dependencyUpdates"
    id("com.github.johnrengelman.shadow") version "5.0.0" // https://imperceptiblethoughts.com/shadow/getting-started/ for ":shadowJar"
    id("com.palantir.docker") version "0.21.0"            // https://github.com/palantir/gradle-docker/ for ":docker"
}

// From https://docs.gradle.org/current/userguide/java_plugin.html
configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8

    manifest {
        attributes (
            mapOf("Main-Class" to "de.lathspell.App")
        )
    }
}

configure<ApplicationPluginConvention> {
    mainClassName = "de.lathspell.App"
}

/*
tasks {
    // https://docs.gradle.org/current/userguide/java_plugin.html
    jar {
        manifest {
            attributes(mapOf("Main-Class" to "de.lathspell.mainClassName"))
        }
    }

    // Run with "./gradlew clean shadowJar docker" to ensure that the fat jar is up to date
    docker {
      //  name("lathspell/foo:latest")
        // copies the files into build/docker where they can be found by "COPY template-java-groovy-all.jar /foo.jar"
        files("build/libs/template-java-groovy-all.jar")
        noCache(true)
    }
}
*/

repositories {
    mavenCentral()

}

// This does something similar to project.ext.set($closureWithMap). The properties are referenced simply as $foo
// but also available as "$project.ext.foo" or "$project.foo".
// See https://docs.gradle.org/current/dsl/org.gradle.api.plugins.ExtraPropertiesExtension.html
// ext {
//    lombokVersion = "1.18.6"
// }
// ... This does not work with Kotlin, instead we use a local val in dependencies.

dependencies {
    val lombokVersion = "1.18.6"

    annotationProcessor("org.projectlombok:lombok:$lombokVersion")
    compileOnly("org.projectlombok:lombok:$lombokVersion")

    testImplementation("junit:junit:4.12")
    testImplementation("org.assertj:assertj-core:3.11.1")

    implementation("ch.qos.logback:logback-core:1.2.3")
}
