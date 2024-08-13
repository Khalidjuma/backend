package com.example.demo.Services;

import com.example.demo.dto.StaffRequest;
import com.example.demo.dto.UserRequest;
import com.example.demo.model.Enum.AppointmentStatus;
import com.example.demo.model.Enum.Role;
import com.example.demo.model.Feedback;
import com.example.demo.model.Staff;
import com.example.demo.model.User;
import com.example.demo.repo.Staffrepo;
import com.example.demo.repo.Userrepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
public class StaffServices {


    private static final String IMAGE_PATH = "src/main/resources/images/";
        @Autowired
        private Staffrepo staffRepository;
        @Autowired
        private Userrepo userrepo;
        public List<Staff> getAllStaff(){
            return  staffRepository.findAll();
        }



        public Optional<Staff> getStaffById(Long id) {
            return staffRepository.findById(id);
        }

        public void saveStaff(UserRequest userRequest, StaffRequest staffRequest, MultipartFile imageFile ) throws IOException  {
            String imageName = saveProfileImage(imageFile);
            User user = new User();
            user.setFullName(userRequest.getFullName());
            user.setStatus(userRequest.getStatus());
            user.setEmail(userRequest.getEmail());
            user.setPassword(userRequest.getPassword());
            user.setPhoneNumber(userRequest.getPhoneNumber());
            user.setUsername(userRequest.getUsername());
            user.setRole(Role.Staff);
            user.setProfileImage(imageName);
            User savedUser = userrepo.save(user);

           Staff staff = new Staff();
           staff.setStaffType(staffRequest.getStaffType());
           staff.setDepertment(staffRequest.getDepertment());
           staff.setPossition(staffRequest.getPossition());
           staff.setOfficeNumber(staffRequest.getOfficeNumber());
           staff.setUserId(savedUser.getUserId());

           staffRepository.save(staff);


        }

        public void deleteStaff(Long id) {
            staffRepository.deleteById(id);
        }

        public ResponseEntity updateStaff(Long id, StaffRequest staffRequest) {
            Staff staff = staffRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Staff with that id  not found"));

            staff.setStaffType(staffRequest.getStaffType());
            staff.setDepertment(staffRequest.getDepertment());
            staff.setPossition(staffRequest.getPossition());
            staff.setOfficeNumber(staffRequest.getOfficeNumber());
            staff.setUserId(staffRequest.getUserId());
            staffRepository.save(staff);
            return  ResponseEntity.ok(Boolean.TRUE);
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
    }

