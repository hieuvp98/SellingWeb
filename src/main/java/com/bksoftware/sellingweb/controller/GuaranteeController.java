package com.bksoftware.sellingweb.controller;

import com.bksoftware.sellingweb.service_impl.ProductService_Impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/guarantee")
public class GuaranteeController {

    @Autowired
    ProductService_Impl productService;

    @GetMapping("{/phone_number}")
    public ResponseEntity<Object> findPhoneToGuarantee(@PathVariable(value = "phone_number") int phone_number) {
        int guarantee = productService.findGuaranteeToPhone(phone_number);
        if (guarantee <= 0) {
            return new ResponseEntity<>("Time Up Guarantee", HttpStatus.OK);
        }
        return new ResponseEntity<>(guarantee, HttpStatus.OK);
    }
}
