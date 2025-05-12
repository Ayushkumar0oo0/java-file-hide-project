package main.java.service;

import java.sql.SQLException;
import main.java.model.User;
import main.java.dao.userDAO;

public class UserService {
    public static Integer saveUser(User user) {
        try {
            if (userDAO.isExist(user.getEmail())) {
                return 0;
            } else {
                return userDAO.saveUser(user);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
