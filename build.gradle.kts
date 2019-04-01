allprojects {
    tasks.register("hello") {
        doLast {
            println("I'm ${this.project.name}")
        }
    }
}

subprojects {
    tasks.named("hello") {
        doLast {
            println("- I depend on gradle_test")
        }
    }
}

project(":getting-started").tasks.named("hello") {
    doLast {
        println("- I'm ${this.project.name} under :getting-started.")
    }
}

configure(subprojects.filter { it.name != "getting-started" }) {
    tasks.named("hello") {
        doLast {
            println("- I'm not from getting-started.")
        }
    }
}
