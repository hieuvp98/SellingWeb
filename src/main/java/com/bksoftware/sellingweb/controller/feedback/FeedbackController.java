package com.bksoftware.sellingweb.controller.feedback;

import com.bksoftware.sellingweb.entities.product.Feedback;
import com.bksoftware.sellingweb.service_impl.product.FeedbackService_Impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
    @RequestMapping("api/v1/public/feedback")
public class FeedbackController {


    @Autowired
    FeedbackService_Impl feedbackService;

    @GetMapping
    public ResponseEntity<List<Feedback>> findAllFeedback() {

        List<Feedback> feedback = feedbackService.findAllFeedback();

        if (feedback != null) {
            return new ResponseEntity<>(feedback, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @GetMapping("/count")
    public ResponseEntity<Object> countAllFeedback() {

        int count = feedbackService.countFeedbackAndReplies();

        if (count >= 0) {
            return new ResponseEntity<>(count, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody Feedback feedback) {
        feedback.setStatus(true);
        if (feedbackService.saveFeedback(feedback))
            return new ResponseEntity<>("add success", HttpStatus.OK);
        else
            return new ResponseEntity<>("add fails", HttpStatus.BAD_REQUEST);
    }


//    @GetMapping(value = "/cookie")
//    public ResponseEntity<Object> findAllFeedbackCookie(HttpServletRequest request) {
//
//        Map<String, String> feedbackMap = new HashMap<>();
//        Cookie cookie = null;
//        Cookie[] cookies = null;
//        // Get an array of Cookies associated with this domain
//        cookies = request.getCookies();
//        if (cookies != null) {
//            for (Cookie value : cookies) {
//                cookie = value;
//                feedbackMap.put(cookie.getName(), cookie.getValue());
//            }
//        } else {
//            System.out.println("No Cookie here");
//            return new ResponseEntity<Object>("No Cookie Here", HttpStatus.OK);
//        }
//        return new ResponseEntity<>(cookies, HttpStatus.OK);
//    }
}
