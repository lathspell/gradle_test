package de.lathspell;

import org.junit.Test;

public class AppTest {

    @Test
    public void testAppHasAGreeting() {
        assertThat(new App().getGreeting()).isEqualTo("Hello World!");
    }
}
