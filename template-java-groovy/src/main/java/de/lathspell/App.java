package de.lathspell;

public class App {

    public String getGreeting() {
        return "Hello " + new Greeting("Tim").getName();
    }

    public static void main(String[] args) {
        System.out.println(new App().getGreeting());
    }
}
