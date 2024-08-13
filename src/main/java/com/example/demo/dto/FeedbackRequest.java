package com.example.demo.dto;


import com.example.demo.model.Enum.AppointmentStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class FeedbackRequest {

    private String comments;

    private Long appointment;
    @Enumerated(EnumType.STRING)
    private AppointmentStatus notfication;
}
