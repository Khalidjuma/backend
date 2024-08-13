package com.example.demo.dto;

import com.example.demo.model.Program;
import lombok.Data;

@Data
public class ProgramResponse {
    private Long proId;
    private String proName;
    private String proLocation;

    public ProgramResponse(Program program) {
    }
}
