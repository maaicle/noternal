package com.noternal.app.service;

import com.noternal.app.entity.User;

import java.util.List;

public interface UserService {
    void addUser(String userName, String passHash);
    List<User> getAllUsers();
}
