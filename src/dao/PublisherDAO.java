package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import model.Publisher;
import utility.DatabaseHelper;

public class PublisherDAO {

	   public void addPublisher(String publisher) throws SQLException {
		   String sql = "INSERT INTO tbl_publisher (publisherName) VALUES(?)";
		
	try(  Connection conn = DatabaseHelper.getConnection();
		  PreparedStatement stmt = conn.prepareStatement(sql);)
	     {
		  stmt.setString(1, publisher);
		  stmt.executeUpdate();
		 }		  
	   }   
		
		public boolean isExisting(String authorName) throws SQLException {
			String sql = "SELECT COUNT(*) FROM tbl_publisher WHERE LOWER(publisherName) = LOWER(?)";
			
		try(	Connection conn = DatabaseHelper.getConnection();
		    PreparedStatement stmt = conn.prepareStatement(sql);){
		    stmt.setString(1, authorName);
		    ResultSet rs = stmt.executeQuery();    
		    if(rs.next()) {
		    	return rs.getInt(1) > 0;
		    }
		}  
		    return false;
		}
		
	   	
	public ArrayList<Publisher> loadPublisher() throws SQLException{
		ArrayList<Publisher> loadPublisher = new ArrayList<>();
		String sql = "SELECT * FROM tbl_publisher";
		try(
		Connection conn = DatabaseHelper.getConnection();
		PreparedStatement stmt = conn.prepareStatement(sql);
				){		
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) {
			Publisher publisher = new Publisher();
			publisher.setPublisherId(rs.getInt("publisherId"));
			publisher.setPublisherName(rs.getString("publisherName"));
			loadPublisher.add(publisher);
		}
		return loadPublisher;
		}
	}
	
	public void updatePublisher(int publisherId, String publisherName) throws SQLException {		
	String sql = "UPDATE tbl_publisher SET publisherName = ? WHERE publisherId = ?";
	try(
    Connection conn = DatabaseHelper.getConnection();	
    PreparedStatement stmt = conn.prepareStatement(sql); )
	{
    stmt.setString(1, publisherName);  
    stmt.setInt(2, publisherId);
    stmt.executeUpdate();  }
	}
	
	public void deletePublisher(int publisherId) throws SQLException {
		String sql = "DELETE FROM tbl_publisher WHERE publisherId = ? ";
		try(
		Connection conn = DatabaseHelper.getConnection();
		PreparedStatement stmt = conn.prepareStatement(sql); )
		{
		stmt.setInt(1, publisherId);
		stmt.executeUpdate();   }
		}
	
    public ArrayList<Publisher> searchPublisher(String publisherName) throws SQLException{
    	ArrayList<Publisher> filteredPublisher = new ArrayList<>();
    	String sql = "SELECT * FROM tbl_publisher WHERE LOWER(publisherName) LIKE LOWER(?)";
    	
    try(	Connection conn = DatabaseHelper.getConnection();
    	PreparedStatement stmt = conn.prepareStatement(sql); ){
    	stmt.setString(1, "%"+ publisherName + "%");
 
    try( ResultSet rs = stmt.executeQuery(); )
      {
    	
    	while(rs.next()) {
    		filteredPublisher.add( new Publisher(rs.getInt("publisherId"), rs.getString("publisherName"))); //getInt,getString galing sa DB
    	}
      }
    }
    	return filteredPublisher; 	
    }
	
	
	
	
}
