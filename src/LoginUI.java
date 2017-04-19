import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginUI extends JFrame {
    private static final int FRAME_WIDTH = 350;
    private static final int FRAME_HEIGHT = 200;
    private static final int COLUMN_SIZE = 10;

    private JButton loginButton;
    private JLabel userLabel;
    private JLabel passwordLabel;
    private JTextField userTextfield;
    private JPasswordField passwordTextfield;

    public LoginUI() throws HeadlessException {
        createComponents();
    }

    public void createComponents(){
        loginButton = new MyButton("Login");

        loginButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                LoginController login = new LoginController();
                //login.loginValidation(userTextfield.getText(), passwordTextfield.getText());
            }
        });

        userTextfield = new JTextField(COLUMN_SIZE);
        userTextfield.setFont(new Font("Ariel", Font.PLAIN, 20));
        passwordTextfield = new JPasswordField(COLUMN_SIZE);
        passwordTextfield.setFont(new Font("Ariel", Font.PLAIN, 20));
        userLabel = new MyJLabel("Username:");
        passwordLabel = new MyJLabel("Password:");
        JPanel panel = new JPanel();
        panel.add(userLabel);
        panel.add(userTextfield);
        panel.add(passwordLabel);
        panel.add(passwordTextfield);
        panel.add(loginButton);
        this.add(panel);
        this.setSize(FRAME_WIDTH,FRAME_HEIGHT);
    }




}
