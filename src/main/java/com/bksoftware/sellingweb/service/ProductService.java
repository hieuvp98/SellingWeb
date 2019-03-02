package com.bksoftware.sellingweb.service;


import com.bksoftware.sellingweb.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Map;


public interface ProductService {

    Map<String, Long> findGuaranteeToPhone(int phone_number);

    Page<Product> findProductByName(String name, Pageable pageable);

    Sort sortData(String type);
}
