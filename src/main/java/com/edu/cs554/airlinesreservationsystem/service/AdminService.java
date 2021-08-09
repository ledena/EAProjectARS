package com.edu.cs554.airlinesreservationsystem.service;

import com.edu.cs554.airlinesreservationsystem.dto.AdminRegistrationRequest;
import com.edu.cs554.airlinesreservationsystem.model.Admin;

import java.util.List;
import java.util.Optional;

public interface AdminService {
    Admin create(AdminRegistrationRequest request);
    List<Admin> findAll();
    Optional<Admin> findById(long adminId);
    Admin update(Admin admin);
    boolean deleteAdminById(long adminId);

}
