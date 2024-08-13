package com.example.demo.dto;

import com.example.demo.model.Enum.AppointmentStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.sql.Date;
import java.sql.Time;
@Data
public class AppointmentRequest {
    private Long appointmentId;
    private  Long student;
    private  Long staff;
    private Time appointmentTime;
    private String appointmentReason;
    private Date appointmentDate;
    @Enumerated(EnumType.STRING)
    private AppointmentStatus status;
}
