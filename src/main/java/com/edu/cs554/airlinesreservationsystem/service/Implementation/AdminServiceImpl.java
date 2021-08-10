package com.edu.cs554.airlinesreservationsystem.service.Implementation;

import com.edu.cs554.airlinesreservationsystem.dto.AdminRegistrationRequest;
import com.edu.cs554.airlinesreservationsystem.model.Admin;
import com.edu.cs554.airlinesreservationsystem.model.Role;
import com.edu.cs554.airlinesreservationsystem.model.User;
import com.edu.cs554.airlinesreservationsystem.repository.AdminRepository;
import com.edu.cs554.airlinesreservationsystem.service.AdminService;
import com.edu.cs554.airlinesreservationsystem.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private UserService userService;

    public List<Admin> findAll() {
        return adminRepository.findAll();
    }

    public Optional<Admin> findById(long adminId) {
        return adminRepository.findById(adminId);
    }

    public Admin update(Admin admin) {
        return adminRepository.save(admin);
    }

    @Override
    public boolean deleteAdminById(long adminId) {
        if(adminRepository.existsById(adminId)) {
            adminRepository.deleteById(adminId);
            return !adminRepository.existsById(adminId);
        } else
            return false;
    }

    @Override
    public Admin create(AdminRegistrationRequest request) {

        User user = new User(request.getUser().getUserName(), request.getUser().getPassword(), Role.ADMIN);

        user = userService.encodePassword(user);

        Admin admin = new Admin(request.getFirstName(), request.getLastName(), request.getResidenceAddress(), user);

        return adminRepository.save(admin);

    }

}
