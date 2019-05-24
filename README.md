
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
    gradle -q/-i <task>     - Loglevel Quiet or INFO
    gradle --profile <task> - Emits a nice HTML page with statistics 

    from "java" plugin:
    gradle init             - Interactive project creation
    gradle build            - Builds Java project
    gradle assemble         - Builds Java project
    gradle depdendencies    - Shows dependencies graph

Gradle kann über ~/.gradle.properties und ./gradle.properties konfiguriert werden.
Beispiele:

    org.gradle.caching = true           // Aktiviert den Build-Cache
    org.gradle.console = plain          // Zeigt auch ohne "--info" die Namen der Lifecycle Tasks an

Gradle Wrapper
--------------

The bundled Gradle version can be updated. The version has to be specified as it defaults to
recreate the scripts for the currently installed version. Distribution type BIN only loads the
necessary binaries while ALL also includes the sources that are usefull for IDE autocompletion. 

    ./gradlew wrapper --gradle-version=5.3.1 --distribution-type=ALL 

Gradle Daemon
-------------

Can be controlled via CLI with `--no-daemon/--daemon` or `org.gradle.daemon=true` in `gradle.properties`.

Create new projects
-------------------

    gradle init --type java-library --project-name jvm-library --dsl kotlin

Plugins
=======

* "com.dorongold.task-tree" - Shows the task tree for a specific task. See https://plugins.gradle.org/plugin/com.dorongold.task-tree
* Maven Publish Plugin - Alternative to Maven "install" and "deploy"
* "com.github.ben-manes.versions" - Shows outdated dependencies with ":dependencyUpdates". See https://github.com/ben-manes/gradle-versions-plugin/
* "com.github.johnrengelman.shadow" - Creates shadowed fat jars with ":shadowJar". See https://imperceptiblethoughts.com/shadow/getting-started/
* "nebula.ospackage" - Builds Debian .deb packages. See https://github.com/nebula-plugins/gradle-ospackage-plugin
* "com.gorylenko.gradle-git-properties" - Creates git.properties file with current GIT revsion

Migration from Maven
====================

See https://docs.gradle.org/current/userguide/migrating_from_maven.html

Dependency Scopes:
* compile -> implementation or api
* runtime -> runtimeOnly
* test -> testImplementation or testRuntimeOnly
* provided -> compileOnly (the war plugin adds providedCompile and providedRuntime)
* import -> "implementation platform" and read chapter about BOMs in Migration page!

Rule-Tasks
==========

Some plugins offer rules like e.g. this one from the Java plugin which allows commands to
use "cleanJar" to clean the output of the "Jar" task.

    Rules
    -----
    Pattern: clean<TaskName>: Cleans the output files of a task.

Templates
==========

The "template-java" directory should be used as template for new Java projects.
The build.groovy.kts-beta file might not yet be up-to-date.

Kotlin
======

I found two ways to configure plugins using the Kotlin DSL. The first one refers to
`ApplicationPluginConvention` and the latter to `JavaApplication`. Not all attributes
are equally available. Not sure what this means so the shorter version wins for now :)  

```
configure<ApplicationPluginConvention> {
    mainClassName = "de.lathspell.App"
}

    vs.
    
application {
    mainClassName = "de.lathspell.App"
}
```

Caveats
=======

Make sure that Project Settings refer to a valid JDK or else the Kotlin DSL files will give error messages like:
"Cannot access class 'java.lang.String'. Check your module classpath for missing or conflicting dependencies."

In Intellij IDEA the `build.gradle.kts` had red underlined configuration blocks for some plugins for hours.
They suddenly vanished after a simple "Code -> reformat".

Writing Tasks
=============

Task Definition
---------------

`task(…)` with the following noteworthy parameters (see )
* dependsOn
* description
* group

