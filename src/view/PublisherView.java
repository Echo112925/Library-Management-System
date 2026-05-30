package view;

import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import controller.PublisherController;
import model.Publisher;
import utility.AppContext;
import utility.TableRefresherHelper;

import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.awt.Component;
import javax.swing.JSeparator;
import javax.swing.JTable;


public class PublisherView extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
 
	private JTextField txtPublisher;
	private JButton btnAdd;
	private JButton btnUpdate;
	private JButton btnDelete;
	private JLabel lblSearch;
	private JTextField txtSearch;
	private JScrollPane scrollPane;
	private JSeparator separator;
	private JTable tblPublisher;
	private DefaultTableModel tblModel; 
	private int selectedPublisherId = -1 ;
	
	private PublisherController publisherController = AppContext.getInstance().getPublisherController();



	
	public PublisherView() {
		execute();
	}
	 
	
	 public void execute(){
		 setPanel();
		 initComponent();
		 initAction();
	   }
	 
	 public void setPanel(){
			this.setBounds(100, 100, 900, 720);
			contentPanel.setBackground(Color.LIGHT_GRAY);
			contentPanel.setBorder(null);
			this.contentPanel.setLayout(null);
			this.setContentPane(contentPanel);
			this.setLocationRelativeTo(null);
			this.setResizable(false);
			this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

	   }
	 
	 public void initComponent() {
		  
		 
			JPanel componentsBorder = new JPanel();
			componentsBorder.setLayout(null);
			componentsBorder.setBorder(new LineBorder(new Color(51, 204, 51), 3, true));
			componentsBorder.setBounds(38, 76, 803, 120);
			contentPanel.add(componentsBorder);
			
			JLabel lblPublisher = new JLabel("Publisher :");
			lblPublisher.setForeground(new Color(51, 102, 51));
			lblPublisher.setFont(new Font("Tahoma", Font.BOLD, 26));
			lblPublisher.setBounds(25, 21, 139, 20);
			componentsBorder.add(lblPublisher);
			
			txtPublisher = new JTextField();
			txtPublisher.setColumns(10);
			txtPublisher.setBounds(174, 23, 209, 23);
			componentsBorder.add(txtPublisher);
			
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
			
			lblSearch = new JLabel("Search");
			lblSearch.setForeground(new Color(51, 102, 51));
			lblSearch.setFont(new Font("Tahoma", Font.BOLD, 17));
			lblSearch.setBounds(573, 211, 64, 14);
			contentPanel.add(lblSearch);
			
			txtSearch = new JTextField();
			txtSearch.setColumns(10);
			txtSearch.setBounds(634, 207, 206, 23);
			contentPanel.add(txtSearch);
			
			scrollPane = new JScrollPane((Component) null);
			scrollPane.setBounds(38, 241, 803, 412);
			contentPanel.add(scrollPane);
			
		    String[] column = { "id", "Publisher" };
		    tblModel = new DefaultTableModel(column, 0){
		        public boolean isCellEditable(int row, int column) {
		            return false; // hindi na maaari mag-edit ng kahit anong cell
		        }	};
			tblPublisher = new JTable(tblModel);
			scrollPane.setViewportView(tblPublisher);
			
		
			// itago ang ID column para hindi makita ng user
			tblPublisher.getColumnModel().getColumn(0).setMinWidth(0);
			tblPublisher.getColumnModel().getColumn(0).setMaxWidth(0);
			tblPublisher.getColumnModel().getColumn(0).setWidth(0);
			
			separator = new JSeparator();
			separator.setBackground(new Color(51, 204, 51));
			separator.setBounds(38, 664, 803, 10);
			contentPanel.add(separator);
			
	 }
		
	 
	public void initAction() {
		btnAdd.addActionListener( e ->{
			addPublisher();
		});	
		
		loadPublisher();	
		TableRefresherHelper.tblRefresher(3000, () ->{ loadPublisher(); });

		btnUpdate.addActionListener(e -> {
			updatePublisher();
		});
		
		btnDelete.addActionListener(e ->{
			deletePublisher();
		});
		
		 searchPublisher();
		
		tblPublisher.getSelectionModel().addListSelectionListener(e -> {
			if (!e.getValueIsAdjusting()) { // para hindi mag-fire ng dalawang beses mouse, click down return true, release return false , gumamit ng ng ! para , to reverse the return, for cleaner industry standard not false == false
		        int selectedRow = tblPublisher.getSelectedRow(); // ✅ local variable lang — hindi kailangan sa ibang methods
		        
		        if (selectedRow != -1) { // may selected na row
		            // kumuha ng data sa selected row
		        	Object id =  tblModel.getValueAt(selectedRow, 0);
		        	Object name = tblModel.getValueAt(selectedRow, 1);  // column 1 = name
		            
		            // ilagay sa text field
		            txtPublisher.setText((String) name);
		            
		            // i-store yung id (para magamit sa edit/delete)
		            selectedPublisherId = (int) id;
		        }
		    }
				
		});
				txtSearch.getDocument().addDocumentListener(new DocumentListener() {
				    public void insertUpdate(DocumentEvent e) { searchPublisher(); }
				    public void removeUpdate(DocumentEvent e) { searchPublisher(); }
				    public void changedUpdate(DocumentEvent e) { searchPublisher(); }
				});
		
		
	} 
	 
	 public void addPublisher() {
		 try {
		String publisherName = txtPublisher.getText().trim().replaceAll("\\s+", " ");
		publisherController.addPublisher(publisherName);
		JOptionPane.showMessageDialog(this, "Successfully added!");
		txtPublisher.setText("");
		selectedPublisherId = -1;
		 }catch(Exception e) {	 
			 JOptionPane.showMessageDialog(this, e.getMessage(), "Warninggg" , JOptionPane.ERROR_MESSAGE);
		 }
	 }
	 
	 public void loadPublisher() {
		
		 tblModel.setRowCount(0); //prevent row stacking		 
		 try {
		 List<Publisher> publisherList = publisherController.loadPublisher();
		 for(Publisher publisher : publisherList) {
			 Object[] list = {publisher.getPublisherId(), publisher.getPublisherName() };
			 tblModel.addRow(list);
		 }
		 }catch(Exception e) {
			 JOptionPane.showMessageDialog(this, e.getMessage(), "Warning", JOptionPane.ERROR_MESSAGE);
		 }
		  
	 }
	 
	 public void updatePublisher() {
		 try {
		 String editedPublisher = txtPublisher.getText().trim().replaceAll("\\s+", " ");
		 try {
		 publisherController.updatePublisher(selectedPublisherId, editedPublisher);
		 JOptionPane.showMessageDialog(this, "Successfully updated!");
		 selectedPublisherId = -1;
		 }catch(Exception e) {
		     JOptionPane.showMessageDialog(this, e.getMessage(), "Warning", JOptionPane.ERROR_MESSAGE);
		 }
		 }catch(Exception e) {
			 JOptionPane.showMessageDialog(this, e.getMessage(), "Warning", JOptionPane.ERROR_MESSAGE);
		 }
	 }
	 
	 
	 public void deletePublisher() {
		 try {
		 int publisherid = selectedPublisherId;
		 publisherController.deletePublisher(publisherid);
		 JOptionPane.showMessageDialog(this, "Successfully deleted!");
		 txtPublisher.setText("");
		 selectedPublisherId = -1;
		 }catch(Exception e) {
			 JOptionPane.showMessageDialog(this, e.getMessage(), "Warning", JOptionPane.ERROR_MESSAGE);
		 }
	 }
	 
	 public void searchPublisher(){
		   
		    // kunin ang text sa search box, alisin ang extra spaces
		    String keyword = txtSearch.getText().trim();
		    if(!keyword.isEmpty()) TableRefresherHelper.stopRefresher();
		    else TableRefresherHelper.startRefresher();
		    
		    // i-clear muna ang table bago mag-load ng bagong results
		    tblModel.setRowCount(0);
      try {
		    // kung walang keyword → load lahat, may keyword → i-filter
		    ArrayList<Publisher> list = keyword.isEmpty()
		        ? publisherController.loadPublisher()       // walang keyword, load all
		        : publisherController.searchPublisher(keyword); // may keyword, i-filter

		    // i-loop ang results at idagdag sa table row by row
		    for (Publisher a : list) {
		        tblModel.addRow(new Object[]{a.getPublisherId(), a.getPublisherName()});
		    }
      }catch(Exception e) {
    	  JOptionPane.showMessageDialog(this, e.getMessage());
      } 
	 }
	
	 
	 
}
