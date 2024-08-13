package com.example.demo.repo;

import com.example.demo.model.Appointment;
import com.example.demo.model.Availability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AvailabilityRepo extends JpaRepository<Availability,Long> {

    @Query(value = "Select * from staff st join  availability av on st.staff_id  = st.staff_id  where st.user_id    =?1  ",nativeQuery = true)
    List<Appointment> findAvailabilityByUserID(Long userID);
}
