package de.lathspell;

public class App {
    public String getGreeting() {
        return new Greeting("Hello world.").getGreeting();
    }

    public static void main(String[] args) {

        System.out.println(new App().getGreeting());
    }
}
