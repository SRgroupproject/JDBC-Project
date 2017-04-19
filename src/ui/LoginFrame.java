package hotelReception.ui;

import hotelReception.business.InputCheck;
import hotelReception.technical.DBFacade;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by suman on 4/18/2017.
 */
public class LoginFrame extends JFrame {
    private final int WIDTH = 600;
    private final int HEIGHT = 600;

    private JButton loginButton;
    private JLabel titlelabel, emailLabel, Passwordlabel, forgotPasswordLabel;
    public JTextField emailText;
    private JPasswordField passwordText;
    private JPanel logo, comp, pnl;
    protected DBFacade db;
    private final InputCheck inP;


    public LoginFrame() {InputCheck inP1;
        // inP1 = inP;
        db = new DBFacade("basicUser","1234");
        inP1 = new InputCheck();
        this.inP = inP1;
        this.setSize(WIDTH, HEIGHT);
        this.setTitle("Login Window");
        this.setBackground(Color.WHITE);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        initializeComp();
        this.setVisible(true);
    }




    /**
     * Initialize all components.
     */
    public void initializeComp() {
        ImageIcon img = new ImageIcon("logo.png");
        titlelabel = new JLabel(img);
        loginButton = new JButton("Login");

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel confirmpnl = new JPanel();
                Object[] options = {"OK"};
                if (inP.emailverification(emailText.getText()) && !inP.isEmpty(passwordText.getText())) {
                    int usertype = db.getUserType(emailText.getText(), passwordText.getText());
                    switch (usertype) {
                        case 0:
                            db = new DBFacade("hotelStaff","1234");
                            JOptionPane.showOptionDialog(confirmpnl, "Logged in as HotelStaff", "Succesfull", JOptionPane.PLAIN_MESSAGE, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                            new MainFrame(db);
                            dispose();
                            break;
                        case 1:
                            db = new DBFacade("hotelManager","1234");
                            JOptionPane.showOptionDialog(confirmpnl, "Logged in as Manager!", "Succesfull", JOptionPane.PLAIN_MESSAGE, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                            new MainFrame(db);
                            dispose();
                            break;
                        case 2:
                            db = new DBFacade("hotelReception","1234");
                            JOptionPane.showOptionDialog(confirmpnl, "Logged in as Receptionist!", "Succesfull", JOptionPane.PLAIN_MESSAGE, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                            new MainFrame(db);
                            dispose();
                            break;

                        default:
                            JOptionPane.showOptionDialog(confirmpnl, "E-Mail or Password is wrong", "Error", JOptionPane.PLAIN_MESSAGE, JOptionPane.ERROR_MESSAGE, null, options, options[0]);
                            break;
                    }

                } else {
                    JOptionPane.showOptionDialog(confirmpnl, "Something went wrong. Please check input", "Error", JOptionPane.PLAIN_MESSAGE, JOptionPane.ERROR_MESSAGE, null, options, options[0]);
                }

            }
        });
        emailLabel = new JLabel("E-Mail");
        Passwordlabel = new JLabel("Password");
        forgotPasswordLabel = new JLabel("Forgot password?");
        forgotPasswordLabel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //doStuff
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                //doStuff
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        emailText = new JTextField(15);
        passwordText = new JPasswordField(15);

        logo = new JPanel();
        logo.add(titlelabel);

        comp = new JPanel();
        comp.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.NORTHWEST;
        c.weightx = 0;
        c.weighty = 0;
        c.insets = new Insets(5, 10, 5, 10);

        c.gridy = 1;
        c.gridx = 0;
        comp.add(emailLabel, c);

        c.gridx = 1;
        comp.add(emailText, c);

        c.gridy = 2;
        c.gridx = 0;
        comp.add(Passwordlabel, c);

        c.gridx = 1;
        comp.add(passwordText, c);

        c.gridy = 3;
        comp.add(forgotPasswordLabel, c);

        c.gridy = 4;
        c.gridx = 1;
        comp.add(loginButton, c);

        pnl = new JPanel(new BorderLayout());
        pnl.add(logo, BorderLayout.NORTH);
        pnl.add(comp, BorderLayout.CENTER);
        logo.setBackground(Color.LIGHT_GRAY);
        comp.setBackground(Color.lightGray);

        this.add(pnl);
    }
}
