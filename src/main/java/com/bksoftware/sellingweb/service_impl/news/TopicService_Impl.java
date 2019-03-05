package com.bksoftware.sellingweb.service_impl.news;

import com.bksoftware.sellingweb.entities.news.Topic;
import com.bksoftware.sellingweb.repository.news.TopicRepository;
import com.bksoftware.sellingweb.service.news.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class TopicService_Impl implements TopicService {

    Logger LOGGER = Logger.getLogger(TopicService_Impl.class.getName());

    @Autowired
    TopicRepository topicRepository;

    @Override
    public List<Topic> findAllTopic() {
        try {
            return topicRepository.findAll();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-all-topic-error : {0}", ex.getMessage());
        }
        return null;
    }
}
