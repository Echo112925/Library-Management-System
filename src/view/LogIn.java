package view;

import controller.LoginController;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LogIn extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtUserName;
    private JPasswordField txtPassword;  // changed to JPasswordField
    private JButton btnLogIn;
    private LoginController loginController;  // added

    public LogIn() {
        loginController = new LoginController();  // added
        executeCode();
    }

    public void executeCode() {
        setupFrame();
    }

    public void setupFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 458, 477);
        contentPane = new JPanel();
        contentPane.setBackground(Color.LIGHT_GRAY);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        txtUserName = new JTextField();
        txtUserName.setBounds(101, 196, 206, 20);
        contentPane.add(txtUserName);
        txtUserName.setColumns(10);

        txtPassword = new JPasswordField();  // changed to JPasswordField
        txtPassword.setColumns(10);
        txtPassword.setBounds(101, 242, 206, 20);
        contentPane.add(txtPassword);

        btnLogIn = new JButton("Log In");
        btnLogIn.setBounds(156, 306, 89, 23);
        contentPane.add(btnLogIn);
        setLocationRelativeTo(null);

        // added action listener
        btnLogIn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleLogin();
            }
        });
    }

    private void handleLogin() {
        String userName = txtUserName.getText().trim();
        String password = new String(txtPassword.getPassword()).trim();

        // Check if empty
        if (userName.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Please fill in all fields.",
                "Warning",
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Check login via controller
        boolean success = loginController.login(userName, password);

        if (success) {
            JOptionPane.showMessageDialog(this,
                "Login successful!",
                "Success",
                JOptionPane.INFORMATION_MESSAGE);
            this.dispose(); // close login form
            // TODO: open Dashboard
            // new Dashboard().setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this,
                "Invalid username or password.",
                "Login Failed",
                JOptionPane.ERROR_MESSAGE);
        }
    }
}