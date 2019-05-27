package ch5;

import java.util.Arrays;

public class App {

    public static void main(String[] args) {
        System.out.println("My args were: " + Arrays.deepToString(args));
        System.out.println(new App().getGreeting());
    }

    public String getGreeting() {
        return "Hello Tim";
    }
}
