package com.mfa.mims.service.impl;

import com.mfa.mims.entity.User;
import com.mfa.mims.repository.UserRepository;
import com.mfa.mims.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Async("taskExecutor")
    @Override
    public CompletableFuture<User> creteUser(User user) {
        user.setPassword(user.getPassword());
        return CompletableFuture.completedFuture(userRepository.save(user));
    }

    @Async("taskExecutor")
    @Override
    public CompletableFuture<Optional<User>> getUserById(Long id) {
        Optional<User> userByIdOptional = userRepository.findById(id);
        return CompletableFuture.completedFuture(userByIdOptional);
    }


    @Async("taskExecutor")
    @Override
    public CompletableFuture<Optional<User>> getUserByUsername(String username) {
        return CompletableFuture.completedFuture(userRepository.findByUsername(username));
    }


    @Async("taskExecutor")
    @Override
    public CompletableFuture<Optional<User>> getUserByEmail(String email) {
       return CompletableFuture.completedFuture(userRepository.findByEmail(email));
    }

    @Async("taskExecutor")
    @Override
    public CompletableFuture<List<User>> getAllUsers() {
        return CompletableFuture.completedFuture(userRepository.findAll());
    }

    @Async("taskExecutor")
    @Override
    public CompletableFuture<User> updateUser(Long id, User userDetails) {
        return CompletableFuture.supplyAsync(
                ()->
                {
                   return userRepository.findById(id).map(
                       user -> {
                           user.setUsername(userDetails.getUsername());
                           user.setPassword(userDetails.getPassword());
                           user.setEmail(userDetails.getEmail());
                           user.setRole(userDetails.getRole());
                           user.setActive(userDetails.isActive());
                           return userRepository.save(user);
                       }
                    ).orElseThrow(()-> new RuntimeException("User with id "+id+" not found"));

                }
        );

    }

    @Async("taskExecutor")
    @Override
    public CompletableFuture<Void> deleteUser(Long id) {
        return CompletableFuture.runAsync(()->userRepository.deleteById(id));
    }
}
