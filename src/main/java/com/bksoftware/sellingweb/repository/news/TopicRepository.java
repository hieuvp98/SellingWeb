package com.bksoftware.sellingweb.repository.news;

import com.bksoftware.sellingweb.entities.news.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<Topic,Integer> {
}
