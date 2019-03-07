package com.bksoftware.sellingweb.service_impl.product;


import com.bksoftware.sellingweb.entities.product.Feedback;
import com.bksoftware.sellingweb.entities.product.Product;
import com.bksoftware.sellingweb.repository.product.FeedbackRepository;
import com.bksoftware.sellingweb.repository.product.ProductDetailsRepository;
import com.bksoftware.sellingweb.repository.product.ProductRepository;
import com.bksoftware.sellingweb.repository.product.ReplyRepository;
import com.bksoftware.sellingweb.service.product.FeedbackService;
import com.bksoftware.sellingweb.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class FeedbackService_Impl implements FeedbackService {

    private final static Logger LOGGER = Logger.getLogger(FeedbackService_Impl.class.getName());

    private final
    FeedbackRepository feedbackRepository;

    @Autowired
    ReplyRepository replyRepository;

    public FeedbackService_Impl(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    @Override
    public List<Feedback> findAllFeedback() {
        try {
            List<Feedback> feedbacks = feedbackRepository.findAll();
            return feedbacks
                    .stream()
                    .filter(p -> (p.isStatus() == true))
                    .collect(Collectors.toList());
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-all-feedback-error : {0}", ex.getMessage());
        }
        return null;
    }

    @Override
    public Integer countFeedbackAndReplies() {
        try {
            RepLyService_Impl repLyService = new RepLyService_Impl();
            return findAllFeedback().size() + repLyService.findAllReplies().size();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "count-feedback-error : {0}", ex.getMessage());
        }
        return null;

    }


}
