package com.example.demo.model;

import com.example.demo.model.Enum.Role;
import com.example.demo.model.Enum.Status;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @Column(unique = true)
    private  String username;
    private String fullName;
    private String phoneNumber;
    @Column(unique = true)
    private String email;
    private String password;
    private String profileImage;
    @Enumerated(EnumType.STRING)
    private Role role;
    @Enumerated(EnumType.STRING)
    private Status status;

}
