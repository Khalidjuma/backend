package com.example.demo.dto;

import com.example.demo.model.Student;
import jakarta.persistence.Column;
import lombok.Data;

@Data
public class StudentResponse {
    @Column(unique = true)
    private  String regNumber;

    private ProgramResponse program;
    private Long userId;

    public StudentResponse(Student student) {
        this.regNumber = student.getRegNumber();
        this.program = new ProgramResponse(student.getProgram()); // Assuming Student has getProgram()
        this.userId = student.getUserId();
    }
}
