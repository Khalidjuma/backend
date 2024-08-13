package com.example.demo.dto;


import lombok.Data;

import java.sql.Date;
import java.sql.Time;
@Data
public class AvailabilityRequest {
    private long staff;
    private Date dayAvailable;
    private Time timeAvailable;
}
