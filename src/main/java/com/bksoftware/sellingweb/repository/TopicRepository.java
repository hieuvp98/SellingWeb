package com.bksoftware.sellingweb.repository;


import com.bksoftware.sellingweb.entities.news.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicRepository extends JpaRepository<Topic,Integer> {
}
