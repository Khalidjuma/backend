package com.example.demo.model;

import com.example.demo.model.Enum.AppointmentStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
import java.sql.Time;

@Entity
@Data
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long appointmentId;
    @ManyToOne
    private Student student;
    @ManyToOne
    private Staff staff;
    private Time appointmentTime;
    private String appointmentReason;
    private Date appointmentDate;
    @Enumerated(EnumType.STRING)
    private AppointmentStatus status;


    public Appointment(Long appointment) {
    }

    public Appointment() {

    }
}
