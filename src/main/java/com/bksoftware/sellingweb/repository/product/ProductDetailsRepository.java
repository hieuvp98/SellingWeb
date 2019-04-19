package com.bksoftware.sellingweb.repository.product;

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

    @Query("select p from ProductDetails p where p.status=true")
    List<ProductDetails> findAll();

    Page<ProductDetails> findByStatus(boolean status, Pageable pageable);

    ProductDetails findById(int id);

    ProductDetails findByProduct(Product product);

    @Query("select pd from ProductDetails pd where pd.status=true and pd.product.id= :idProduct")
    ProductDetails showProductDetailsById(@Param("idProduct")int idProduct);
}
