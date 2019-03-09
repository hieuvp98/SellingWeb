package com.bksoftware.sellingweb.service.product;

import com.bksoftware.sellingweb.entities.product.BuyForm;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BuyFormService {

    List<BuyForm> findAllUnCheckBuyForm();

    boolean checkBuyForm(BuyForm buyForm);

    BuyForm findById(int id);
}
