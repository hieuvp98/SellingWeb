package com.bksoftware.sellingweb.controller;

import com.bksoftware.sellingweb.entities.*;
import com.bksoftware.sellingweb.entities.category.BigCategory;
import com.bksoftware.sellingweb.entities.category.MediumCategory;
import com.bksoftware.sellingweb.entities.category.SmallCategory;
import com.bksoftware.sellingweb.entities.product.*;
import com.bksoftware.sellingweb.repository.AppAdminRepository;
import com.bksoftware.sellingweb.service_impl.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import java.util.Random;

@RestController
public class AdminProductController {
    private final ProductService_Impl productService;
    private final ProductDetailsService_Impl productDetailsService;
    private final CategoryService_Impl categoryService;
    private final PartnerService_Impl partnerService;

    public AdminProductController(
            ProductService_Impl productService,
            ProductDetailsService_Impl productDetailsService,
            CategoryService_Impl categoryService,
            PartnerService_Impl partnerService) {
        this.productService = productService;
        this.productDetailsService = productDetailsService;
        this.categoryService = categoryService;
        this.partnerService = partnerService;
    }


    // -----------------------------------Category-----------------------------------------
    //add
    @RolesAllowed("ADMIN")
    @PostMapping(value = "/api/v1/admin/big-category")
    public ResponseEntity<String> addBigCategory(@RequestBody BigCategory bigCategory) {
        bigCategory.setStatus(true);
        if (categoryService.saveBigCategory(bigCategory))
            return new ResponseEntity<>("add success", HttpStatus.OK);
        else
            return new ResponseEntity<>("add fails", HttpStatus.BAD_REQUEST);
    }

    @RolesAllowed("ADMIN")
    @PostMapping(value = "/api/v1/admin/medium-category", params = "big-category-id")
    public ResponseEntity<String> addMediumCategory(@RequestBody MediumCategory mediumCategory,
                                                    @RequestParam(value = "big-category-id") int id) {
        BigCategory bigCategory = categoryService.findBigCategoryById(id);
        mediumCategory.setBigCategory(bigCategory);
        mediumCategory.setStatus(true);
        if (categoryService.saveMediumCategory(mediumCategory))
            return new ResponseEntity<>("add success", HttpStatus.OK);
        else
            return new ResponseEntity<>("add fail", HttpStatus.BAD_REQUEST);

    }

    @RolesAllowed("ADMIN")
    @PostMapping(value = "/api/v1/admin/small-category", params = "medium-category-id")
    public ResponseEntity<String> addMediumCategory(@RequestBody SmallCategory smallCategory,
                                                    @RequestParam(value = "medium-category-id") int id) {
        MediumCategory mediumCategory = categoryService.findMediumCategoryById(id);
        smallCategory.setMediumCategory(mediumCategory);
        smallCategory.setStatus(true);
        if (categoryService.saveSmallCategory(smallCategory))
            return new ResponseEntity<>("add success", HttpStatus.OK);
        else
            return new ResponseEntity<>("add fail", HttpStatus.BAD_REQUEST);
    }
    //delete


    // ---------------------- PARTNER---------------------------------------------------
    //add
    @RolesAllowed("ADMIN")
    @PostMapping(value = "/api/v1/admin/partner")
    public ResponseEntity<String> addPartner(@RequestBody Partner partner) {
        partner.setStatus(true);
        if (partnerService.savePartner(partner))
            return new ResponseEntity<>("add success", HttpStatus.OK);
        else
            return new ResponseEntity<>("add fail", HttpStatus.BAD_REQUEST);


    }

    // -------------------------PRODUCT------------------------------------------------
    //add
    @RolesAllowed("ADMIN")
    @PostMapping(value = "/api/v1/admin/product", params = {"small-category-id", "partner-id"})
    public ResponseEntity<String> addProduct(@RequestBody Product product,
                                             @RequestParam(name = "small-category-id") int smallCategoryId,
                                             @RequestParam(name = "partner-id") int partnerId) {
        product.setStatus(true);
        product.setSmallCategory(categoryService.findSmallCategoryById(smallCategoryId));
        product.setPartner(partnerService.findById(partnerId));
        if (productService.saveProduct(product))
            return new ResponseEntity<>("add product success", HttpStatus.OK);
        else return new ResponseEntity<>("add product fail", HttpStatus.BAD_REQUEST);
    }

