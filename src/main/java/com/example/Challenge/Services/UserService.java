package com.example.Challenge.Services;

import com.example.Challenge.Model.UserModel;
import com.example.Challenge.Repository.UserRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void registerUser(UserModel user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        // will be enabled after email verification
        userRepo.save(user);
    }

    public UserModel findByEmail(String email) {
        return userRepo.findByEmail(email).orElse(null);
    }


    public boolean emailExists(String email) {
        return userRepo.findByEmail(email).isPresent();
    }

}
