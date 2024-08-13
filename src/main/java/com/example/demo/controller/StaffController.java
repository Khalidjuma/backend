package com.example.demo.controller;

import com.example.demo.Services.StaffServices;
import com.example.demo.dto.StaffRequest;
import com.example.demo.dto.StaffResponse;
import com.example.demo.dto.UserRequest;
import com.example.demo.model.Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/staff")
@CrossOrigin(origins = "http://localhost:3000")
public class StaffController {

    @Autowired
    private StaffServices staffService;

    @GetMapping
    public ResponseEntity<List<StaffResponse>> getAllStaff() {
        List<Staff> staffList = staffService.getAllStaff();
        List<StaffResponse> response = staffList.stream()
                .map(StaffResponse::new)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(response);
        }

    @GetMapping("/{id}")
    public ResponseEntity<StaffResponse> getStaffById(@PathVariable Long id) {
        Staff staff = staffService.getStaffById(id)
                .orElseThrow(() -> new RuntimeException("Staff not found"));
        StaffResponse response = new StaffResponse(staff);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<StaffResponse> createStaff(@ModelAttribute UserRequest userRequest,
                                                     @ModelAttribute StaffRequest staffRequest,
                                                     @RequestPart MultipartFile imageFile) throws IOException {
        staffService.saveStaff(userRequest, staffRequest, imageFile);
        // Assuming you want to return some response after creation, modify accordingly
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<StaffResponse> updateStaff(@PathVariable Long id, @RequestBody StaffRequest updatedStaff) {
        ResponseEntity updated = staffService.updateStaff(id,updatedStaff);
        StaffResponse response = new StaffResponse(updated);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStaff(@PathVariable Long id) {
        staffService.deleteStaff(id);
        return ResponseEntity.ok().build();
    }
}
