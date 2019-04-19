package com.bksoftware.sellingweb.controller.product;

import com.bksoftware.sellingweb.entities.product.Partner;
import com.bksoftware.sellingweb.entities.product.Product;
import com.bksoftware.sellingweb.service_impl.category.CategoryService_Impl;
import com.bksoftware.sellingweb.service_impl.product.PartnerService_Impl;
import com.bksoftware.sellingweb.service_impl.product.ProductService_Impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.Part;
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
        products.forEach(product -> partners.add(product.getPartner()));
        return new ResponseEntity<>(partners, HttpStatus.OK);
    }

    @GetMapping(value = "/find-all")
    public ResponseEntity<List<Partner>> showPartner(
            @RequestParam(name = "page", required = false, defaultValue = "1") int page,
            @RequestParam(name = "size", required = false, defaultValue = "10") int size
    ) {

        if (page < 1) page = 1;
        if (size < 0) size = 0;

        Pageable pageable = PageRequest.of(page - 1, size);
        List<Partner> lstPartner = partnerService.findAllPartner(pageable).getContent();
        return new ResponseEntity<>(lstPartner, HttpStatus.OK);
    }

    @GetMapping(value = "/find-by-id")
    public ResponseEntity<Partner> findPartnerById(@RequestParam("id") int id) {
        Partner partner = partnerService.findById(id);
        return new ResponseEntity<>(partner, HttpStatus.OK);
    }


    @GetMapping(value = "/all")
    public ResponseEntity<List<Partner>> allPartner() {
        List<Partner> lstPartner = partnerService.findAllPartnerPage();
        return new ResponseEntity<>(lstPartner, HttpStatus.OK);
    }

    @GetMapping(value = "/find-all/size")
    public ResponseEntity<Double> findPagePartner() {
        List<Partner> lstPartner = partnerService.findAllPartnerPage();
        return new ResponseEntity<>(Math.ceil(lstPartner.size() / 10) + 1, HttpStatus.OK);
    }

    @GetMapping(value = "/find-by-medium-category")
    public ResponseEntity<HashSet<Partner>> findByMediumCategory(@RequestParam("id-medium-category") int id) {
        HashSet<Partner> partners = new HashSet<>();
        List<Product> products = productService.findProductByMedium(id);
        for (Product p : products) {
            partners.add(p.getPartner());
        }
        return new ResponseEntity<>(partners, HttpStatus.OK);
    }

    @GetMapping(value = "/find-by-small-category")
    public ResponseEntity<HashSet<Partner>> findBySmallCategory(@RequestParam("id-small-category") int id) {
        HashSet<Partner> partners = new HashSet<>();
        List<Product> products = productService.findProductBySmall(id);
        for (Product p : products) {
            partners.add(p.getPartner());
        }
        return new ResponseEntity<>(partners, HttpStatus.OK);
    }


}
