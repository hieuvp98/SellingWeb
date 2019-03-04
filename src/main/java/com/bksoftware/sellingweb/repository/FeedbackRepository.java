package com.bksoftware.sellingweb.repository;

import com.bksoftware.sellingweb.entities.product.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackRepository extends JpaRepository<Feedback,Integer> {

}
