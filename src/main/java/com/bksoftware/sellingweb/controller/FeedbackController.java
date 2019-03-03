package com.bksoftware.sellingweb.controller;

import com.bksoftware.sellingweb.entities.Feedback;
import com.bksoftware.sellingweb.service_impl.FeedbackService_Impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/public/feedbacks")
public class FeedbackController {

    @Autowired
    FeedbackService_Impl feedbackService;

    @GetMapping
    public ResponseEntity<List<Feedback>> findAllFeedback() {
        List<Feedback> feedbacks = feedbackService.findAllFeedback();
        if (feedbacks != null) {
            return new ResponseEntity<>(feedbacks, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
