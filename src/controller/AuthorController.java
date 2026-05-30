package controller;

import java.sql.SQLException;
import java.util.ArrayList;

import dao.AuthorDAO;
import model.Author;
 
public class AuthorController {
      private AuthorDAO authorDAO; 
      
        public AuthorController(AuthorDAO authorDAO) {
        	this.authorDAO = authorDAO;
        }
       
        public void addAuthor(String authorName) {
    		if(authorName.isEmpty()) {
    	        throw new IllegalArgumentException("Please input all fields");  }// ← throw, hindi JOptionPane		
    		if(isExisting(authorName)){
    			throw new IllegalArgumentException("Author Already Exist");  }  		
    try {		
        	    authorDAO.addAuthor(authorName);
    }catch(SQLException e) {
    	throw new RuntimeException("Database Connection Error");
    }
        	
        }
        
        public boolean isExisting(String authorName) {
        try {	
        	boolean existing = authorDAO.isExisting(authorName); 
            return existing;
        }catch(SQLException e) {
        	throw new RuntimeException("Database Connection Error");
           }
        }
        
         
        
        public ArrayList<Author> loadAuthor() {
        try {	
        	return authorDAO.loadAuthor();
        }catch(SQLException e) {
        	throw new RuntimeException("Failed to load author");
           }
        }
                
        
        public void editAuthor(int id, String editAuthor) {
       	
        	if(editAuthor.isEmpty()) {
    	        throw new IllegalArgumentException("Please input all fieldssssssss"); // ← throw, hindi JOptionPane
    		} 	
        	if(isExisting(editAuthor)){
    			throw new IllegalArgumentException("Author Already Exist"); 			
    		}
        	
         try {	
        	authorDAO.editAuthor(id, editAuthor);
       }catch(SQLException e) {
    	    throw new RuntimeException("Database Error");
       }
       
   }
        
        
        public void deleteAuthor(int id) {
        if(id == -1) {
        throw new IllegalArgumentException("Select Author First"); }
      try {
        	authorDAO.deleteAuthor(id);
      }catch(SQLException e) {
  	      throw new RuntimeException("Database Error");
         }      
       }
        
        
        public ArrayList<Author> searchAuthor(String keyword) {
            try {  
                return authorDAO.searchAuthor(keyword); // DAO na ang mag-filter
            } catch (SQLException e) {
                throw new RuntimeException("Database Error");
            }
        }
        
        
        
}
