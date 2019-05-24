import java.util.ResourceBundle;

public class Hello {

    public static void main(String[] args) {
        System.out.println("Hello " + ResourceBundle.getBundle("messages").getString("name"));
        System.out.println("Hello " + Fooby.getName());
    }

}
