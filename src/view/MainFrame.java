package view;

import javax.swing.JFrame;
import javax.swing.JPanel;

// BINAGO: AdminDash → MainFrame (mas malinaw na pangalan)
// siya ang iisang JFrame ng buong app pagkatapos mag-login
public class MainFrame extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JPanel currentPanel;
    
    private Modules modules; // composition

    public MainFrame() {
        executeCode();
    }

    public void executeCode() {
        setupFrame();
        initComponents(); 
        showPanel(new DashboardPanel()); // default panel kapag nag-open ang MainFrame
        // BINAGO: MainFrame na ang naipass, hindi na AdminDash
        modules = new Modules(this);
        modules.setBounds(0, 0, 240, 768);
        contentPane.add(modules);
    }

    public void setupFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1366, 768);
        setLocationRelativeTo(null);
        setResizable(false);
        contentPane = new JPanel();
        contentPane.setLayout(null);
        setContentPane(contentPane);
    }

    public void initComponents() {
   
    }

    public void showPanel(JPanel panel) {
        if (currentPanel != null) {
            contentPane.remove(currentPanel);
        }
        currentPanel = panel;
        currentPanel.setBounds(234, 0, getWidth() - 240, getHeight());
        contentPane.add(currentPanel);
        contentPane.revalidate();
        contentPane.repaint();
    }
}