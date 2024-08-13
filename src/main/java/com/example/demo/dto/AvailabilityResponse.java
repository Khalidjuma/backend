package com.example.demo.dto;


import com.example.demo.model.Availability;
import lombok.Data;

import java.sql.Date;
import java.sql.Time;
@Data
public class AvailabilityResponse {
    private Long availId;

    private StaffResponse staff;
    private Date dayAvailable;
    private Time timeAvailable;

    public AvailabilityResponse(Availability availability) {
    }
}
