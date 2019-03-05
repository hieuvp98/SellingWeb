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
public class AdminController {
    private final AppAdminRepository appAdminRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ProductService_Impl productService;
    private final ProductDetailsService_Impl productDetailsService;
    private final CategoryService_Impl categoryService;
    private final PartnerService_Impl partnerService;
    private final SendMailService_Impl sendMailService;
    private final UserMail userMail;

    public AdminController(AppAdminRepository appAdminRepository,
                           BCryptPasswordEncoder bCryptPasswordEncoder,
                           ProductService_Impl productService,
                           ProductDetailsService_Impl productDetailsService,
                           CategoryService_Impl categoryService,
                           PartnerService_Impl partnerService,
                           SendMailService_Impl sendMailService,
                           UserMail user) {
        this.appAdminRepository = appAdminRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.productService = productService;
        this.productDetailsService = productDetailsService;
        this.categoryService = categoryService;
        this.partnerService = partnerService;
        this.sendMailService = sendMailService;
        this.userMail = user;
    }

    // -------------------------------------ADMIN info------------------------------------
    //get info
    @RolesAllowed("ADMIN")
    @GetMapping(value = "/api/v1/admin/info")
    public ResponseEntity<AppAdmin> getInfo(HttpServletRequest request) {
        String username = request.getUserPrincipal().getName();
        AppAdmin appAdmin = appAdminRepository.findByUsername(username);
        if (appAdmin == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } else return new ResponseEntity<>(appAdmin, HttpStatus.OK);
    }

    //change password
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

    // change info
    @RolesAllowed("ADMIN")
    @PutMapping(value = "/api/v1/admin/change-info")
    public ResponseEntity<String> changeInfo(@RequestBody AppAdmin appAdmin) {
        try {
            appAdminRepository.save(appAdmin);
            return new ResponseEntity<>("changed", HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>("change error: " + ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    //forget password
    @GetMapping(value = "/api/v1/public/forget-password", params = "email")
    public ResponseEntity<String> forgetPassword(@RequestParam(value = "email") String email) {
        AppAdmin appAdmin = appAdminRepository.findAll().get(0);
        if (email.equals(appAdmin.getEmail())) {
            userMail.setEmailAddress(email);
            int random = new Random(10000).nextInt();
            String content = " your new password is <b>"+random+"</b>.";
            appAdmin.setPassword(bCryptPasswordEncoder.encode(String.valueOf(random)));
            appAdminRepository.save(appAdmin);
            sendMailService.sendMail(userMail,"New password",content);
            return new ResponseEntity<>("ok",HttpStatus.OK);
        }
        return new ResponseEntity<>("email wrong",HttpStatus.BAD_REQUEST);
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
    @PostMapping(value = "/api/v1/admin/medium-category", params = "bigCategory_id")
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
    @PostMapping(value = "/api/v1/admin/small-category", params = "mediumCategory_id")
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
    @PostMapping(value = "/api/v1/admin/product-details", params = "product_id")
    public ResponseEntity<String> addProductDetails(@RequestBody ProductDetails productDetails,
                                                    @RequestParam(value = "product_id") int id) {
        productDetails.setProduct(productService.findById(id));
        if (productDetailsService.saveProductDetails(productDetails))
            return new ResponseEntity<>("add productDetails success", HttpStatus.OK);
        else return new ResponseEntity<>("add productDetails fail", HttpStatus.BAD_REQUEST);
    }
    //---------------------FEATURE-------------------------------------------------
    //add
    @RolesAllowed("ADMIN")
    @PostMapping(value ="/api/v1/admin/feature",params = "productDetails_id")
    public ResponseEntity<String> addFeature(@RequestBody Feature feature,
                                             @RequestParam(value = "productDetails_id")int id){
        ProductDetails productDetails = productDetailsService.findById(id);
        feature.setProductDetails(productDetails);
        if (productDetailsService.saveFeature(feature))
            return new ResponseEntity<>("add feature success",HttpStatus.OK);
        return new ResponseEntity<>("add feature fail",HttpStatus.BAD_REQUEST);
    }
    //update
    @RolesAllowed("ADMIN")
    @PutMapping(value ="/api/v1/admin/feature")
    public ResponseEntity<String> updateFeature(@RequestBody Feature feature){
        if (productDetailsService.saveFeature(feature))
            return new ResponseEntity<>("update feature success",HttpStatus.OK);
        return new ResponseEntity<>("update feature fail",HttpStatus.BAD_REQUEST);
    }
    //delete
    @RolesAllowed("ADMIN")
    @PutMapping(value ="/api/v1/admin/delete-feature")
    public ResponseEntity<String> deleteFeature(@RequestBody Feature feature){
        feature.setStatus(false);
        if (productDetailsService.saveFeature(feature))
            return new ResponseEntity<>("delete feature success",HttpStatus.OK);
        return new ResponseEntity<>("delete feature fail",HttpStatus.BAD_REQUEST);
    }
    //---------------------------productImage--------------------------
    @RolesAllowed("ADMIN")
    @PostMapping(value ="/api/v1/admin/product-image",params = "productDetails_id")
    public ResponseEntity<String> addFeature(@RequestBody ProductImage productImage,
                                             @RequestParam(value = "productDetails_id")int id){
        ProductDetails productDetails = productDetailsService.findById(id);
        productImage.setProductDetails(productDetails);
        if (productDetailsService.saveProductImage(productImage))
            return new ResponseEntity<>("add product image success",HttpStatus.OK);
        return new ResponseEntity<>("add product image fail",HttpStatus.BAD_REQUEST);
    }
}
