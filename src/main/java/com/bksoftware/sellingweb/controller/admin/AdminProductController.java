package com.bksoftware.sellingweb.controller.admin;

import com.bksoftware.sellingweb.entities.product.Partner;
import com.bksoftware.sellingweb.entities.product.Product;
import com.bksoftware.sellingweb.service_impl.category.CategoryService_Impl;
import com.bksoftware.sellingweb.service_impl.product.PartnerService_Impl;
import com.bksoftware.sellingweb.service_impl.product.ProductService_Impl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.time.LocalDate;

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
    public ResponseEntity<Object> addPartner(@RequestBody Partner partner) {
        partner.setStatus(true);
        if (partnerService.savePartner(partner))
            return new ResponseEntity<>(partner, HttpStatus.OK);
        else
            return new ResponseEntity<>("add fail", HttpStatus.BAD_REQUEST);
    }

    //update
    @RolesAllowed("ADMIN")
    @PutMapping(value = "/partner")
    public ResponseEntity<Object> updatePartner(@RequestBody Partner partner) {
        if (partnerService.savePartner(partner))
            return new ResponseEntity<>(partner, HttpStatus.OK);
        else
            return new ResponseEntity<>("update fail", HttpStatus.BAD_REQUEST);
    }

    //delete
    @RolesAllowed("ADMIN")
    @PutMapping(value = "/delete-partner")
    public ResponseEntity<String> deletePartner(@RequestParam("id") int id) {
        Partner partner = partnerService.findById(id);
        if (partnerService.deletePartner(partner))
            return new ResponseEntity<>("delete success", HttpStatus.OK);
        else
            return new ResponseEntity<>("delete fail", HttpStatus.BAD_REQUEST);
    }


    // -------------------------PRODUCT------------------------------------------------
    //add
    @RolesAllowed("ADMIN")
    @PostMapping(value = "/product", params = {"small-category-id", "partner-id"})
    public ResponseEntity<Object> addProduct(@RequestBody Product product,
                                             @RequestParam(name = "small-category-id") int smallCategoryId,
                                             @RequestParam(name = "partner-id") int partnerId) {
        product.setStatus(true);
        product.setView(0);
        product.setInitDate(LocalDate.now());
        product.setSmallCategory(categoryService.findSmallCategoryById(smallCategoryId));
        product.setPartner(partnerService.findById(partnerId));
        if (productService.saveProduct(product))
            return new ResponseEntity<>(product, HttpStatus.OK);
        else return new ResponseEntity<>("add product fail", HttpStatus.BAD_REQUEST);
    }

    //update
    @RolesAllowed("ADMIN")
    @PutMapping(value = "/product")
    public ResponseEntity<Object> updateProduct(@RequestBody Product product) {
        if (productService.saveProduct(product))
            return new ResponseEntity<>(product, HttpStatus.OK);
        else return new ResponseEntity<>("update product fail", HttpStatus.BAD_REQUEST);
    }

    //delete
    @RolesAllowed("ADMIN")
    @PutMapping(value = "/delete-product")
    public ResponseEntity<String> deleteProduct(@RequestParam("id") int idProduct) {
        Product product = productService.findById(idProduct);
        System.out.println("ID - " + product.getId());
        if (productService.deleteProduct(product))
            return new ResponseEntity<>("delete product success", HttpStatus.OK);
        else return new ResponseEntity<>("delete product fail", HttpStatus.BAD_REQUEST);
    }

}
