package controller;

import dao.UserDAO;
import model.User;

public class LoginController {

    private UserDAO userDAO;

    public LoginController() {
       userDAO = new UserDAO();
    }
 
    public boolean login(String userName, String password) {
        User user = userDAO.validateLogin(userName, password);
        return user != null; // true = login success
    }
}