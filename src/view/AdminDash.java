package view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Image;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdminDash extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnFileMaintenance;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;

	
	public AdminDash() {
		
		executeCode();
	}
	
	public void executeCode() {
        setupFrame();
    }
	
	public void setupFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1366, 768);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setResizable(false);

		
		JPanel panel = new JPanel();
		panel.setBackground(Color.GRAY);
		panel.setBounds(0, 0, 230, 729);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel logo = new JLabel();
		ImageIcon icon = new ImageIcon(AdminDash.class.getResource("/img/images__1_-removebg-preview.png"));
		// resize image to fit label bounds
		Image img = icon.getImage();
		Image resized = img.getScaledInstance(185, 148, Image.SCALE_SMOOTH);
		logo.setIcon(new ImageIcon(resized));
		logo.setBounds(22, 29, 198, 181);
		panel.add(logo);
		
		btnFileMaintenance = new JButton("FILE MAINTENANCE");
		btnFileMaintenance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnFileMaintenance.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnFileMaintenance.setBounds(10, 255, 210, 28);
		panel.add(btnFileMaintenance);
		
		btnNewButton = new JButton("New button");
		btnNewButton.setBounds(10, 299, 210, 28);
		panel.add(btnNewButton);
		
		btnNewButton_1 = new JButton("New button");
		btnNewButton_1.setBounds(10, 338, 210, 28);
		panel.add(btnNewButton_1);
		
		btnNewButton_2 = new JButton("New button");
		btnNewButton_2.setBounds(10, 377, 210, 28);
		panel.add(btnNewButton_2);

    }
	
	
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminDash frame = new AdminDash();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
