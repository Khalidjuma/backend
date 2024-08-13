package com.example.demo.repo;

import com.example.demo.model.Appointment;
import com.example.demo.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface Feedbackrepo extends JpaRepository<Feedback,Long> {

    @Query(value = "Select * from staff st join  availability av on st.staff_id  = st.staff_id  where st.user_id    =?1  ",nativeQuery = true)
    List<Appointment> findAvailabilityByUserID(Long userID);
}
