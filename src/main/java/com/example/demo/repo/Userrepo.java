package com.example.demo.repo;

import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface Userrepo extends JpaRepository<User,Long> {


    @Query(value = "Select * from user  er where er.email =?1 ",nativeQuery = true)
    Optional<User> findByEmail(String email);



}
