package com.bksoftware.sellingweb.controller.product;

import com.bksoftware.sellingweb.entities.product.Partner;
import com.bksoftware.sellingweb.entities.product.Product;
import com.bksoftware.sellingweb.service_impl.category.CategoryService_Impl;
import com.bksoftware.sellingweb.service_impl.product.PartnerService_Impl;
import com.bksoftware.sellingweb.service_impl.product.ProductService_Impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/public/partner")
public class PartnerController {

    @Autowired
    private PartnerService_Impl partnerService;
    @Autowired
    private CategoryService_Impl categoryService;
    @Autowired
    private ProductService_Impl productService;

    @GetMapping(value = "/find-by-big-category")
    public ResponseEntity<HashSet<Partner>> findByBigCategory(@RequestParam(name = "big-category-id") int id) {
        HashSet<Partner> partners = new HashSet<>();
        List<Product> products = productService.findAllProductByBigCategory(categoryService.findBigCategoryById(id));
        products.forEach( product -> partners.add(product.getPartner()));
        return new ResponseEntity<>(partners, HttpStatus.OK);
    }

    @GetMapping(value = "/showPartner")
    public ResponseEntity<List<Partner>> showPartner(){
        List<Partner> lstPartner =  partnerService.show();
        return new ResponseEntity<>(lstPartner,HttpStatus.OK);
    }
    @GetMapping(value = "/find-by-medium-category")
    public ResponseEntity<HashSet<Partner>> findByMediumCategory(@RequestParam("id-medium-category") int id){
        HashSet<Partner> partners = new HashSet<>();
        List<Product> products = productService.findProductByMedium(id);
        for (Product p : products){
            partners.add(p.getPartner());
        }
        return new ResponseEntity<>(partners,HttpStatus.OK);
    }

    @GetMapping(value = "/find-by-small-category")
    public ResponseEntity<HashSet<Partner>> findBySmallCategory(@RequestParam("id-small-category") int id){
        HashSet<Partner> partners = new HashSet<>();
        List<Product> products = productService.findProductBySmall(id);
        for (Product p : products){
            partners.add(p.getPartner());
        }
        return new ResponseEntity<>(partners,HttpStatus.OK);
    }



}
