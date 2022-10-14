import org.testng.annotations.Test;

public class FirstTestcase {
    @Test
    void setup()
    {
        System.out.println("Opening browser");
    }
    @Test
    void login()
    {
        System.out.println("Login page");
    }
    @Test
    void quiting(){
        System.out.println("closing thr browser");
    }
}
