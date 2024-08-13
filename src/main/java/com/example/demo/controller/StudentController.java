package com.example.demo.controller;
import com.example.demo.Services.StudentService;
import com.example.demo.dto.StudentRequest;
import com.example.demo.dto.StudentResponse;
import com.example.demo.dto.UserRequest;
import com.example.demo.model.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public ResponseEntity<List<StudentResponse>> getAllStudents() {
        List<Student> students = studentService.getAllStudents();
        List<StudentResponse> response = students.stream()
                .map(StudentResponse::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentResponse> getStudentById(@PathVariable Long id) {
        Student student = studentService.getStudentById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        StudentResponse response = new StudentResponse(student);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<StudentResponse> createStudent(@ModelAttribute UserRequest userRequest,
                                                         @ModelAttribute StudentRequest studentRequest,
                                                         @RequestPart MultipartFile imageFile) throws IOException {
        studentService.saveStudent(userRequest, studentRequest, imageFile);
        // Assuming you want to return some response after creation, modify accordingly
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentResponse> updateStudent(@PathVariable Long id, @RequestBody StudentRequest updatedStudent) {
        Student updated = studentService.updateStudent(id, updatedStudent);
        StudentResponse response = new StudentResponse(updated);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok().build();
    }
}
