package view;

import controller.LoginController;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;
import java.awt.Color;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class LogIn extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtUserName;
    private JPasswordField txtPassword;  // changed to JPasswordField
    private JButton btnLogIn;
    private LoginController loginController;  // added
    private JRadioButton rbtnShow;
    private JLabel logo;

    public LogIn() {
        loginController = new LoginController();  // added
        executeCode();
    }
 
    public void executeCode() {
        setupFrame();
        setLocationRelativeTo(null);

    }

    public void setupFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setBounds(100, 100, 347, 381);
        contentPane = new JPanel();
        contentPane.setBackground(Color.LIGHT_GRAY);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        txtUserName = new JTextField();
        txtUserName.setBounds(49, 189, 247, 20);
        contentPane.add(txtUserName);
        txtUserName.setColumns(10);

        txtPassword = new JPasswordField();  // changed to JPasswordField
        txtPassword.setColumns(10);
        txtPassword.setBounds(49, 213, 247, 20);
        txtPassword.setEchoChar('*'); // default view ng jpasswordfield, kahit walng ganyan
        contentPane.add(txtPassword);

        btnLogIn = new JButton("Log In");
        btnLogIn.setBounds(112, 282, 104, 20);
        contentPane.add(btnLogIn);
        
        rbtnShow = new JRadioButton("show");
        rbtnShow.setBounds(49, 240, 65, 23);
        contentPane.add(rbtnShow);
        
        JLabel logo = new JLabel();
		ImageIcon icon = new ImageIcon(AdminDash.class.getResource("/img/images__1_-removebg-preview.png"));
		// resize image to fit label bounds
		Image img = icon.getImage();
		Image resized = img.getScaledInstance(185, 148, Image.SCALE_SMOOTH);
		logo.setIcon(new ImageIcon(resized));
		logo.setBounds(73, 11, 198, 181);
		contentPane.add(logo);		
        

        // added action listener
        btnLogIn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleLogin();
            }
        });
        
        rbtnShow.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        	
        	if(rbtnShow.isSelected()) {
        		txtPassword.setEchoChar((char)0);
        	}else {
        		txtPassword.setEchoChar('*');
        	}	
        		
        	}
        });
        
    }

    private void handleLogin() {
        String userName = txtUserName.getText().trim().replaceAll("\\s+", " "); //  \\s Regex palitan lahat ng multiple space ng one spcae lng
        String password = new String(txtPassword.getPassword()).replaceAll("\\s+", " ");

        // Check if empty
        if (userName.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Please fill in all fields.",
                "Warning Po!",
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Check login via controller
        boolean success = loginController.login(userName, password);

        if (success) {
            JOptionPane.showMessageDialog(this,
                "Login successful!",
                "Success Po!",
                JOptionPane.INFORMATION_MESSAGE);
            this.dispose(); // close login form
            AdminDash aDash = new AdminDash();
            aDash.setVisible(true);
            
        } else {
            JOptionPane.showMessageDialog(this,
                "Invalid username or password.",
                "Login Failed po",
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
}