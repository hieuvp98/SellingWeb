package com.bksoftware.sellingweb.repository;

import com.bksoftware.sellingweb.entities.product.Product;
import com.bksoftware.sellingweb.entities.product.ProductDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductDetailsRepository extends JpaRepository<ProductDetails, Integer> {

    @Query(
            "select p from ProductDetails pd " +
                    " join pd.product p join p.buyForm bf where bf.phoneNumber = :phone_number"
    )
    List<Product> findSoldDateToPhone(@Param("phone_number") int phone_number);



    ProductDetails findById(int id);

}
