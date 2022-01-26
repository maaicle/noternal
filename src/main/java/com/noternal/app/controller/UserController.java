package com.noternal.app.controller;

import com.noternal.app.entity.User;
import com.noternal.app.model.UserDto;
import com.noternal.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class UserController {

    @Autowired
    UserService userService;

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/users")
    public String addUser(@RequestBody UserDto userDto) {
        String passHash = passwordEncoder.encode(userDto.getPassHash());
        userService.addUser(userDto.getUserName(), passHash);
        return "User Added Successfully";
    }

}
