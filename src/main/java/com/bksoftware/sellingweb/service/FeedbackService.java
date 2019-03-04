package com.bksoftware.sellingweb.service;

import com.bksoftware.sellingweb.entities.product.Feedback;

import java.util.List;


public interface FeedbackService {
    List<Feedback> findAllFeedback();

    Integer countFeedbackAndReplies();
}
