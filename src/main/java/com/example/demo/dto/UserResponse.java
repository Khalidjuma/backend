package com.example.demo.dto;


import com.example.demo.model.User;
import jakarta.persistence.Column;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
@Data
public class UserResponse {
    private Long userId;
    @Column(unique = true)
    private  String username;
    private String fullName;
    private String phoneNumber;
    @Column(unique = true)
    private String email;
    private String password;
    private String profileImage;

    private String role;

    private String status;

    public UserResponse(User user) {
        this.userId = user.getUserId();
        this.username = user.getUsername();
        this.fullName = user.getFullName();
        this.phoneNumber = user.getPhoneNumber();
        this.email = user.getEmail();
        this.password = user.getPassword(); // Be cautious about returning the password in the response!
        this.profileImage = user.getProfileImage();

    }
}
