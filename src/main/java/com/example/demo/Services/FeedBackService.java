package com.example.demo.Services;

import com.example.demo.dto.FeedbackRequest;
import com.example.demo.model.Appointment;
import com.example.demo.model.Enum.AppointmentStatus;
import com.example.demo.model.Feedback;
import com.example.demo.repo.Appointmentrepo;
import com.example.demo.repo.Feedbackrepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class FeedBackService {
    @Autowired
    private Feedbackrepo feedbackRepository;
    @Autowired
    private Appointmentrepo appointmentrepo;
    public List<Feedback> getAllFeedback() {
        return feedbackRepository.findAll();
    }

    public Optional<Feedback> getFeedbackById(Long id) {
        return feedbackRepository.findById(id);
    }

    public Feedback saveFeedback(FeedbackRequest feedbackRequest) {
        Feedback feedback = new Feedback();
        Appointment appointment = appointmentrepo.findById(feedbackRequest.getAppointment())
                .orElseThrow(() -> new EntityNotFoundException("Appoitment with id "+ feedbackRequest.getAppointment() + "not found"));

        feedback.setAppointment(appointment);
        feedback.setComments(feedbackRequest.getComments());
        feedback.setNotfication(feedbackRequest.getNotfication());
        feedback.setNotfication(AppointmentStatus.Pending);
        return feedbackRepository.save(feedback);
    }

    public void deleteFeedback(Long id) {
        feedbackRepository.deleteById(id);
    }

    public ResponseEntity updateFeedback(Long id, FeedbackRequest feedbackRequest) {
        Feedback feedback = feedbackRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("feeback with that id  not found"));
        feedback.setAppointment(new Appointment(feedbackRequest.getAppointment()));
        feedback.setComments(feedbackRequest.getComments());
        feedback.setNotfication(feedbackRequest.getNotfication());
        feedbackRepository.save(feedback);
        return  ResponseEntity.ok(Boolean.TRUE);

    }


    public  List<Feedback> changeStatus(Long Id){
        Feedback feedback = feedbackRepository.findById(Id)
                .orElseThrow(() -> new RuntimeException("feeback with that id  not found"));
        if(feedback.getNotfication().equals(AppointmentStatus.Confirmed)){
            feedback.setNotfication(AppointmentStatus.Pending);

        }else {
            feedback.setNotfication(AppointmentStatus.Confirmed);
        }

        feedbackRepository.save(feedback);
        return List.of(feedback);


    }
}
