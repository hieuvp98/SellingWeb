package com.bksoftware.sellingweb.repository;

import com.bksoftware.sellingweb.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query(
            "select pd.guarantee  from Product p join p.productDetails pd where p.buyForm.phoneNumber = :phone_number"
    )
    int findGuaranteeToPhone(@Param("phone_number") int phone_number);


    @Query(
            "select pd.soldDate from Product p " +
                    "join p.productDetails pd join p.buyForm bf where bf.phoneNumber = :phone_number"
    )
    LocalDate findSoldDateToPhone(@Param("phone_number") int phone_number);


}
