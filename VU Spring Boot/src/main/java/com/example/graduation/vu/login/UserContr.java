package com.example.graduation.vu.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserContr {
    @Autowired
    private UserService userService;

    //User authentication - if user exist return him else return status:not found
    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticateUser(@RequestBody User user){
        return userService.authenticateUser(user);
    }
}
