package com.bksoftware.sellingweb.repository;

import com.bksoftware.sellingweb.entities.product.Feature;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeatureRepository extends JpaRepository<Feature,Integer> {

}
