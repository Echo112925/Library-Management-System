package view;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Image;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;

public class Modules extends JPanel {
    private static final long serialVersionUID = 1L;
    private JButton btnDashboard;
    private JButton btnFileMaintenance;
    private JButton btnInventory;

    // BINAGO: AdminDash → MainFrame na ang reference
    private MainFrame mainFrame;

    public Modules() {
        setPanel();
    }
    
    // BINAGO: AdminDash → MainFrame na ang parameter
    public Modules(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setPanel();
        initAction();
    }

    public void setPanel() {
        setBackground(Color.GRAY);
        setLayout(null);

        JLabel logo = new JLabel();
        ImageIcon originalIcon = new ImageIcon(
        Modules.class.getResource("/img/images__1_-removebg-preview.png"));
        Image scaledImage = originalIcon.getImage()
            .getScaledInstance(200, 162, Image.SCALE_SMOOTH);
        logo.setIcon(new ImageIcon(scaledImage));
        logo.setBounds(20, 32, 230, 159);
        add(logo);

        btnDashboard = new JButton("DASHBOARD");
        btnDashboard.setFont(new Font("Tahoma", Font.BOLD, 13));
        btnDashboard.setBounds(21, 232, 200, 30);
        add(btnDashboard);

        btnFileMaintenance = new JButton("FILE MAINTENANCE");
        btnFileMaintenance.setFont(new Font("Tahoma", Font.BOLD, 13));
        btnFileMaintenance.setBounds(20, 273, 200, 30);
        add(btnFileMaintenance);

        btnInventory = new JButton("CIRCULATION");
        btnInventory.setFont(new Font("Tahoma", Font.BOLD, 13));
        btnInventory.setBounds(20, 314, 200, 30);
        add(btnInventory);
    }

    public void initAction() {
        // BINAGO: mainFrame na ang ginagamit, hindi na adminDash
        btnDashboard.addActionListener(e ->
            mainFrame.showPanel(new DashboardPanel()));

        btnFileMaintenance.addActionListener(e ->
            mainFrame.showPanel(new FileMaintenancePanel()));

        
    }
}