package com.example.demo.dto;

import com.example.demo.model.Feedback;
import lombok.Data;

@Data
public class FeedbackResponse {
    private String comments;

    private AppoitmentResponse appointment;

    private String notfication;

    public FeedbackResponse(Feedback feedback) {
    }
}
