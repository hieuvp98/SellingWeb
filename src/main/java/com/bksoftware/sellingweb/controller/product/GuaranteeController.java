package com.bksoftware.sellingweb.controller.product;

import com.bksoftware.sellingweb.service_impl.product.ProductService_Impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api/v1/public/guarantees")
public class GuaranteeController {

    @Autowired
    ProductService_Impl productService;

    @GetMapping
    public ResponseEntity<Object> findPhoneToGuarantee(@RequestParam("phone") int phone_number) {
        Map<String, Long> guarantees = productService.findGuaranteeToPhone(phone_number);

        for (Map.Entry<String, Long> entry : guarantees.entrySet()) {
            if (entry.getValue() < 0) {
                entry.setValue(0L);
            }
        }
        return new ResponseEntity<>(guarantees, HttpStatus.OK);
    }
}
