package com.edu.cs554.airlinesreservationsystem.repositories;

import com.edu.cs554.airlinesreservationsystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUserName(String username);
    User findById(int id);
}
