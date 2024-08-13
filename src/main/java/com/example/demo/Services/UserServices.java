package com.example.demo.Services;

import com.example.demo.dto.UserRequest;
import com.example.demo.model.Enum.Role;
import com.example.demo.model.Enum.Status;
import com.example.demo.model.User;
import com.example.demo.repo.Userrepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
@Service
public class UserServices {
    private static final String IMAGE_PATH = "src/main/resources/images/";
    @Autowired
    private Userrepo userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public void saveUser(UserRequest userRequest, MultipartFile imageFile) throws IOException {
        // Check if password is not null or empty
        if (userRequest.getPassword() == null || userRequest.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }

        // Save profile image and get the image name
        String imageName = saveProfileImage(imageFile);

        // Create a new User object
        User user = new User();
        user.setFullName(userRequest.getFullName());
        user.setStatus(userRequest.getStatus()); // Ensure Status enum is handled properly
        user.setEmail(userRequest.getEmail());

        // Encode password only once
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));

        user.setPhoneNumber(userRequest.getPhoneNumber());
        user.setUsername(userRequest.getUsername());
        user.setProfileImage(imageName);
        user.setRole(userRequest.getRole()); // Ensure Role enum is handled properly

        // Save user to the repository
        userRepository.save(user);
    }


    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public User updateUser(Long id,UserRequest userRequest) {
     User user = userRepository.findById(id)
             .orElseThrow(() -> new EntityNotFoundException("User with id " + id + " not found"));
     user.setEmail(userRequest.getEmail());
     user.setPassword(passwordEncoder.encode(passwordEncoder.encode(userRequest.getPassword())));
     user.setPhoneNumber(userRequest.getPhoneNumber());
     user.setFullName(userRequest.getFullName());
     user.setUsername(userRequest.getUsername());

     return  userRepository.save(user);

    }
    public  List<User> changeStatus(Long Id){
        User user = userRepository.findById(Id)
                .orElseThrow(() -> new RuntimeException("User with that id  not found"));
        if(user.getStatus().equals(Status.Active)){
            user.setStatus(Status.Block);

        }else {
            user.setStatus(Status.Active);
        }

        userRepository.save(user);
        return List.of(user);
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


    public User loginAuthentication(String email , String password){

            Optional<User> loginUser = userRepository.findByEmail(email);
            if(loginUser.isPresent()){
                User user = loginUser.get();

                if (passwordEncoder.matches(password,user.getPassword())){
                    return  user;
                }
                
            }

            return  null;
    }

}
