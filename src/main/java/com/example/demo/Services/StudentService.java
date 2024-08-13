package com.example.demo.Services;

import com.example.demo.dto.StudentRequest;
import com.example.demo.dto.UserRequest;
import com.example.demo.model.Enum.Role;
import com.example.demo.model.Program;
import com.example.demo.model.Student;
import com.example.demo.model.User;
import com.example.demo.repo.ProgramRepo;
import com.example.demo.repo.StudentRepo;
import com.example.demo.repo.Userrepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private static final String IMAGE_PATH = "src/main/resources/images/";

    @Autowired
    private StudentRepo studentRepository;

    @Autowired
    private Userrepo userRepo;

    @Autowired
    private ProgramRepo programRepository;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Optional<Student> getStudentById(Long id) {
        return studentRepository.findById(id);
    }

    public void saveStudent(UserRequest userRequest, StudentRequest studentRequest, MultipartFile imageFile) throws IOException {
        String imageName = saveProfileImage(imageFile);

        User user = new User();
        user.setFullName(userRequest.getFullName());
        user.setStatus(userRequest.getStatus());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());
        user.setPhoneNumber(userRequest.getPhoneNumber());
        user.setUsername(userRequest.getUsername());
        user.setProfileImage(imageName);
        user.setRole(Role.Student);

        User savedUser = userRepo.save(user);

        Student student = new Student();
        student.setRegNumber(studentRequest.getRegNumber());
        student.setProgram(programRepository.findById(studentRequest.getProgram())
                .orElseThrow(() -> new EntityNotFoundException("Program with id " + studentRequest.getProgram() + " not found")));
        student.setUserId(savedUser.getUserId());


        studentRepository.save(student);
    }

    private String saveProfileImage(MultipartFile imageFile) throws IOException {
        String fileName = imageFile.getOriginalFilename();
        String uploadPath = IMAGE_PATH;

        Path uploadDir = Paths.get(uploadPath);

        if (!Files.exists(uploadDir)) {
            Files.createDirectories(uploadDir);
        }

        Path filePath = uploadDir.resolve(fileName);
        Files.copy(imageFile.getInputStream(), filePath);

        return fileName;
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    public Student updateStudent(Long id, StudentRequest updatedStudent) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Student with id " + id + " not found"));

        student.setRegNumber(updatedStudent.getRegNumber());
        student.setProgram(programRepository.findById(updatedStudent.getProgram())
                .orElseThrow(() -> new EntityNotFoundException("Program with id " + updatedStudent.getProgram() + " not found")));
        student.setUserId(updatedStudent.getUserId());

        return studentRepository.save(student);
    }
}
