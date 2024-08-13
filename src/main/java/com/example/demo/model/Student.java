package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long studentId;
    @Column(unique = true)
    private  String regNumber;
    @ManyToOne
    private Program program;
    private Long userId;

    public  Student(Long studentId) {
        this.studentId = studentId;
    }

    public Student() {

    }
}
