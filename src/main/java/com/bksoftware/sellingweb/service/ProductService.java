package com.bksoftware.sellingweb.service;

import com.bksoftware.sellingweb.entities.BuyForm;

import java.util.List;

public interface ProductService {

    List<BuyForm> findGuaranteeToPhone(int phone_number);
}
