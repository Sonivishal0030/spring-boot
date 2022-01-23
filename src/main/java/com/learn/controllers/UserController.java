package com.learn.controllers;

import com.learn.domain.User;
import com.learn.requests.UserRequest;
import com.learn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/all-users")
    public ResponseEntity getAllUsers(Pageable pageable){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PostMapping("/persist-user")
    public ResponseEntity persistData(@RequestBody UserRequest userRequest){
        return ResponseEntity.ok(userService.persistUser(userRequest));
    }
}
