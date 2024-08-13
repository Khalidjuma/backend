package com.example.demo.Services;

import com.example.demo.dto.AvailabilityRequest;
import com.example.demo.model.Availability;
import com.example.demo.model.Staff;
import com.example.demo.repo.AvailabilityRepo;
import com.example.demo.repo.Staffrepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AvailabilityService {

    @Autowired
    private AvailabilityRepo availabilityRepository;

    @Autowired
    private Staffrepo staffRepository;

    public List<Availability> getAllAvailabilities() {
        return availabilityRepository.findAll();
    }

    public Optional<Availability> getAvailabilityById(Long id) {
        return availabilityRepository.findById(id);
    }

    public Availability saveAvailability(AvailabilityRequest availabilityRequest) {
        Availability availability = new Availability();
        Staff staff =staffRepository.findById(availabilityRequest.getStaff())
                .orElseThrow(() -> new EntityNotFoundException("Staff with id "+ availabilityRequest.getStaff() + "not found"));
        availability.setStaff(staff);
        availability.setDayAvailable(availabilityRequest.getDayAvailable());
        availability.setDayAvailable(availabilityRequest.getDayAvailable());

        return availabilityRepository.save(availability);
    }

    public void deleteAvailability(Long id) {
        availabilityRepository.deleteById(id);
    }

    public Availability updateAvailability(Long id, AvailabilityRequest updatedAvailability) {
        Availability availability = availabilityRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Availability with id " + id + " not found"));
        availability.setStaff(new Staff(updatedAvailability.getStaff()));
        availability.setDayAvailable(updatedAvailability.getDayAvailable());
        availability.setTimeAvailable(updatedAvailability.getTimeAvailable());
        return availabilityRepository.save(availability);
    }

//
//    public List<Availability> findByStaffId(Long staffId) {
//        Staff staff = staffRepository.findById(staffId)
//                .orElseThrow(() -> new EntityNotFoundException("Staff with id " + staffId + " not found"));
//
//        return availabilityRepository.findByStaff(staff);
//    }
}
