package com.mfa.mims.controller;

import com.mfa.mims.entity.User;
import com.mfa.mims.exception.UserNotFoundException;
import com.mfa.mims.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user)
    {
        return ResponseEntity.ok(userService.creteUser(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id)
    {
        User user = userService.getUserById(id).orElseThrow(()-> new UserNotFoundException("User not found with the given id: "+id));
        return ResponseEntity.ok(user);
    }
}
