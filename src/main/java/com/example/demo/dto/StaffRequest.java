package com.example.demo.dto;

import com.example.demo.model.Enum.StaffType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class StaffRequest {

    @Enumerated(EnumType.STRING)
    private StaffType staffType;
    private String possition;
    private String depertment;
    private String officeNumber;
    private Long userId;
}
