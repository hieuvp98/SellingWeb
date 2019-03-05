package com.bksoftware.sellingweb.service.product;


import com.bksoftware.sellingweb.entities.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Map;


public interface ProductService {

    Map<String, Long> findGuaranteeToPhone(int phone_number);

    Page<Product> findProductByName(String name, Pageable pageable);

    Sort sortData(String type);

    Product findById(int id);

    boolean saveProduct(Product product);

    boolean deleteProduct(Product product);


}
