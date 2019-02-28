package com.bksoftware.sellingweb.repository;

import com.bksoftware.sellingweb.entities.ProductDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductDetailsRepository extends JpaRepository<ProductDetails, Integer> {

}
