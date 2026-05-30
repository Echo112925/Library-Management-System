package controller;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.PublisherDAO;
import model.Publisher;
public class PublisherController {
    
	private PublisherDAO publisherDao ; //composition Dependency injection
	
	public PublisherController(PublisherDAO publisherDao) {
		this.publisherDao = publisherDao;
	}
	
	public void addPublisher(String publisherName) {
		
	    	publisherName = publisherName.trim().replaceAll("\\s+", " ");
		    if(publisherName.isEmpty()) throw new IllegalArgumentException("Input all fields!");
		    if(isExisting(publisherName)) throw new IllegalArgumentException("Publisher already Exist!");
		    
		
		try {
		publisherDao.addPublisher(publisherName);
		}catch(SQLException e) {
			throw new RuntimeException("Database Connection Error!");
		}
	}
	
	
	public boolean isExisting(String publisherName) {
		try {
		boolean isExisting = publisherDao.isExisting(publisherName);
		return isExisting;
		}catch(SQLException e) {
			throw new RuntimeException("Database Connection Error!");
		}
	}
	
	
	public ArrayList<Publisher> loadPublisher(){     
		try {
			ArrayList<Publisher> loadPublisher = publisherDao.loadPublisher();
			return loadPublisher;
		}catch(SQLException e) {
			throw new RuntimeException("Database Connection Error");
		}
	}
	
	
	public void updatePublisher(int publisherId, String publisherName) {
		
		publisherName = publisherName.trim().replaceAll("\\s+", " ");
		
		if(publisherId < 0)throw new IllegalArgumentException("Select publisher first!");
		if(publisherName.isEmpty()) throw new IllegalArgumentException("Input all fields!");
	    if(isExisting(publisherName)) throw new IllegalArgumentException("Publisher already Exist!");
	
	    try {
	       publisherDao.updatePublisher(publisherId, publisherName);
	    }catch(SQLException e) {
	    	throw new RuntimeException("Database Connection Error");
	    }
	    
	}
	
	public void deletePublisher(int publisherId) {
		if(publisherId < 0) {throw new IllegalArgumentException("Select Publisher first!");}
		try {
		publisherDao.deletePublisher(publisherId);
		}catch(SQLException e) {
			throw new RuntimeException("Database Connection Error");
		}
	}
	
    
	public ArrayList<Publisher> searchPublisher(String publisherName){	
		try {
		ArrayList<Publisher> filteredPublisher = publisherDao.searchPublisher(publisherName);
		return filteredPublisher;
		}catch(SQLException e) {
			throw new RuntimeException("Database Connection Error!");
		}
	}
	
	
     
	
	
}
