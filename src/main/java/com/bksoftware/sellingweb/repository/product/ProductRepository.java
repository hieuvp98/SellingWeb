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

    @Query("SELECT p FROM Product p WHERE p.name LIKE CONCAT('%',:name_product,'%')")
    Page<Product> findByName(@Param("name_product") String name, Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.name LIKE CONCAT('%',:name_product,'%')")
    List<Product> findByNamePage(@Param("name_product") String name);


    @Query(value = " from Product p order by p.initDate desc ")
    Page<Product> findNewProducts(Pageable pageable);

    @Query(value = " from Product p order by (p.originCost-p.saleCost) asc ")
    Page<Product> findHotProducts(Pageable pageable);


    List<Product> findAllBySmallCategory(SmallCategory smallCategory);

    Product findById(int id);


    Page<Product> findByStatus(boolean status, Pageable pageable);

    @Query("select p from Product p where p.status=true and p.smallCategory.id= :id")
    Page<Product> showProduct(@Param("id") int id, Pageable pageable);

    @Query("select p from Product p where p.status=true and p.smallCategory.id= :id")
    List<Product> showProduct(@Param("id") int id);

    @Query("select p from Product p where p.status=true and p.smallCategory.id= :id")
    List<Product> findProductBySmall(@Param("id") int id);

    @Query("select p from Product p where p.status=true and p.smallCategory.id= :id and p.partner.id= :branch")
    Page<Product> showProductSmallBranch(@Param("id") int id, @Param("branch") int branch, Pageable pageable);

    @Query("select p from Product p where p.status=true and p.smallCategory.mediumCategory.id= :id")
    Page<Product> showProductByMedium(@Param("id") int id, Pageable pageable);

    @Query("select p from Product p where p.status=true and p.smallCategory.mediumCategory.id= :id")
    List<Product> findProductByMedium(@Param("id") int id);

    @Query("select p from Product p where p.status=true and p.smallCategory.mediumCategory.id= :id and p.partner.id= :branch")
    Page<Product> showProductByMediumBranch(@Param("id") int id, @Param("branch") int branch, Pageable pageable);

    @Query("select p from Product p where p.status=true and p.smallCategory.mediumCategory.bigCategory.id= :id")
    Page<Product> showProductByBig(@Param("id") int id, Pageable pageable);

    @Query("select p from Product p where p.status=true and p.smallCategory.mediumCategory.bigCategory.id= :id")
    List<Product> showProductByBigList(@Param("id") int id);

    @Query("select p from Product p where p.status=true and p.smallCategory.mediumCategory.bigCategory.id= :id and p.partner.id= :branch")
    Page<Product> showProductByBigBranch(@Param("id") int id, @Param("branch") int branch, Pageable pageable);

    @Query("select p from Product p where p.status=true and p.smallCategory.mediumCategory.bigCategory.id= :id" +
            " and (p.saleCost> :low and p.saleCost< :high)")
    Page<Product> findProductByPrice(@Param("id") int id, @Param("low") int low, @Param("high") int high, Pageable pageable);

    @Query("select p from Product p where p.status=true and p.smallCategory.mediumCategory.bigCategory.id= :id  and p.partner.id= :branch" +
            " and (p.saleCost> :low and p.saleCost< :high)")
    Page<Product> findProductByPriceBranch(@Param("id") int id, @Param("low") int low, @Param("high") int high, @Param("branch") int branch, Pageable pageable);

    @Query("select p from Product p where p.status=true and p.saleCost>0")
    Page<Product> showProductSale(Pageable pageable);


    @Query("select p from Product p where p.status=true")
    List<Product> findAll();
}
