package view;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JSeparator;

import controller.AuthorController;
import model.Author;
import utility.AppContext;
import utility.TableRefresherHelper;

import java.lang.IllegalArgumentException;



public class AuthorView extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtAuthor;
	private JTextField txtSearch;
	private JTable tblAuthor;
	private DefaultTableModel tblModel; 
	private JButton btnDelete;
	private JButton btnUpdate;
	private JButton btnAdd;
	
	
    
	private AuthorController authorController = AppContext.getInstance().getAuthorController();
	private int selectedAuthorId = -1 ;
	
	public AuthorView() {		    
		execute();
	}
	 
	public void execute() {
		setPanel();
		initComponents();
		initActions();
		loadAuthor();
		}
	
	public void setPanel() {
		this.setBounds(100, 100, 900, 720);
		contentPanel.setBackground(Color.LIGHT_GRAY);
		contentPanel.setBorder(null);
		this.contentPanel.setLayout(null);
		this.setContentPane(contentPanel);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

	}
	
	public void initComponents() {
		
		   String[] columns = {"ID", "Author Name"}; // BAGO
			// ✅ PALITAN NG:
			   tblModel = new DefaultTableModel(columns, 0) {
			       @Override
			       public Class<?> getColumnClass(int columnIndex) {
			           if (columnIndex == 0) return Integer.class; // ✅ ID column = Integer lagi
			           return String.class;
			       }
			       
			       @Override
			       public boolean isCellEditable(int row, int column) {
			           return false; // ✅ bonus — hindi na maa-edit ang cells ng user directly
			       }
			   };
		
		JPanel componentsBorder = new JPanel();
		componentsBorder.setLayout(null);
		componentsBorder.setBorder(new LineBorder(new Color(51, 204, 51), 3, true));
		componentsBorder.setBounds(38, 76, 803, 120);
		contentPanel.add(componentsBorder);
		
		JLabel lblAuthor = new JLabel("Author :");
		lblAuthor.setForeground(new Color(51, 102, 51));
		lblAuthor.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblAuthor.setBounds(25, 21, 107, 20);
		componentsBorder.add(lblAuthor);
		
		txtAuthor = new JTextField();
		txtAuthor.setColumns(10);
		txtAuthor.setBounds(142, 21, 209, 23);
		componentsBorder.add(txtAuthor);
		
		btnAdd = new JButton("ADD");
		btnAdd.setForeground(new Color(51, 102, 51));
		btnAdd.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnAdd.setBounds(25, 68, 80, 20);
		componentsBorder.add(btnAdd);
		
		btnUpdate = new JButton("UPDATE");
		btnUpdate.setForeground(new Color(51, 102, 51));
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnUpdate.setBounds(133, 68, 95, 20);
		componentsBorder.add(btnUpdate);
		
		btnDelete = new JButton("DELETE");
		btnDelete.setForeground(new Color(51, 102, 51));
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnDelete.setBounds(256, 68, 95, 20);
		componentsBorder.add(btnDelete);
		
		JSeparator separator = new JSeparator();
		separator.setBackground(new Color(51, 204, 51));
		separator.setBounds(38, 661, 803, 10);
		contentPanel.add(separator);
		
		JLabel lblSearch = new JLabel("Search");
		lblSearch.setBounds(573, 208, 64, 14);
		contentPanel.add(lblSearch);
		lblSearch.setForeground(new Color(51, 102, 51));
		lblSearch.setFont(new Font("Tahoma", Font.BOLD, 17));
		
		txtSearch = new JTextField();
		txtSearch.setBounds(634, 204, 206, 23);
		contentPanel.add(txtSearch);
		txtSearch.setColumns(10);
		
		tblAuthor = new JTable();
		tblAuthor.setBounds(38, 238, 803, 412);
		tblAuthor.setModel(tblModel);                        // BAGO - i-connect ang model
		JScrollPane scrollPane = new JScrollPane(tblAuthor);
		scrollPane.setBounds(38, 238, 803, 412);             // BAGO - bounds sa scrollPane na
		contentPanel.add(scrollPane);

		// itago ang ID column para hindi makita ng user
		tblAuthor.getColumnModel().getColumn(0).setMinWidth(0);
		tblAuthor.getColumnModel().getColumn(0).setMaxWidth(0);
		tblAuthor.getColumnModel().getColumn(0).setWidth(0);
	}
	
	public void initActions() {
		btnAdd.addActionListener(e->{
			addAuthor();
			}); 
		
		btnUpdate.addActionListener(e->{
			updateAuthor();
		});
		
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteAuthor();
			}
		});
		
		this.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent e) {
		        TableRefresherHelper.stopRefresher();
		    }
		});
		
		tblAuthor.getSelectionModel().addListSelectionListener(e -> {
			if (!e.getValueIsAdjusting()) { // para hindi mag-fire ng dalawang beses
		        int selectedRow = tblAuthor.getSelectedRow(); // ✅ local variable lang — hindi kailangan sa ibang methods
		        
		        if (selectedRow != -1) { // may selected na row
		            // kumuha ng data sa selected row
		        	int id = (int) tblModel.getValueAt(selectedRow, 0);
		        	String name = (String) tblModel.getValueAt(selectedRow, 1);  // column 1 = name
		            
		            // ilagay sa text field
		            txtAuthor.setText(name);
		            
		            // i-store yung id (para magamit sa edit/delete)
		            selectedAuthorId = id;
		        }
		    }
			
		});
		
		// getDocument() — kinukuha ang "document" ng txtSearch (yung laman/content ng text field)
		// addDocumentListener() — naglalagay ng listener na mag-mo-monitor sa bawat pagbabago ng document
		txtSearch.getDocument().addDocumentListener(new DocumentListener() {
		    
		    // insertUpdate — fires pag NAGDAGDAG ng character ang user (nag-type)
		    // example: "" → "J" → "Jo" → "Joh" → "John"
		    public void insertUpdate(DocumentEvent e) { searchAuthor(); }

		    // removeUpdate — fires pag NAG-BURA ng character ang user (nag-backspace/delete)
		    // example: "John" → "Joh" → "Jo" → "J" → ""
		    public void removeUpdate(DocumentEvent e) { searchAuthor(); }

		    // changedUpdate — fires pag nagbago ang STYLE ng text (bold, italic, font size)
		    // rarely fires sa JTextField — mostly sa JTextPane/JEditorPane na lang
		    // nilalagay pa rin dahil REQUIRED (interface)
		    public void changedUpdate(DocumentEvent e) { searchAuthor(); }
		});
		
		TableRefresherHelper.tblRefresher(3000, () ->{ loadAuthor(); });

		
		
		
	}
	 
	public void addAuthor() {
		String authorName = txtAuthor.getText().trim().replaceAll("\\s+", " ");
		
	    try {		
			 authorController.addAuthor(authorName);
			 JOptionPane.showMessageDialog(this, "Successfully Added");
			 txtAuthor.setText("");
			 selectedAuthorId = -1;
		}catch(IllegalArgumentException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "WARNING", JOptionPane.WARNING_MESSAGE); return;
		}catch(RuntimeException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "WARNING", JOptionPane.WARNING_MESSAGE);  return;
		}
	}
	
	
	public void loadAuthor() {
	 try {
	   tblAuthor.clearSelection(); // ✅ DAGDAG ITO — prevents stale selection event sa setRowCount(0)
	   tblModel.setRowCount(0);
	    ArrayList<Author> authorList = authorController.loadAuthor();

	    for(Author a : authorList) {
	        Object[] row = {a.getAuthorId(), a.getAuthorName()}; // ✅ ID + Name
	        tblModel.addRow(row);
	    }
	    
	    
	 }catch(RuntimeException e) {
		JOptionPane.showMessageDialog(this, e.getMessage(), "Database problem", JOptionPane.WARNING_MESSAGE);
		 return;
	 }
	}
	
	public void updateAuthor() {
	    
	    String editedName = txtAuthor.getText().trim().replaceAll("\\s+", " ");
	  // maganda pang debug  System.out.println("name " + editedName + " id " + selectedAuthorId );
	    
	    if(selectedAuthorId < 0) {
	    	JOptionPane.showMessageDialog(this, "Select Author first!", "WARNING", JOptionPane.ERROR_MESSAGE);
	    	return;
	    }
	    
	    try { 
	        authorController.editAuthor(selectedAuthorId, editedName);
	        JOptionPane.showMessageDialog(this, "Successfully Updated");
	        txtAuthor.setText("");
	        selectedAuthorId = -1; // i-reset after edit
	        
	    } catch (IllegalArgumentException e) {
	        JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
	        return;
	    }
	}
	
	
	public void deleteAuthor() {	
	    if(selectedAuthorId < 0) {
	    	JOptionPane.showMessageDialog(this, "Select Author first!", "WARNING", JOptionPane.ERROR_MESSAGE);
	    	return;
	    }
		try {
		int authorId = selectedAuthorId;
		authorController.deleteAuthor(authorId);	
		JOptionPane.showMessageDialog(this, "Successfully Deleted");
		txtAuthor.setText("");
		selectedAuthorId = -1;
		}catch(IllegalArgumentException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Error yah", JOptionPane.WARNING_MESSAGE);
			return;
		}
	}
	
	
	 

	
	
	public void searchAuthor() {
	    // kunin ang text sa search box, alisin ang extra spaces
	    String keyword = txtSearch.getText().trim();
	    if(!keyword.isEmpty()) {
	    	TableRefresherHelper.stopRefresher();
	    }else {
	    	TableRefresherHelper.startRefresher();
	    }
	    
	    // i-clear muna ang table bago mag-load ng bagong results
	    tblModel.setRowCount(0);
     try {
	    // kung walang keyword → load lahat, may key word → i-filter
	    ArrayList<Author> list = keyword.isEmpty()
	        ? authorController.loadAuthor()       // walang keyword, load all
	        : authorController.searchAuthor(keyword); // may keyword, i-filter

	    // i-loop ang results at idagdag sa table row by row
	    for (Author a : list) {
	        tblModel.addRow(new Object[]{a.getAuthorId(), a.getAuthorName()});
	    }
     }catch(Exception e) {
    	 JOptionPane.showMessageDialog(this, e.getMessage());
     } 
	}
	
	

}