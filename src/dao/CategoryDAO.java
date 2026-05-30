package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import utility.DatabaseHelper;
import model.Category;

public class CategoryDAO {
	
	public void addCategory(String categoryName)throws SQLException {
		String sql = "INSERT INTO tbl_category (categoryName) VALUES(?)";
		try(
		Connection conn = DatabaseHelper.getConnection();
		PreparedStatement stmt = conn.prepareStatement(sql);)
		{
		stmt.setString(1, categoryName);
		stmt.executeUpdate();
		}
	}
	
	public boolean isExisting(String categoryName) throws SQLException {
		String sql = "SELECT COUNT(*) FROM tbl_category WHERE LOWER(categoryName) = LOWER(?)";
	try(	
		Connection conn = DatabaseHelper.getConnection();
		PreparedStatement stmt = conn.prepareStatement(sql))
	{
	    stmt.setString(1, categoryName);	    
       try( ResultSet rs = stmt.executeQuery())
       {
		
		if(rs.next()) {
			return rs.getInt(1) > 0;
		}
       }
	 }	
	return false;						
	}

	public ArrayList<Category> loadCategory() throws SQLException{
		ArrayList<Category> loadCategory = new ArrayList<>();
		String sql = "SELECT * FROM tbl_category";
		
	try(	
		Connection conn = DatabaseHelper.getConnection();
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();)
	{
		
		while(rs.next()) {
			Category categoryList = new Category();
			categoryList.setCategoryId(rs.getInt("categoryId"));
			categoryList.setCategoryName(rs.getString("categoryName"));
			loadCategory.add(categoryList);
		}
		return loadCategory;
	}
		
	}
	
	public void updateCategory(int categoryId, String categoryName) throws SQLException {
		String sql = "UPDATE tbl_category SET categoryName = ? WHERE categoryId = ?";
	try(	
		Connection conn = DatabaseHelper.getConnection();
		PreparedStatement stmt = conn.prepareStatement(sql);)
	{
		stmt.setString(1, categoryName);
		stmt.setInt(2, categoryId);
		stmt.executeUpdate();
	}	
   }
	
	public void deleteCategory(int categoryId) throws SQLException {
		String sql = "DELETE FROM tbl_category WHERE categoryId = ?";
		try(	
				Connection conn = DatabaseHelper.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);)
			{
				stmt.setInt(1, categoryId);
				stmt.executeUpdate();
			}	
	}
	
	public ArrayList<Category> searchCategory(String keyword) throws SQLException{
		ArrayList<Category> filteredCategory = new ArrayList<>();
		
		String sql = "SELECT * FROM tbl_category WHERE LOWER(categoryName)LIKE LOWER(?)";
		
		try(Connection conn = DatabaseHelper.getConnection();
		PreparedStatement stmt = conn.prepareStatement(sql);)
	{
		stmt.setString(1, "%" + keyword + "%");
		
		try(ResultSet rs = stmt.executeQuery())
		{
	    while(rs.next()) {
	    	filteredCategory.add(new Category(rs.getInt("categoryId"), rs.getString("categoryName")));
	      }
	   	}
	}
	    return filteredCategory;	
	}
	
	
	
	
	
	
}
