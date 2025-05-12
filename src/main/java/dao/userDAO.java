package main.java.dao;

import java.sql.*;
import main.java.db.MyConnection;
import main.java.model.User;

public class userDAO {
    public static boolean isExist(String email) throws SQLException {
        String query = "SELECT email FROM user WHERE email = ?";
        try (Connection connection = MyConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }

    public static int saveUser(User user) throws SQLException {
        String query = "INSERT INTO user(name, email) VALUES(?, ?)";
        try (Connection connection = MyConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            return ps.executeUpdate();
        }
    }
}
