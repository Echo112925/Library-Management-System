package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import dao.CategoryDAO;
import model.Category;

public class CategoryController {
	
	private CategoryDAO categoryDao;
	
	public CategoryController(CategoryDAO categoryDao) {
		this.categoryDao = categoryDao;
	}
	
	public void addCategory(String categoryName) {
		categoryName = categoryName.trim().replaceAll("\\s+", " ");
	    categoryName = capitalizeWords(categoryName);

		
		if(categoryName.isEmpty()) throw new IllegalArgumentException("Category Cannot be empty!");
		if(isExisting(categoryName)) throw new IllegalArgumentException("Category already exist!");
		
		try {
		categoryDao.addCategory(categoryName);
		}catch(SQLException e) {
			throw new RuntimeException("Database connection error!");
		}
	}
	
	   private boolean isExisting(String categoryName) {
		   categoryName = categoryName.trim().replaceAll("\\s+", " ");
		   try {
		    boolean isExisting = categoryDao.isExisting(categoryName);
		    return isExisting;
		   }catch(SQLException e) {
			   e.printStackTrace();
			   throw new RuntimeException("Database Connection error!");
		   }
	   }
	   
	   
	   public ArrayList<Category> loadCategory(){
		   try {
		   ArrayList<Category> categoryList = categoryDao.loadCategory();
		   return categoryList;
		   }catch(SQLException e) {
			   throw new RuntimeException("Database connection error!");
		   }
	   }
	   
	   
	   public void updateCategory(int categoryId, String categoryName) {
		   categoryName = categoryName.trim().replaceAll("\\s+", " ");
		    categoryName = capitalizeWords(categoryName);

		   
		   if(categoryId < 0) throw new IllegalArgumentException("Select category first!"); 			   
		   if(categoryName.isEmpty()) throw new IllegalArgumentException("Category cannot be empty!"); 		
		   if(isExisting(categoryName)) throw new IllegalArgumentException("Category already exist");
		  
		   try {
		   categoryDao.updateCategory(categoryId, categoryName);
		   }catch(SQLException e) {
			   e.printStackTrace();
			   throw new RuntimeException("Database Connection error!");	 
		   }		  
	   }
	   
	   
	   public void deleteCategory(int categoryId) {
		   if(categoryId < 0) throw new IllegalArgumentException("Select category first!");
		   try {
		   categoryDao.deleteCategory(categoryId);
		   }catch(SQLException e) {
			   throw new RuntimeException("Database connection error!");
		   }   
	   }
	   
	   public ArrayList<Category> searchCategory(String keyword){
		   keyword = keyword.trim();
		   try {
		  return  categoryDao.searchCategory(keyword); 
		   }catch(SQLException e) {
			   throw new RuntimeException("Database connection error!");
		   }
	   }
	   
	   
	// BAGONG HELPER METHOD
	   private String capitalizeWords(String input) {
		    String[] words = input.split(" ");
		    StringBuilder result = new StringBuilder();
		    for (String word : words) {
		        if (!word.isEmpty()) {
		            result.append(Character.toUpperCase(word.charAt(0))) // capitalize first letter
		                  .append(word.substring(1)) // ✅ preserve na lang yung remaining letters
		                  .append(" ");
		        }
		    }
		    return result.toString().trim();
		}
   
	   
	   
	   

}
