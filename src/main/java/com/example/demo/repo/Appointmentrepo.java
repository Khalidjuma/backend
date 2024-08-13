package com.example.demo.repo;

import com.example.demo.model.Appointment;
import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface Appointmentrepo extends JpaRepository<Appointment,Long> {

    @Query(value = "Select * from user er JOIN staff sf on sf.user_id = er.user_id where sf.user_id =?1  ",nativeQuery = true)
    Optional<Appointment> findStaffByUserId(Long userID);


    @Query(value = "Select * from user er JOIN student st on st.user_id = er.user_id where st.user_id =?1  ",nativeQuery = true)
    Optional<Appointment> findStudentByUserId(Long userID);

    @Query(value = "Select * from appoitment ap where ap.status  =?1  ",nativeQuery = true)
    List<Appointment> findAppoitmentByStatus(String status);



}
