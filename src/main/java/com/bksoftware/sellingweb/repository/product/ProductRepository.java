package com.bksoftware.sellingweb.repository.product;

import com.bksoftware.sellingweb.entities.product.Product;
import com.bksoftware.sellingweb.entities.category.SmallCategory;
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
            value = "SELECT p.* FROM product p  WHERE MATCH(p.name) AGAINST(:name_product IN BOOLEAN MODE)",
            nativeQuery = true)
    Page<Product> findByName(@Param("name_product") String name, Pageable pageable);

    //Pageable sẽ chứa các thông tin phân trang như số phần tử được lấy, vị trí trang được lấy
    //Page sẽ chứa kết quả trả về (gồm số phần tử, danh sách các phần tử)


    List<Product> findAllBySmallCategory(SmallCategory smallCategory);

    Product findById(int id);
}
