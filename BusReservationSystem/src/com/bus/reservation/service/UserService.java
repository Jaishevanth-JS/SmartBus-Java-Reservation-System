package com.bus.reservation.service;

import com.bus.reservation.dao.UserDAO;
import com.bus.reservation.model.User;

public class UserService {
    private UserDAO userDAO = new UserDAO();

    public boolean register(String name, String email, String password) {
        User user = new User(0, name, email, password);
        return userDAO.register(user);
    }

    public User login(String email, String password) {
        return userDAO.login(email, password);
    }
}
