package com.bksoftware.sellingweb.repository;

import com.bksoftware.sellingweb.entities.Product;
import com.bksoftware.sellingweb.entities.SmallCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {


    @Query(
            "select p from Product p " +
                    " join p.buyForm bf where bf.phoneNumber = :phone_number"
    )
    List<Product> findSoldDateToPhone(@Param("phone_number") int phone_number);

    @Query(
            value = "SELECT p.* FROM product p WHERE MATCH(p.name) AGAINST(:name_product IN BOOLEAN MODE)",
            countQuery = "SELECT count(*) FROM product p WHERE MATCH(p.name) AGAINST(:name_product IN BOOLEAN MODE)",
            nativeQuery = true)
    Page<Product> findByName(@Param("name_product") String name, Pageable pageable);

    //Pageable sẽ chứa các thông tin phân trang như số phần tử được lấy, vị trí trang được lấy
    //Page sẽ chứa kết quả trả về (gồm số phần tử, danh sách các phần tử)

    List<Product> findAllBySmallCategory(SmallCategory smallCategory);
}
