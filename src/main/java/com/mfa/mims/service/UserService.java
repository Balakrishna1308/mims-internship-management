package com.mfa.mims.service;

import com.mfa.mims.entity.User;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public interface UserService {

    CompletableFuture<User> creteUser(User user);
    CompletableFuture<Optional<User>> getUserById(Long id);
    CompletableFuture<Optional<User>> getUserByUsername(String username);
    CompletableFuture<Optional<User>> getUserByEmail(String email);
    CompletableFuture<List<User>> getAllUsers();
    CompletableFuture<User> updateUser(Long id, User userDetails);
    CompletableFuture<Void> deleteUser(Long id);

}
