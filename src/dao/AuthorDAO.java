package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Author;

import utility.DatabaseHelper;

public class AuthorDAO {
     
	public void addAuthor(String authorName) throws SQLException {
        String sql = "INSERT INTO tbl_author (authorName) VALUES(?)";
       
        try(
        Connection conn = DatabaseHelper.getConnection();
	        PreparedStatement stmt =  conn.prepareStatement(sql); )
        {
	        stmt.setString(1, authorName);
	        stmt.executeUpdate();     
        }
	}
	
	
	public boolean isExisting(String authorName) throws SQLException {
	    String sql = "SELECT COUNT(*) FROM tbl_author WHERE LOWER(authorName) = LOWER(?)"; // COUNT = binibilang lahat ng magka same na name
	    try(
	    Connection conn = DatabaseHelper.getConnection();
	    PreparedStatement stmt = conn.prepareStatement(sql);)
	    {
	    stmt.setString(1, authorName);
	     
	   try ( ResultSet rs = stmt.executeQuery() )
	   {
	    
	    if(rs.next()) { //pupunta sa unang row 
	     return rs.getInt(1) > 0;  //getInt(1) means 1 counts lang mula kay column authorName field , kung may authorId pa getInt(2) na for column 2
	     }  
        }
	   }
	    return false;
	}
	 
	
	
	public ArrayList<Author> loadAuthor() throws SQLException {		
		ArrayList<Author> authorList = new ArrayList<>();
		String sql = "SELECT * FROM tbl_author";

		try(
		Connection conn = DatabaseHelper.getConnection();
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();)
		{
		while(rs.next()) {
		Author author = new Author();
		author.setAuthorId(rs.getInt("authorId"));
	    author.setAuthorName(rs.getString("authorName")); // BAGO - kunin ang value
        authorList.add(author);		
		}
		}
		return authorList;
	}
	
	public void editAuthor(int id, String editAuthor) throws SQLException {
		String sql = "UPDATE tbl_author SET authorName = ? WHERE authorId = ?";
	try(
		Connection conn = DatabaseHelper.getConnection();
		PreparedStatement stmt = conn.prepareStatement(sql); )
	{
		stmt.setString(1, editAuthor);
		stmt.setInt(2, id);
		stmt.executeUpdate();
		
//		int rowsAffected = stmt.executeUpdate();
//
//		if (rowsAffected == 0) {
//		    System.out.println("No record found with that ID");
//		}    Debugging tips, id tracking
	  }
	}
	
	public void deleteAuthor(int id) throws SQLException {
		String sql = "DELETE FROM tbl_author WHERE authorId = ?";
	try(
		Connection conn = DatabaseHelper.getConnection();
		PreparedStatement stmt = conn.prepareStatement(sql);
		)	{
		stmt.setInt(1, id);
		stmt.executeUpdate();	
	   }
	}
	
	
	public ArrayList<Author> searchAuthor(String keyword) throws SQLException {
	    ArrayList<Author> authors = new ArrayList<>();
	    
	    String sql = "SELECT * FROM tbl_author WHERE LOWER(authorName) LIKE LOWER(?)";
	    try ( Connection conn = DatabaseHelper.getConnection();
	        PreparedStatement ps = conn.prepareStatement(sql))
	    {
	        ps.setString(1, "%" + keyword + "%"); //keyword% first word ng keyword, %keyword last word ng keyword, % kyword %, any 
	      try(  ResultSet rs = ps.executeQuery();)
	      {
	        
	        while (rs.next()) {
	            authors.add(new Author(rs.getInt("authorId"), rs.getString("authorName")));
	        }
	      }
	    }
	    return authors;
	}
	
	
	
}	