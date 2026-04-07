package dao;

import model.User;
import utility.DatabaseHelper;
import java.sql.*;

public class UserDAO {

    public User validateLogin(String userName, String password) {
        String sql = "SELECT * FROM tbl_user WHERE userName = ? AND password = ?";
        try (Connection conn = DatabaseHelper.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
 
            stmt.setString(1, userName);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new User(rs.getString("userName"), rs.getString("password"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // null means login failed
    }
}