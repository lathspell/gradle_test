
Concepts
========

* "project" - usually one per build.gradle. settings.gradle defines if more than one build.gradle should be processed 
* "task" - each project can have multiple tasks which can depend on each other. There is no default lifecycle.

The project name is by default derived by the directory name. It is recommended though to
explicitly specify it in settings.gradle, especially if using CI build tools who might check out the
code to a random named directory.

Convention Properties
---------------------
Those are properties defined in a plugin that can be set as if they were toplevel i.e. project properties.
They can however also be set inside a task configuration. An example is "sourceCompatibility" from the Java plugin. 

Dependencies
============

Pre- and post Gradle 3.0:
    compile     -> `implementation` or `api`
    testCompile -> `testImplementation`
    -           -> `compileOnly` 

Usage
=====

    gradle tasks --all      - List tasks
    gradle -i <task>        - Loglevel INFO

    from "java" plugin:
    gradle init             - Interactive project creation
    gradle build            - Builds Java project
    gradle assemble         - Builds Java project
    gradle depdendencies    - Shows dependencies graph

Create new projects
-------------------

    gradle init --type java-library --project-name jvm-library --dsl kotlin

Plugins
=======

* Maven Publish Plugin - Alternative to Maven "install" and "deploy"
* "com.github.ben-manes.versions" - Shows outdated dependencies with ":dependencyUpdates". See https://github.com/ben-manes/gradle-versions-plugin/
* "com.github.johnrengelman.shadow" - Creates shadowed fat jars with ":shadowJar". See https://imperceptiblethoughts.com/shadow/getting-started/
* "nebula.ospackage" - Builds Debian .deb packages. See https://github.com/nebula-plugins/gradle-ospackage-plugin

Migration from Maven
====================

See https://docs.gradle.org/current/userguide/migrating_from_maven.html

Dependency Scopes:
* compile -> implementation or api
* runtime -> runtimeOnly
* test -> testImplementation or testRuntimeOnly
* provided -> compileOnly (the war plugin adds providedCompile and providedRuntime)
* import -> "implementation platform" and read chapter about BOMs in Migration page!

Templates
==========

The "template-java" directory should be used as template for new Java projects.
The build.groovy.kts-beta file might not yet be up-to-date.
