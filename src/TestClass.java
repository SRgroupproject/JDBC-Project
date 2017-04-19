import javax.swing.*;

/**
 * Created by Louise Windows on 07-04-2017.
 */
public class TestClass {
    public static void main(String[] args) {
        JFrame frame = new LoginUI();
        frame.setTitle("Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        DBController controller = new DBController();
        System.out.println(controller.findUserByUserName("Peter").toString());


    }
}
