package view;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import java.awt.Component;
import javax.swing.JSeparator;

public class SupplierView extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel contentPanel;
	private JTextField textField;
	private JTextField textField_1;
	

	public SupplierView() {
		execute();
	}
	
	public void execute() {
		 setPanel();
	}
	
	public void setPanel() {
		setBounds(100, 100, 900, 720);
		setLocationRelativeTo(null);
		setResizable(false);
		getContentPane().setLayout(null);
		
		contentPanel = new JPanel();
		contentPanel.setLayout(null);
		contentPanel.setBackground(Color.LIGHT_GRAY);
		contentPanel.setBounds(0, 0, 884, 681);
		getContentPane().add(contentPanel);	
		
		JPanel componentsBorder = new JPanel();
		componentsBorder.setLayout(null);
		componentsBorder.setBorder(new LineBorder(new Color(51, 204, 51), 3, true));
		componentsBorder.setBounds(46, 54, 803, 120);
		contentPanel.add(componentsBorder);
		
		JLabel lblCategory = new JLabel("Category:");
		lblCategory.setForeground(new Color(51, 102, 51));
		lblCategory.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblCategory.setBounds(25, 10, 139, 36);
		componentsBorder.add(lblCategory);
		
		textField = new JTextField();
		textField.setText("");
		textField.setColumns(10);
		textField.setBounds(163, 20, 209, 23);
		componentsBorder.add(textField);
		
		JButton btnAdd = new JButton("ADD");
		btnAdd.setForeground(new Color(51, 102, 51));
		btnAdd.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnAdd.setEnabled(false);
		btnAdd.setBounds(25, 68, 80, 20);
		componentsBorder.add(btnAdd);
		
		JButton btnUpdate = new JButton("UPDATE");
		btnUpdate.setForeground(new Color(51, 102, 51));
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnUpdate.setEnabled(false);
		btnUpdate.setBounds(133, 68, 95, 20);
		componentsBorder.add(btnUpdate);
		
		JButton btnDelete = new JButton("DELETE");
		btnDelete.setForeground(new Color(51, 102, 51));
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnDelete.setEnabled(false);
		btnDelete.setBounds(256, 68, 95, 20);
		componentsBorder.add(btnDelete);
		
		JScrollPane scrollPane = new JScrollPane((Component) null);
		scrollPane.setBounds(46, 237, 803, 412);
		contentPanel.add(scrollPane);
		
		JLabel lblSearch = new JLabel("Search");
		lblSearch.setForeground(new Color(51, 102, 51));
		lblSearch.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblSearch.setBounds(582, 206, 64, 14);
		contentPanel.add(lblSearch);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(643, 202, 206, 23);
		contentPanel.add(textField_1);
		
		JSeparator separator = new JSeparator();
		separator.setBackground(new Color(51, 204, 51));
		separator.setBounds(46, 664, 803, 10);
		contentPanel.add(separator);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

	}
	
	
	
	public static void main(String[] args) {
		try {
			SupplierView dialog = new SupplierView();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