    //update
    @RolesAllowed("ADMIN")
    @PutMapping(value = "/api/v1/admin/product")
    public ResponseEntity<String> updateProduct(@RequestBody Product product) {
        if (productService.saveProduct(product))
            return new ResponseEntity<>("update product success", HttpStatus.OK);
        else return new ResponseEntity<>("update product fail", HttpStatus.BAD_REQUEST);
    }

    //delete
    @RolesAllowed("ADMIN")
    @PutMapping(value = "/api/v1/admin/delete-product")
    public ResponseEntity<String> deleteProduct(@RequestBody Product product) {
        if (productService.deleteProduct(product))
            return new ResponseEntity<>("delete product success", HttpStatus.OK);
        else return new ResponseEntity<>("delete product fail", HttpStatus.BAD_REQUEST);
    }

    //---------------------PRODUCT DETAILS----------------------------------------
    //add
    @RolesAllowed("ADMIN")
    @PostMapping(value = "/api/v1/admin/product-details", params = "product-id")
    public ResponseEntity<String> addProductDetails(@RequestBody ProductDetails productDetails,
                                                    @RequestParam(value = "product-id") int id) {
        productDetails.setProductStatus(true);
        productDetails.setStatus(true);
        productDetails.setProduct(productService.findById(id));
        if (productDetailsService.saveProductDetails(productDetails))
            return new ResponseEntity<>("add productDetails success", HttpStatus.OK);
        else return new ResponseEntity<>("add productDetails fail", HttpStatus.BAD_REQUEST);
    }

    //---------------------FEATURE-------------------------------------------------
    //add
    @RolesAllowed("ADMIN")
    @PostMapping(value = "/api/v1/admin/feature", params = "product-details-id")
    public ResponseEntity<String> addFeature(@RequestBody Feature feature,
                                             @RequestParam(value = "product-details-id") int id) {
        ProductDetails productDetails = productDetailsService.findById(id);
        feature.setProductDetails(productDetails);
        feature.setStatus(true);
        if (productDetailsService.saveFeature(feature))
            return new ResponseEntity<>("add feature success", HttpStatus.OK);
        return new ResponseEntity<>("add feature fail", HttpStatus.BAD_REQUEST);
    }

    //update
    @RolesAllowed("ADMIN")
    @PutMapping(value = "/api/v1/admin/feature")
    public ResponseEntity<String> updateFeature(@RequestBody Feature feature) {
        if (productDetailsService.saveFeature(feature))
            return new ResponseEntity<>("update feature success", HttpStatus.OK);
        return new ResponseEntity<>("update feature fail", HttpStatus.BAD_REQUEST);
    }

    //delete
    @RolesAllowed("ADMIN")
    @PutMapping(value = "/api/v1/admin/delete-feature")
    public ResponseEntity<String> deleteFeature(@RequestBody Feature feature) {
        feature.setStatus(false);
        if (productDetailsService.saveFeature(feature))
            return new ResponseEntity<>("delete feature success", HttpStatus.OK);
        return new ResponseEntity<>("delete feature fail", HttpStatus.BAD_REQUEST);
    }

    //---------------------------productImage--------------------------
    @RolesAllowed("ADMIN")
    @PostMapping(value = "/api/v1/admin/product-image", params = "product-details-id")
    public ResponseEntity<String> addFeature(@RequestBody ProductImage productImage,
                                             @RequestParam(value = "product-details-id") int id) {
        ProductDetails productDetails = productDetailsService.findById(id);
        productImage.setProductDetails(productDetails);
        productImage.setStatus(true);
        if (productDetailsService.saveProductImage(productImage))
            return new ResponseEntity<>("add product image success", HttpStatus.OK);
        return new ResponseEntity<>("add product image fail", HttpStatus.BAD_REQUEST);
    }
}
