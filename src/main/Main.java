package main;

import java.awt.EventQueue;

import view.LogIn;
import dao.AuthorDAO;
import dao.CategoryDAO;
import dao.PublisherDAO;
import utility.AppContext;
import controller.AuthorController;
import controller.CategoryController;
import controller.PublisherController;

public class Main {

// learn App context, servie locator

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
//				AuthorDAO authorDao = new AuthorDAO();	
//				AuthorController authorController = new AuthorController(authorDao);	
//				
// 				PublisherDAO publisherDao = new PublisherDAO();
//				PublisherController publisherController = new PublisherController(publisherDao);
//				
//				CategoryDAO categoryDao = new CategoryDAO();
//				CategoryController categoryController = new CategoryController(categoryDao);
					 
			    AppContext ctx = AppContext.getInstance();
			    ctx.setAuthorController(new AuthorController(new AuthorDAO()));
			    ctx.setPublisherController(new PublisherController(new PublisherDAO())); 
			    ctx.setCategoryController(new CategoryController(new CategoryDAO()));				
				
				
				LogIn login = new LogIn();
				login.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
}

//no dependecy in AuthorDAO/Controller , for educational purpose only
