Multi-Module-Project
====================

How many settings.gradle?
-------------------------

Gradle supports an arbitrary deeply nested structure but it only seems to parse the
`settings.gradle` files at the top-level directory.

In this example it does find the `:foo:furious` as it's included in the
top-level `settings.gradle`but not `barious` which is included from the
`bar/settings.gradle`.

    Root project 'multi-module'
    +--- Project ':bar'
    \--- Project ':foo'
         \--- Project ':foo:furious'

