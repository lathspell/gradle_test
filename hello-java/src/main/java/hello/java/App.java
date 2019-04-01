package hello.java;

public class App {

    public String getGreeting() {
        return "Hello Java!";
    }

    public static void main(String[] args) {
        System.out.println(new App().getGreeting());
    }
}
