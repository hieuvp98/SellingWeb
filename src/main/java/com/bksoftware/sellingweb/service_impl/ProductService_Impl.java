package com.bksoftware.sellingweb.service_impl;


import com.bksoftware.sellingweb.entities.product.Product;
import com.bksoftware.sellingweb.entities.product.ProductDetails;
import com.bksoftware.sellingweb.repository.ProductDetailsRepository;
import com.bksoftware.sellingweb.repository.ProductRepository;
import com.bksoftware.sellingweb.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class ProductService_Impl implements ProductService {

    private static final Logger LOGGER = Logger.getLogger(ProductService_Impl.class.getName());


    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductDetailsRepository productDetailsRepository;


    public Map<String, Long> findGuaranteeToPhone(int phone_number) {

        Map<String, Long> mapProduct = new HashMap<>();
        try {
            List<Product> products = productDetailsRepository.findSoldDateToPhone(phone_number);
            products.forEach(p -> System.out.println(p.getName()));
            products.forEach(p -> {
                LocalDate date_now = LocalDate.now();
                LocalDate date_buy = p.getProductDetails().getSoldDate();
                long used_time = ChronoUnit.DAYS.between(date_buy, date_now);
                long guarantee = p.getProductDetails().getGuarantee();
                long day_lefts = guarantee - used_time;
                mapProduct.put(p.getName(), day_lefts);
            });

            return mapProduct;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-guarantee-by-phone-error : {0}", ex.getMessage());
        }
        return null;
    }

    @Override
    public Page<Product> findProductByName(String name, Pageable pageable) {
        try {
            return productRepository.findByName("+" + name + "*", pageable);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-product-by-name-error : {0}", ex.getMessage());
        }
        return null;
    }

    @Override
    public Sort sortData(String type) {
        Sort sortable = null;
        if (type.equals("ASC")) {
            sortable = Sort.by("view").ascending();
        }
        if (type.equals("DESC")) {
            sortable = Sort.by("view").descending();
        }
        return sortable;
    }


    @Override
    public Product findById(int id) {
        return productRepository.findById(id);
    }

    @Override
    public boolean saveProduct(Product product) {
        try {
            productRepository.save(product);
            return true;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "save-product-error", ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteProduct(Product product) {
        try {
            product.setStatus(false);
            productRepository.save(product);
            return true;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "delete-product-error : {0}", ex.getMessage());
            return false;
        }
    }


}
