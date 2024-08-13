package com.example.demo.dto;

import com.example.demo.model.Appointment;
import lombok.Data;

import java.sql.Date;
import java.sql.Time;

@Data
public class AppoitmentResponse {
    private Long appointmentId;
    private StaffResponse staff;
    private StudentRequest student;
    private Time appointmentTime;
    private String appointmentReason;
    private Date appointmentDate;
    private String status;

    public AppoitmentResponse(Appointment appointment) {
        this.appointmentId = appointment.getAppointmentId();
  // Assuming StudentRequest has a constructor that accepts Student
        this.appointmentTime = appointment.getAppointmentTime();
        this.appointmentReason = appointment.getAppointmentReason();
        this.appointmentDate = appointment.getAppointmentDate();

    }

}
