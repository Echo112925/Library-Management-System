package view;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Font;

// BAGO: FileMaintenancePanel extends JPanel
// dati si FileMaintenance extends JFrame — nagbubukas ng bagong window
// ngayon panel lang ito na nilo-load sa loob ng AdminDash
public class FileMaintenancePanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private JButton btnAdd;
    private JTable table;

    public FileMaintenancePanel() {
        setLayout(null);
        initComponents();
    }

    public void initComponents() {
        btnAdd = new JButton("ADD");
        btnAdd.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnAdd.setBounds(10, 47, 144, 35);
        add(btnAdd);

        // BAGO: JScrollPane para may scrollbar ang JTable
        // dapat palaging naka-wrap ang JTable sa JScrollPane
        table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(23, 306, 1090, 411);
        add(scrollPane);
    }
}