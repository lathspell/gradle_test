package ch6;

import java.util.Arrays;

public class App {
    public String getGreeting() {
        return "Hello world.";
    }

    public static void main(String[] args) {
        System.out.println("Args: " + Arrays.deepToString(args));
        System.out.println("Property foo: " + System.getProperty("foo"));
        System.out.println(new App().getGreeting());
    }
}
