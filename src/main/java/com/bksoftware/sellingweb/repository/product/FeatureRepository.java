package com.bksoftware.sellingweb.repository.product;

import com.bksoftware.sellingweb.entities.product.Feature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeatureRepository extends JpaRepository<Feature,Integer> {

}
