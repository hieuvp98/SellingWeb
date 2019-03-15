package com.bksoftware.sellingweb.repository.product;

import com.bksoftware.sellingweb.entities.product.BuyFormHasProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BuyFormHasProductRepository extends JpaRepository<BuyFormHasProduct,Integer> {

    BuyFormHasProduct findByBuyFormIdAndProductId(int buyFormId, int productId);

    List<BuyFormHasProduct> findAllByBuyFormId(int buyFormId);
}
