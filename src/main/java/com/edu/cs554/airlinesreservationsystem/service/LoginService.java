package com.edu.cs554.airlinesreservationsystem.service;

import com.edu.cs554.airlinesreservationsystem.dto.AuthRequest;
import com.edu.cs554.airlinesreservationsystem.model.User;

public interface LoginService {
    public String authenticate(AuthRequest request);
    public String getLoggedInUser();
    public User loggedInUser();
}
