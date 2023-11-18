package com.example.springmailsender.service.impl;

import com.example.springmailsender.model.Confirmation;
import com.example.springmailsender.model.User;
import com.example.springmailsender.repository.ConfirmationRepository;
import com.example.springmailsender.repository.UserRepository;
import com.example.springmailsender.service.EmailService;
import com.example.springmailsender.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ConfirmationRepository confirmationRepository;
    private final EmailService emailService;

    @Override
    public User saveUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        user.setEnabled(false);
        userRepository.save(user);
        Confirmation confirmation = new Confirmation(user);
        confirmationRepository.save(confirmation);
        emailService.sendHtmlEmail(user.getName(),user.getEmail(),confirmation.getToken());
        return user;
    }


    @Override
    public Boolean verifyToken(String token) {
        Confirmation confirmation = confirmationRepository.findByToken(token);
        User user = userRepository.findByEmailIgnoreCase(confirmation.getUser().getEmail());
        user.setEnabled(true);
        userRepository.save(user);
        //confirmation Repository.delete (confirmation);
        return Boolean.TRUE;
    }
}