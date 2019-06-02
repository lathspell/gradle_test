package ch10;

import java.util.Arrays;

public final class App {

    public static void main(final String[] args) {
        System.out.println("My args were: " + Arrays.deepToString(args));
        System.out.println(new App().getGreeting());
    }

    public String getGreeting() {
        return "Hello Tim";
    }
}
