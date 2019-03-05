package com.bksoftware.sellingweb.service_impl;


import com.bksoftware.sellingweb.entities.product.Feedback;
import com.bksoftware.sellingweb.repository.product.FeedbackRepository;
import com.bksoftware.sellingweb.service.FeedbackService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class FeedbackService_Impl implements FeedbackService {

    private final static Logger LOGGER = Logger.getLogger(FeedbackService_Impl.class.getName());

    private final
    FeedbackRepository feedbackRepository;

    public FeedbackService_Impl(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    @Override
    public List<Feedback> findAllFeedback() {
        try {
            return feedbackRepository.findAll();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-all-feedback-error : {0}", ex.getMessage());
        }
        return null;
    }

    @Override
    public Integer countFeedbackAndReplies() {
        try {
            return feedbackRepository.findAll().size() + feedbackRepository.findAll().size();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "count-feedback-error : {0}", ex.getMessage());
        }
        return null;

    }


}
