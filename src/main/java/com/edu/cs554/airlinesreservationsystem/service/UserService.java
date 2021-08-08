package com.edu.cs554.airlinesreservationsystem.service;

import com.edu.cs554.airlinesreservationsystem.dto.RegistrationRequest;
import com.edu.cs554.airlinesreservationsystem.model.Role;
import com.edu.cs554.airlinesreservationsystem.model.User;
import com.edu.cs554.airlinesreservationsystem.repositories.UserRepository;
import com.edu.cs554.airlinesreservationsystem.util.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
@Transactional
@AllArgsConstructor
public class UserService implements UserDetailsService {

//    @Autowired
//    private AuthenticationManager authenticationManager;

    @Autowired
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(userName);
        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), new ArrayList<>());
    }

    public User register(RegistrationRequest request){

        User user = new User(request.getUserName(), request.getPassword(), Role.PASSENGER);

        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        return userRepository.save(user);

    }

//    public ResponseEntity<Object> authenticate(AuthRequest request) throws Throwable {
//        JSONObject responseObject = new JSONObject();
//        if(!validateInputs(request.getEmail())){
//            responseObject.put("email","Email is required");
//        }
//        if(!validateInputs(request.getPassword())){
//            responseObject.put("password","Password is required");
//        }
//        if(!responseObject.isEmpty()){
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseObject);
//        }else{
//            try {
//                authenticationManager.authenticate(
//                        new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
//                );
//            } catch (Exception ex) {
//                responseObject.put("credentials","Invalid credentials");
//                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseObject);
//            }
//            String token = jwtUtil.generateToken(request.getEmail());
//            responseObject.put("success",true);
//            responseObject.put("token","Bearer " +token);
//            return ResponseEntity.status(HttpStatus.OK).body(responseObject);
//        }
//
//    }
//
//    public String getLoggedInUserEmail(){
//        String username;
//        String email;
//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        if (principal instanceof UserDetails) {
//            username = ((UserDetails)principal).getUsername();
//        } else {
//            username = principal.toString();
//        }
//        email = repository.findByUserName(username).getEmail();
//        return email;
//    }
//
//    public ResponseEntity<Object> loggedInUser() {
//        JSONObject response = new JSONObject();
//        User user = repository.findByEmail(getLoggedInUserEmail());
//        if(user != null){
//            return ResponseEntity.status(HttpStatus.OK).body(user);
//        }else{
//            response.put("success",false);
//            response.put("message", "No user found");
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
//        }
//    }
//
//    public boolean validateInputs(String input){
//        if(input == null || input == ""){
//            return false;
//        } else return true;
//    }

}
