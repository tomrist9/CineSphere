package com.example.springmailsender.service;

import com.example.springmailsender.model.User;

public interface UserService {
    User saveUser(User user);
    Boolean verifyToken(String token);
}