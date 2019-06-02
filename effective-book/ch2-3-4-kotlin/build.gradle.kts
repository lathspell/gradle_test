defaultTasks("first", "second")

task(name = "ch2_1") {
    doLast {
        println("hello: $this")   // it is of type "Task" and has a `toString()` method
    }
}

task(name = "ch2_2") {
    doLast {
        println("hello: $name")
    }
}

task(name = "ch2_4") {
    doLast {
        val ir = (1..4)
        ir.forEach {
            println("iterating $it")
        }

        val l = listOf("a", "b", "c")
        println("list: $l with ${l.size} elements; joined=" + l.joinToString("-") { it.toUpperCase() })
    }
}

// creates empty task
task("ch2_part1") {
    doFirst {
        println(name)
    }
}
task("ch2_part2") {
    doFirst {
        println(name)
    }
}
task("ch2_5")
tasks["ch2_5"].dependsOn("ch2_part1")
tasks["ch2_5"].dependsOn("ch2_part2")

task("ch2_6")
listOf("ch2_part1", "ch2_part2").forEach { tasks["ch2_6"].dependsOn(it) }

fun Task.printTaskName() {
    println("Running $name")
}

task("ch2_7") {
    doLast {
        println("Before calling printTaskName()")
        printTaskName()
        println("After calling printTaskName()")
    }
}

task(name = "ch2_8") {
    dependsOn("ch2_part1")
    doLast {
        printTaskName()
    }
}

task("ch2_9") {
    doFirst {
        println("in doFirst")
    }
    doLast {
        println("entering doLast")
        printTaskName()
        println("leaving doLast")
    }
}

tasks["ch2_9"].dependsOn(project.tasks.filter { it.name.startsWith("ch2_part") })

task(name = "ch2_10") {
    description = "I am 10"
}

val exampleGroupName = "Chapter 2 Eleven"
task("ch2_11_a") {
    group = "exampleGroupName"
    description = "First task of this group"
}
task(name = "ch2_11_b", args = mapOf("group" to "exampleGroupName", "description" to "Second task of this group"))


//
// chapter 3
//

task("ch3_1") {
    description = "Working with files"
    doLast {
        val bg = file("build.gradle.kts")
        val bgFile = File("build.gradle.kts")
        val bg2 = project.file(bgFile, PathValidation.FILE)

        println("build.gradle size is ${bg2.length()} bytes and bg == bg2: " + (bg.length() == bg2.length()))
    }
}

task("ch3_2") {
    description = "Working with file trees"
    doLast {
        // val collection : Set<File> = fileTree(".").include("*.gradle").resolve() - deprecated
        val collection: Set<File> = fileTree(".").matching { include("*.gradle.kts") }.toSet()
        val bg1 = collection.first { it.name.startsWith("build") }
        println("bg1: $bg1")

        val bg2 = fileTree(".").matching {
            include("*.gradle.kts")
            exclude("settings.*")
        }.first()
        println("bg2: $bg2")
    }
}

task<Zip>("ch3_3") {
    description = "Creating ./build/zips/ch3_3.zip"

    from(".")                    // from this directory
    include("build.grad*")          // take those file

    destinationDirectory.set(file("./build/zips/"))  // and create in this directory
    archiveFileName.set("ch3_3.zip")                      // that file

    doLast {
        println("Task with type " + this::class + " created build/zips/ch3_3.zip")
    }
}

task("ch3_4") {
    description = "Working with external Properties"
    doLast {
        // Override on the CLI with "-PmyNumber=2" or "ORG_GRADLE_PROJECT_myNumber=4" environment variable
        println("Property myNumber is " + project.property("myNumber"))
    }
}

task("ch3_5") {
    description = "Logging"
    doLast {
        logger.debug("This is a DEBUG message")
        logger.info("This is a INFO message")
        logger.lifecycle("This is a LIFECYCLE message")
        logger.warn("This is a WARNING message")
        logger.quiet("This is a QUIET message")
        logger.error("This is a ERROR message")
    }
}

task("ch4_properties") {
    description = "prints important properties of the Java plugin"
    apply(plugin = "java")
    doLast {
        // => sourceSets.getByName("main") { … }
        /* +++ see ch4_conventions +++
        java.sourceSets {
            main {
                println("java.srcDirs = ${java.srcDirs}")
                println("resources.srcDirs = ${resources.srcDirs}")
                println("java.files = ${java.files.name}")
                println("allJava.files = ${allJava.files.name}")
                println("resources.files = ${resources.files.name}")
                println("allSource.files = ${allSource.files.name}")
                println("output.resourcesDir = ${output.resourcesDir}")
                println("output.files = ${output.files}")
            }
         }
         */
    }
}

task("ch4_conventions") {
    apply(plugin = "java")
    description = "prints some properties of the 'convention' object"
    doLast {
        // All the same!
        // println("sourceSets.main.name: $sourceSets.main.name")
        // println( "project.sourceSets.main.name: ${project.sourceSets.main.name}")
        // println ("project.plugins.java.sourceSets.main.name: ${project.convention.plugins.java.sourceSets.main.name}")
        println("project.plugins.java.sourceSets.main.name: ${(project.convention["sourceSets"] as SourceSetContainer).get("main").name}")
        println("project.plugins.java.sourceSets.main.name: ${(project.convention["java"] as JavaPluginExtension).sourceCompatibility}")
    }
}

//
// Bottom of file!
//

// Put all tasks into the group "chapter3". Executing in configuring phase. Must be executed after all tasks were defined!
project.tasks
        .filter { it.name.matches(Regex("^ch[0-9]+_.*")) }
        .forEach { it.group = it.name.replaceFirst("_.*", "").replaceFirst("^ch", "Chapter ") }
