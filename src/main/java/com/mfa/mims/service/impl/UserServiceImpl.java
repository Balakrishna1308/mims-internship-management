package com.mfa.mims.service.impl;

import com.mfa.mims.entity.User;
import com.mfa.mims.repository.UserRepository;
import com.mfa.mims.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional<User> getUserById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser;
    }

    @Override
    public User creteUser(User user) {
        return userRepository.save(user);
    }
}
