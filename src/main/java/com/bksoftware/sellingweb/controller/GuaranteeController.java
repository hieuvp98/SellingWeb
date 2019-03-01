package com.bksoftware.sellingweb.controller;

import com.bksoftware.sellingweb.dao.ProductDao;
import com.bksoftware.sellingweb.service_impl.ProductService_Impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/public/guarantee")
public class GuaranteeController {

    @Autowired
    ProductService_Impl productService;

    @Autowired
    ProductDao productDao;

    @GetMapping
    public ResponseEntity<Object> findPhoneToGuarantee(@RequestParam("phone") int phone_number) {
        int guarantee = productService.findGuaranteeToPhone(phone_number);
        if (guarantee <= 0) {
            return new ResponseEntity<>("Time Up Guarantee", HttpStatus.OK);
        }
        return new ResponseEntity<>(guarantee, HttpStatus.OK);
    }
}
