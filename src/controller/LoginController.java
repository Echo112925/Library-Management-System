package controller;

import java.sql.SQLException;

import dao.UserDAO;
import model.User;

public class LoginController {

    private UserDAO userDAO;

    public LoginController() {
       userDAO = new UserDAO();
    }
 
    public boolean login(String userName, String password) {
    	try {
        User user = userDAO.validateLogin(userName, password);
        return user != null; // true = login success
    	}catch(SQLException e) {
    		throw new RuntimeException("Database Connection Problem");
     	}
        
    }
}