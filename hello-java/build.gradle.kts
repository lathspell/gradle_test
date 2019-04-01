
plugins {
    java
    application     // gives "run" task and application.mainClassName support
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.google.guava:guava:27.0.1-jre")

    testImplementation("junit:junit:4.12")
}

application {
    applicationDefaultJvmArgs = listOf("-Dfoo=bar")
    mainClassName = "hello.java.App"
}
