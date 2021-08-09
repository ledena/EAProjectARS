package com.edu.cs554.airlinesreservationsystem.repository;

import com.edu.cs554.airlinesreservationsystem.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(propagation = Propagation.REQUIRED)
public interface AdminRepository extends JpaRepository<Admin, Long>, CrudRepository<Admin, Long> {
}
