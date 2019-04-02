plugins {
    java                    // https://docs.gradle.org/current/userguide/java_plugin.html
    application             // https://docs.gradle.org/current/userguide/application_plugin.html
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

application {
    mainClassName = "de.lathspell.App"
}

tasks.withType<Jar> {
    manifest {
        attributes(mapOf("Main-Class" to "de.lathspell.App"))
    }

    // This gives a fat jar!
    doFirst {
        from {
            configurations.runtimeClasspath. {
                it.isDirectory() ? it : zipTree(it)
            }
        }
    }
}

repositories {
    mavenCentral()
}

dependencies {
    annotationProcessor("org.projectlombok:lombok:1.18.6")
    compileOnly("org.projectlombok:lombok:1.18.6")

    testImplementation("junit:junit:4.12") {
        exclude(group = "org.hamcrest")
    }
    testImplementation("org.assertj:assertj-core:3.11.1")

    implementation("ch.qos.logback:logback-core:1.2.3")
}
