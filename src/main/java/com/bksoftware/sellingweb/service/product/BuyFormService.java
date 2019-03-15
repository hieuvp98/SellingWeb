package com.bksoftware.sellingweb.service.product;

import com.bksoftware.sellingweb.entities.product.BuyForm;
import com.bksoftware.sellingweb.entities.product.BuyFormHasProduct;
import com.bksoftware.sellingweb.entities.product.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BuyFormService {

    List<BuyForm> findAllUnCheckBuyForm();

    boolean checkBuyForm(BuyForm buyForm);

    BuyForm findById(int id);

    boolean saveBuyForm(BuyForm buyForm);

    List<BuyFormHasProduct> findAllBuyFormHasProductByBuyFormId(int id);

    BuyFormHasProduct findByBuyFormAndProduct(BuyForm buyForm, Product product);

    boolean updateBuyFormHasProduct(BuyFormHasProduct buyFormHasProduct);
}
