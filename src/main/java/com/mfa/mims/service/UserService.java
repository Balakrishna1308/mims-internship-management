package com.mfa.mims.service;

import com.mfa.mims.entity.User;

import java.util.Optional;

public interface UserService {

    Optional<User> getUserById(Long id);
    User creteUser(User user);
}
