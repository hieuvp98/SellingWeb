package com.bksoftware.sellingweb.controller;

import com.bksoftware.sellingweb.entities.*;
import com.bksoftware.sellingweb.repository.AppAdminRepository;
import com.bksoftware.sellingweb.service_impl.CategoryService_Impl;
import com.bksoftware.sellingweb.service_impl.PartnerService_Impl;
import com.bksoftware.sellingweb.service_impl.ProductDetailsService_Impl;
import com.bksoftware.sellingweb.service_impl.ProductService_Impl;
import org.apache.logging.log4j.util.PropertySource;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

@RestController
public class AdminController {
    private final AppAdminRepository appAdminRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ProductService_Impl productService;
    private final ProductDetailsService_Impl productDetailsService;
    private final CategoryService_Impl categoryService;
    private final PartnerService_Impl partnerService;


    public AdminController(AppAdminRepository appAdminRepository, BCryptPasswordEncoder bCryptPasswordEncoder, ProductService_Impl productService, ProductDetailsService_Impl productDetailsService, CategoryService_Impl categoryService, PartnerService_Impl partnerService) {
        this.appAdminRepository = appAdminRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.productService = productService;
        this.productDetailsService = productDetailsService;
        this.categoryService = categoryService;
        this.partnerService = partnerService;
    }

    // -------------------------------------ADMIN info------------------------------------
    @RolesAllowed("ADMIN")
    @GetMapping(value = "/api/v1/admin/info")
    public ResponseEntity<AppAdmin> getInfo(HttpServletRequest request) {
        String username = request.getUserPrincipal().getName();
        AppAdmin appAdmin = appAdminRepository.findByUsername(username);
        if (appAdmin == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } else return new ResponseEntity<>(appAdmin, HttpStatus.OK);
    }

    @RolesAllowed("ADMIN")
    @PostMapping(value = "/api/v1/admin/change-password", params = {"old", "new"})
    public ResponseEntity<String> changePassword(@RequestParam(value = "old") String oldPassword,
                                                 @RequestParam(value = "new") String newPassword, HttpServletRequest request) {
        String username = request.getUserPrincipal().getName();
        AppAdmin appAdmin = appAdminRepository.findByUsername(username);
        if (bCryptPasswordEncoder.matches(oldPassword, appAdmin.getPassword())) {
            appAdmin.setPassword(bCryptPasswordEncoder.encode(newPassword));
            appAdminRepository.save(appAdmin);
            return new ResponseEntity<>("change success", HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>("password is not correct", HttpStatus.BAD_REQUEST);
    }
    // -----------------------------------Category-----------------------------------------

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
    @PostMapping(value = "/api/v1/admin/mediumCategory", params = "bigCategory_id")
    public ResponseEntity<String> addMediumCategory(@RequestBody MediumCategory mediumCategory,
                                                    @RequestParam(value = "bigCategory_id") int id) {
        BigCategory bigCategory = categoryService.findBigCategoryById(id);
        mediumCategory.setBigCategory(bigCategory);
        mediumCategory.setStatus(true);
        if (categoryService.saveMediumCategory(mediumCategory))
            return new ResponseEntity<>("add success", HttpStatus.OK);
        else
            return new ResponseEntity<>("add fail", HttpStatus.BAD_REQUEST);

    }

    @RolesAllowed("ADMIN")
    @PostMapping(value = "/api/v1/admin/smallCategory", params = "mediumCategory_id")
    public ResponseEntity<String> addMediumCategory(@RequestBody SmallCategory smallCategory,
                                                    @RequestParam(value = "mediumCategory_id") int id) {
        MediumCategory mediumCategory = categoryService.findMediumCategoryById(id);
        smallCategory.setMediumCategory(mediumCategory);
        smallCategory.setStatus(true);
        if (categoryService.saveSmallCategory(smallCategory))
            return new ResponseEntity<>("add success", HttpStatus.OK);
        else
            return new ResponseEntity<>("add fail", HttpStatus.BAD_REQUEST);
    }

    // ---------------------- PARTNER---------------------------------------------------
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
    @PostMapping(value = "/api/v1/admin/product")
    public ResponseEntity<String> addProduct(@RequestBody Product product) {
        product.setStatus(true);
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
    @PostMapping(value = "/api/v1/admin/productDetails",params = "product_id")
    public ResponseEntity<String> addProductDetails(@RequestBody ProductDetails productDetails,
                                                    @RequestParam(value = "product_id")int id) {
        productDetails.setProduct(productService.findById(id));
        if (productDetailsService.saveProductDetails(productDetails))
            return new ResponseEntity<>("add productDetails success", HttpStatus.OK);
        else return new ResponseEntity<>("add productDetails fail", HttpStatus.BAD_REQUEST);
    }
}
