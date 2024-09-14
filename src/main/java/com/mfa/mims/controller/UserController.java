package com.mfa.mims.controller;

import com.mfa.mims.entity.User;
import com.mfa.mims.repository.UserRepository;
import com.mfa.mims.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public CompletableFuture<ResponseEntity<User>> createUser(@RequestBody User user)
    {
        return userService.creteUser(user).thenApply(ResponseEntity::ok);
    }

    @GetMapping("/{id}")
    public CompletableFuture<ResponseEntity<User>> getUserById(@PathVariable  Long id)
    {
        CompletableFuture<Optional<User>> optionalCompletableFuture = userService.getUserById(id);
        return optionalCompletableFuture.thenApply(optionalUser ->
                optionalUser.map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build()));
    }

    @GetMapping("/username/{username}")
    public CompletableFuture<ResponseEntity<User>> getUserByUsername(@PathVariable String username)
    {
        return userService.getUserByUsername(username).thenApply(userOptional->
                userOptional.map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build()));
    }

    @GetMapping("/email/{email}")
    public CompletableFuture<ResponseEntity<User>> getUserByEmail(@PathVariable String email)
    {
        return userService.getUserByEmail(email).thenApply(userOptional->
                userOptional.map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build()));
    }

    @GetMapping
    public CompletableFuture<ResponseEntity<List<User>>> getAllUsers()
    {
        return userService.getAllUsers().thenApply(ResponseEntity::ok);
    }

    @PutMapping("/{id}")
    public CompletableFuture<ResponseEntity<User>> updateUser(@PathVariable Long id, @RequestBody User userDetails)
    {
        return userService.updateUser(id, userDetails).thenApply(ResponseEntity::ok);
    }

    @DeleteMapping("/{id}")
    public CompletableFuture<ResponseEntity<Void>> deleteUserById(@PathVariable Long id)
    {
        return userService.deleteUser(id).thenApply(aVoid->ResponseEntity.notFound().build());
    }
}
