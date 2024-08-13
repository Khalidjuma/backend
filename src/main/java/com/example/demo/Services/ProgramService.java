package com.example.demo.Services;

import com.example.demo.dto.ProgramRequest;
import com.example.demo.model.Program;
import com.example.demo.repo.ProgramRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProgramService {

    @Autowired
    private ProgramRepo programRepository;

    public List<Program> getAllPrograms() {
        return programRepository.findAll();
    }

    public Optional<Program> getProgramById(Long id) {
        return programRepository.findById(id);
    }

    public Program saveProgram(ProgramRequest programRequest) {
        Program program = new Program();
        program.setProName(programRequest.getProName());
        program.setProLocation(programRequest.getProLocation());
        return programRepository.save(program);
    }

    public void deleteProgram(Long id) {
        programRepository.deleteById(id);
    }

    public Program updateProgram(Long id, ProgramRequest updatedProgram) {
        Program program = programRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Program with id " + id + " not found"));
        program.setProName(updatedProgram.getProName());
        program.setProLocation(updatedProgram.getProLocation());
        return programRepository.save(program);
    }
}
