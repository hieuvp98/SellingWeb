package com.bksoftware.sellingweb.repository;

import com.bksoftware.sellingweb.entities.product.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductImageRepository extends JpaRepository<ProductImage,Integer> {
}
