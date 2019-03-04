package com.bksoftware.sellingweb.repository;

import com.bksoftware.sellingweb.entities.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeedbackRepository extends JpaRepository<Feedback,Integer> {

}
