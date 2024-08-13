package com.example.demo.controller;

import com.example.demo.dto.AppoitmentResponse;
import com.example.demo.dto.UserRequest;
import com.example.demo.dto.UserResponse;
import com.example.demo.model.Appointment;
import com.example.demo.model.User;
import com.example.demo.Services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    @Autowired
    private UserServices userServices;

    // GET all users
    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        List<User> users = userServices.getAllUsers();
        List<UserResponse> response = users.stream()
                .map( UserResponse::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    // GET user by ID
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {
        User user = userServices.getUserById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        UserResponse userResponse = new UserResponse(user);
        return ResponseEntity.ok(userResponse);
    }

    // POST create new user
    @PostMapping
    public ResponseEntity<UserResponse> createUser(@ModelAttribute UserRequest userRequest,
                                                   @RequestPart MultipartFile imageFile) throws IOException {
        userServices.saveUser(userRequest, imageFile);
        return ResponseEntity.ok().build();
    }



    @PutMapping("/{id}/status")
    public ResponseEntity<List<UserResponse>> changeStatus(@PathVariable Long id) {
        List<User> updatedAppointments = userServices.changeStatus(id);
        List<UserResponse> response = updatedAppointments.stream()
                .map(UserResponse::new) // Use constructor reference
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userServices.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
    @PostMapping("/login")
    public ResponseEntity<?> loginAuthenticatation(@RequestBody UserRequest request) {
        try {
              String email = request.getEmail();
              String password = request.getPassword();
              User user = userServices.loginAuthentication(email,password);

              if (user == null){
                  return  new ResponseEntity<>("Invalid credentials", HttpStatus.UNAUTHORIZED);
              }

              return  ResponseEntity.ok(user);

        }
        catch (Exception ex){
            return  new ResponseEntity<>(ex.getMessage(),HttpStatus.CONFLICT);
        }
    }
}
