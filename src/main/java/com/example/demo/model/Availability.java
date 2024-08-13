package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
import java.sql.Time;

@Entity
@Data
public class Availability {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long availId;
    @ManyToOne
    @JoinColumn(name = "staffId")
    private Staff staff;
    private Date dayAvailable;
    private Time timeAvailable;



}
