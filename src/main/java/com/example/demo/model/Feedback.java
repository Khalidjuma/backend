package com.example.demo.model;

import com.example.demo.model.Enum.AppointmentStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long feedbackId;
    private String comments;
    @OneToOne
    @JoinColumn(name = "appointmentId")
    private Appointment appointment;
    @Enumerated(EnumType.STRING)
    private AppointmentStatus notfication;

}
