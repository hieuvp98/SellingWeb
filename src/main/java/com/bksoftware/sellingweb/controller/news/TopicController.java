package com.bksoftware.sellingweb.controller.news;

import com.bksoftware.sellingweb.entities.news.Topic;
import com.bksoftware.sellingweb.service_impl.news.TopicService_Impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/public/topic")
public class TopicController {

    @Autowired
    TopicService_Impl topicService;

    @GetMapping
    public ResponseEntity<List<Topic>> findAllTopic() {
        List<Topic> topics = topicService.findAllTopic();
        if (topics != null) {
            return new ResponseEntity<>(topics, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
