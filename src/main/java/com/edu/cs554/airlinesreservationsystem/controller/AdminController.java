package com.edu.cs554.airlinesreservationsystem.controller;

import com.edu.cs554.airlinesreservationsystem.dto.AdminPatchRequest;
import com.edu.cs554.airlinesreservationsystem.dto.AdminRegistrationRequest;
import com.edu.cs554.airlinesreservationsystem.dto.AdminUpdateRequest;
import com.edu.cs554.airlinesreservationsystem.model.Admin;
import com.edu.cs554.airlinesreservationsystem.model.Person;
import com.edu.cs554.airlinesreservationsystem.service.AdminService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="/admin")
@Transactional()
public class AdminController {

    @Autowired
    private AdminService adminService;

    // Registers or Adds admin to DB
    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public Person createAdmin(@RequestBody AdminRegistrationRequest request) {
        return adminService.create(request);
    }

    @GetMapping(value = { "/", "" }, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Admin> getAllAdmins() {
        return adminService.findAll();
    }

    @GetMapping(value = { "/{adminId}" }, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAdmin(@PathVariable long adminId) throws JSONException {

        JSONObject responseBody = new JSONObject();

        Optional<Admin> optionalAdmin = adminService.findById(adminId);

        if (optionalAdmin.isPresent()) {
            return new ResponseEntity<>(optionalAdmin.get(), HttpStatus.OK);
        } else {
            responseBody.put("success", false);
            responseBody.put("message", "Admin not found");
            return new ResponseEntity<>(responseBody.toString(), HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping(value = { "/{adminId}" }, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateAdmin(@PathVariable long adminId, @RequestBody AdminUpdateRequest adminUpdateRequest) throws JSONException {

        Optional<Admin> optionalAdmin = adminService.findById(adminId);

        JSONObject responseBody = new JSONObject();
        HttpStatus httpStatus = HttpStatus.OK;

        if (optionalAdmin.isPresent()) {

            Admin admin = optionalAdmin.get();

            admin.setFirstName(adminUpdateRequest.getFirstName());
            admin.setLastName(adminUpdateRequest.getLastName());
            admin.setResidenceAddress(adminUpdateRequest.getResidenceAddress());
            admin = adminService.update(admin);

            responseBody.put("success", true);
            responseBody.put("message", "Admin successfully updated");
            responseBody.put("admin", admin);

            return new ResponseEntity<>(admin, httpStatus);

        } else {

            responseBody.put("success", false);
            responseBody.put("message", "Admin not found");
            responseBody.put("adminId", adminId);
            httpStatus = HttpStatus.BAD_REQUEST;
            return new ResponseEntity<>(responseBody.toString(), httpStatus);

        }
    }

    @PatchMapping(value = { "/{adminId}" }, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> patchAdmin(@PathVariable long adminId, @RequestBody AdminPatchRequest adminPatchRequest) throws JSONException {

        Optional<Admin> optionalAdmin = adminService.findById(adminId);

        JSONObject responseBody = new JSONObject();
        HttpStatus httpStatus = HttpStatus.OK;

        if (optionalAdmin.isPresent()) {

            Admin admin = optionalAdmin.get();

            admin.setFirstName(adminPatchRequest.getFirstName());
            admin.setLastName(adminPatchRequest.getLastName());
            admin = adminService.update(admin);

            responseBody.put("success", true);
            responseBody.put("message", "Admin successfully updated");
            responseBody.put("admin", admin);

            return new ResponseEntity<>(admin, httpStatus);

        } else {

            responseBody.put("success", false);
            responseBody.put("message", "Admin not found");
            responseBody.put("adminId", adminId);
            httpStatus = HttpStatus.BAD_REQUEST;
            return new ResponseEntity<>(responseBody.toString(), httpStatus);

        }
    }

    @DeleteMapping(value = { "/{adminId}" }, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteAdmin(@PathVariable long adminId) throws JSONException {

        JSONObject responseBody = new JSONObject();
        HttpStatus httpStatus = HttpStatus.OK;

        if (adminService.deleteAdminById(adminId)) {

            responseBody.put("success", true);
            responseBody.put("message", "Successfully deleted");
            responseBody.put("adminId", adminId);

        } else {

            responseBody.put("success", false);
            responseBody.put("message", "Unable to delete: Error");
            responseBody.put("adminId", adminId);
            httpStatus = HttpStatus.BAD_REQUEST;

        }
        return new ResponseEntity<>(responseBody.toString(), httpStatus);
    }

}
