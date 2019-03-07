package com.bksoftware.sellingweb.service.product;

import com.bksoftware.sellingweb.entities.product.Feedback;

import java.util.List;


public interface FeedbackService {
    List<Feedback> findAllFeedback();

    Integer countFeedbackAndReplies();

    boolean saveFeedback(Feedback feedback);

    boolean deleteFeedback(Feedback feedback);
}
