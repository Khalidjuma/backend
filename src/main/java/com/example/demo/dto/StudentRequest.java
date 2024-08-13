package com.example.demo.dto;


import jakarta.persistence.Column;
import lombok.Data;

@Data
public class StudentRequest {

    @Column(unique = true)
    private  String regNumber;

    private Long program;
    private Long userId;
}
