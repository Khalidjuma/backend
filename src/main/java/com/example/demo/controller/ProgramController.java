package com.example.demo.controller;

import com.example.demo.dto.ProgramRequest;
import com.example.demo.dto.ProgramResponse;
import com.example.demo.dto.StaffResponse;
import com.example.demo.model.Program;
import com.example.demo.Services.ProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/programs")
public class ProgramController {

    @Autowired
    private ProgramService programService;

    // GET all programs
    @GetMapping
    public ResponseEntity<List<ProgramResponse>> getAllPrograms() {
        List<Program> programs = programService.getAllPrograms();
        List<ProgramResponse> response = programs.stream()
                .map(ProgramResponse::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    // GET program by ID
    @GetMapping("/{id}")
    public ResponseEntity<ProgramResponse> getProgramById(@PathVariable Long id) {
        Program program = programService.getProgramById(id)
                .orElseThrow(() -> new RuntimeException("Program not found"));

        ProgramResponse programResponse = new ProgramResponse(program);

        return ResponseEntity.ok(programResponse);
    }

    // POST create new program
    @PostMapping
    public ResponseEntity<ProgramResponse> createProgram(@RequestBody ProgramRequest programRequest) {
        Program savedProgram = programService.saveProgram(programRequest);
        ProgramResponse programResponse = new ProgramResponse(savedProgram);
        return ResponseEntity.ok(programResponse);
    }

    // PUT update existing program
    @PutMapping("/{id}")
    public ResponseEntity<ProgramResponse> updateProgram(@PathVariable Long id,
                                                         @RequestBody ProgramRequest programRequest) {
        Program updatedProgram = programService.updateProgram(id, programRequest);
        ProgramResponse programResponse = new ProgramResponse(updatedProgram);
        return ResponseEntity.ok(programResponse);
    }

    // DELETE delete program
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProgram(@PathVariable Long id) {
        programService.deleteProgram(id);
        return ResponseEntity.noContent().build();
    }
}
