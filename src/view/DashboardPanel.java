package view;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Font;

// BAGO: placeholder lang muna para sa Dashboard content
// pwede mong dagdagan ng charts, tables, etc. later
public class DashboardPanel extends JPanel {
    private static final long serialVersionUID = 1L;

    public DashboardPanel() {
        setLayout(null);

        JLabel label = new JLabel("DASHBOARD");
        label.setFont(new Font("Tahoma", Font.BOLD, 24));
        label.setBounds(34, 128, 300, 50);
        add(label);
    }
}