package com.example.demo.dto;

import com.example.demo.model.Enum.Role;
import com.example.demo.model.Enum.Status;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
@Data
public class UserRequest {
    private Long userId;
    @Column(unique = true)
    private  String username;
    private String fullName;
    private String phoneNumber;
    @Column(unique = true)
    private String email;
    private String password;
//    private String profileImage;
    @Enumerated(EnumType.STRING)
    private Role role;
    @Enumerated(EnumType.STRING)
    private Status status;
}
