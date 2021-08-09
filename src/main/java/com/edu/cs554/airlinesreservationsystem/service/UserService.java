package com.edu.cs554.airlinesreservationsystem.service;

import com.edu.cs554.airlinesreservationsystem.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    public User encodePassword(User user);
}
