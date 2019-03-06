package com.bksoftware.sellingweb.repository.product;

import com.bksoftware.sellingweb.entities.product.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FeedbackRepository extends JpaRepository<Feedback,Integer> {

}
