package com.bksoftware.sellingweb.controller.product;

import com.bksoftware.sellingweb.dao.BuyFormDao;
import com.bksoftware.sellingweb.entities.product.BuyForm;
import com.bksoftware.sellingweb.entities.product.BuyFormDetail;
import com.bksoftware.sellingweb.entities.product.BuyFormHasProduct;
import com.bksoftware.sellingweb.service_impl.product.BuyFormService_Impl;
import com.bksoftware.sellingweb.service_impl.product.ProductService_Impl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/public/buy-form")
public class BuyFormController {

    private final BuyFormService_Impl buyFormService;
    private final ProductService_Impl productService;

    public BuyFormController(BuyFormService_Impl buyFormService, ProductService_Impl productService) {
        this.buyFormService = buyFormService;
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<String> addBuyForm(@RequestBody BuyForm buyForm) {
        if (buyForm.getProducts() == null || buyForm.getProducts().isEmpty())
            return new ResponseEntity<>("no product to buy", HttpStatus.BAD_REQUEST);
        if (!BuyFormDao.checkEmail(buyForm.getEmail()))
            return new ResponseEntity<>("email is not correct", HttpStatus.BAD_REQUEST);
        if (!BuyFormDao.checkPhone(String.valueOf(buyForm.getPhoneNumber())))
            return new ResponseEntity<>("phone number is not correct", HttpStatus.BAD_REQUEST);
        if (!BuyFormDao.checkName(buyForm.getName()))
            return new ResponseEntity<>("name is not correct", HttpStatus.BAD_REQUEST);
        if (buyFormService.saveBuyForm(buyForm))
            return new ResponseEntity<>("saved buy form", HttpStatus.OK);
        return new ResponseEntity<>("saved fail", HttpStatus.BAD_REQUEST);
    }



}
