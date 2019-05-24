Language
========

Comments
--------

    // single line comment
    /* block comment */

Operators
---------

    +   String concatenation
 
Literals
--------

    def x = 4               // variable declaration with type inferance

    'no expansion string'
    "string with ${variable.expansion}"     // Called "GString"; would call variable.getExpansion()
    
    1..4                    // creates an IntRange
    [ 'a', 'b', 'c' ]       // creates a List<String>

Classes, Objects and Attributes
-------------------------------

    task.name           // gets attribute "name" via `task.getName()`

Functions and Methods
---------------------

    foo(a: 1)           // calls method foo with a value for named parameter "a" 

Shortcuts
---------

    ((PluginAware) project).apply( ['plugin': 'java'] )
    project.apply( ['plugin': 'java'] )
    apply( 'plugin': 'java' )
    apply( plugin: 'java' )
    apply plugin: 'java'

Closure
-------
 
    // calls `Task task(String name, Closure configureClosure)` from class `Project`
    // `it` is the implicit closure paramter, in this case a `Task`
    task first { println 'hello: ' + it }

    // Using explicit closure parameter
    task second {
      doLast { Task task ->
        println 'hello: ' + it
      }
    }
  
    // Calls a closure as parameter to function "each" of the literally created IntRange
    (1..4).each { … }
    
    defaultTasks 'a', 'b'   // calls function `defaultTasks('a', 'b')` i.e. with an implicit list as parameter
 
