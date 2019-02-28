package com.bksoftware.sellingweb.repository;

import com.bksoftware.sellingweb.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query("select p.productDetails.guarantee  from Product p where p.buyForm.phoneNumber = :phone_number")
    int findGuaranteeToPhone(@Param("phone_number") int phone_number);

    @Query("select p.productDetails.soldDate  from Product p where p.buyForm.phoneNumber = :phone_number")
    LocalDate findSoldBuyToPhone(@Param("phone_number") int phone_number);


}
