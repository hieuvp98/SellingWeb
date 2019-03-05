package com.bksoftware.sellingweb.controller.admin;

import com.bksoftware.sellingweb.entities.product.Partner;
import com.bksoftware.sellingweb.entities.product.Product;
import com.bksoftware.sellingweb.service_impl.CategoryService_Impl;
import com.bksoftware.sellingweb.service_impl.PartnerService_Impl;
import com.bksoftware.sellingweb.service_impl.ProductService_Impl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping(value = "/api/v1/admin")
public class AdminProductController {
    private final ProductService_Impl productService;
    private final CategoryService_Impl categoryService;
    private final PartnerService_Impl partnerService;

    public AdminProductController(
            ProductService_Impl productService,
            CategoryService_Impl categoryService,
            PartnerService_Impl partnerService) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.partnerService = partnerService;
    }

    // ---------------------- PARTNER---------------------------------------------------
    //add
    @RolesAllowed("ADMIN")
    @PostMapping(value = "/partner")
    public ResponseEntity<String> addPartner(@RequestBody Partner partner) {
        partner.setStatus(true);
        if (partnerService.savePartner(partner))
            return new ResponseEntity<>("add success", HttpStatus.OK);
        else
            return new ResponseEntity<>("add fail", HttpStatus.BAD_REQUEST);
    }

    //update
    @RolesAllowed("ADMIN")
    @PutMapping(value = "/partner")
    public ResponseEntity<String> updatePartner(@RequestBody Partner partner) {
        if (partnerService.savePartner(partner))
            return new ResponseEntity<>("update success", HttpStatus.OK);
        else
            return new ResponseEntity<>("update fail", HttpStatus.BAD_REQUEST);
    }

    //delete
    @RolesAllowed("ADMIN")
    @PutMapping(value = "/delete-partner")
    public ResponseEntity<String> deletePartner(@RequestBody Partner partner) {
        if (partnerService.deletePartner(partner))
            return new ResponseEntity<>("delete success", HttpStatus.OK);
        else
            return new ResponseEntity<>("delete fail", HttpStatus.BAD_REQUEST);
    }


    // -------------------------PRODUCT------------------------------------------------
    //add
    @RolesAllowed("ADMIN")
    @PostMapping(value = "/product", params = {"small-category-id", "partner-id"})
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
    @PutMapping(value = "/product")
    public ResponseEntity<String> updateProduct(@RequestBody Product product) {
        if (productService.saveProduct(product))
            return new ResponseEntity<>("update product success", HttpStatus.OK);
        else return new ResponseEntity<>("update product fail", HttpStatus.BAD_REQUEST);
    }

    //delete
    @RolesAllowed("ADMIN")
    @PutMapping(value = "/delete-product")
    public ResponseEntity<String> deleteProduct(@RequestBody Product product) {
        if (productService.deleteProduct(product))
            return new ResponseEntity<>("delete product success", HttpStatus.OK);
        else return new ResponseEntity<>("delete product fail", HttpStatus.BAD_REQUEST);
    }

}
