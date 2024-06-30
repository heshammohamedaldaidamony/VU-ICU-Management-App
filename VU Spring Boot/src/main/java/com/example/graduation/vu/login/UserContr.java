package com.example.graduation.vu.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

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

    @PutMapping("/profile-picture")
    public ResponseEntity<?> setProfilePic(@RequestParam String id ,
                                           @RequestParam String role,
                                           @RequestParam("file") MultipartFile file) throws IOException {
        return userService.setProfilePic(id,role,file);
    }
}
