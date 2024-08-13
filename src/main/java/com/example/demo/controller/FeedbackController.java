package com.example.demo.controller;

import com.example.demo.Services.FeedBackService;
import com.example.demo.dto.FeedbackRequest;
import com.example.demo.dto.FeedbackResponse;;
import com.example.demo.model.Feedback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/feedbacks")
public class FeedbackController {

    @Autowired
    private FeedBackService feedbackServices;

    // GET all feedbacks
    @GetMapping
    public ResponseEntity<List<FeedbackResponse>> getAllFeedbacks() {
        List<Feedback> feedbacks = feedbackServices.getAllFeedback();
        List<FeedbackResponse> response = feedbacks.stream()
                .map(FeedbackResponse::new) // Use constructor reference
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    // GET feedback by ID
    @GetMapping("/{id}")
    public ResponseEntity<FeedbackResponse> getFeedbackById(@PathVariable Long id) {
        Feedback feedback = feedbackServices.getFeedbackById(id)
                .orElseThrow(() -> new RuntimeException("Feedback not found"));

        FeedbackResponse feedbackResponse = new FeedbackResponse(feedback);

        return ResponseEntity.ok(feedbackResponse);
    }

    // POST create new feedback
    @PostMapping
    public ResponseEntity<FeedbackResponse> createFeedback(@RequestBody FeedbackRequest feedbackRequest) {
        Feedback savedFeedback = feedbackServices.saveFeedback(feedbackRequest);
        FeedbackResponse feedbackResponse = new FeedbackResponse(savedFeedback);
        return ResponseEntity.ok(feedbackResponse);
    }

    // PUT update existing feedback
    @PutMapping("/{id}")
    public ResponseEntity<Boolean> updateFeedback(@PathVariable Long id,
                                                  @RequestBody FeedbackRequest feedbackRequest) {
        feedbackServices.updateFeedback(id, feedbackRequest);
        return ResponseEntity.ok(Boolean.TRUE);
    }


    @PutMapping("/{id}/status")
    public ResponseEntity<List<FeedbackResponse>> changeStatus(@PathVariable Long id) {
        List<Feedback> updatedAppointments = feedbackServices.changeStatus(id);
        List<FeedbackResponse> response = updatedAppointments.stream()
                .map(FeedbackResponse::new) // Use constructor reference
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }
    // DELETE delete feedback
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFeedback(@PathVariable Long id) {
        feedbackServices.deleteFeedback(id);
        return ResponseEntity.noContent().build();
    }
}
