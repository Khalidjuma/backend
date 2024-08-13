package com.example.demo.controller;

import com.example.demo.Services.AppointmentServices;
import com.example.demo.dto.AppointmentRequest;

import com.example.demo.dto.AppoitmentResponse;
import com.example.demo.model.Appointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/appointments")
@CrossOrigin(origins = "http://localhost:3000/")
public class AppointmentController {

    @Autowired
    private AppointmentServices appointmentService;

    @GetMapping
    public ResponseEntity<List<AppoitmentResponse>> getAllAppointments() {
        List<Appointment> appointments = appointmentService.getAllAppointments();
        List<AppoitmentResponse> response = appointments.stream()
                .map(AppoitmentResponse::new) // Use constructor reference
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppoitmentResponse> getAppointmentById(@PathVariable Long id) {
        Appointment appointment = appointmentService.getAppointmentById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));
        AppoitmentResponse response = new AppoitmentResponse(appointment); // Use constructor
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<AppoitmentResponse> createAppointment(@RequestBody AppointmentRequest appointmentRequest) {
        Appointment savedAppointment = appointmentService.saveAppointment(appointmentRequest);
        AppoitmentResponse response = new AppoitmentResponse(savedAppointment); // Use constructor
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseEntity> updateAppointment(@PathVariable Long id, @RequestBody AppointmentRequest updatedAppointment) {
        ResponseEntity updated = appointmentService.updateAppointment(id,updatedAppointment);
        return ResponseEntity.ok(updated);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<List<AppoitmentResponse>> changeStatus(@PathVariable Long id) {
        List<Appointment> updatedAppointments = appointmentService.changeStatus(id);
        List<AppoitmentResponse> response = updatedAppointments.stream()
                .map(AppoitmentResponse::new) // Use constructor reference
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }
}
