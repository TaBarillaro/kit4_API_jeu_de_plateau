package com.example.kit4_api.controller;


import com.example.kit4_api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/validate")
    public boolean validateUser(@RequestHeader("X-UserId") String userId) {
        return userService.isValidUser(userId);
    }
}