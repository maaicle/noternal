package com.noternal.app.service;

import com.noternal.app.entity.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UserService {
    void addUser(String username, String password);
    List<User> getAllUsers();
}
