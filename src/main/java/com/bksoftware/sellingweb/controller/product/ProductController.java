package com.bksoftware.sellingweb.controller.product;

import com.bksoftware.sellingweb.entities.product.*;
import com.bksoftware.sellingweb.service_impl.product.FeedbackService_Impl;
import com.bksoftware.sellingweb.service_impl.product.PartnerService_Impl;
import com.bksoftware.sellingweb.service_impl.product.ProductDetailsService_Impl;
import com.bksoftware.sellingweb.service_impl.product.ProductService_Impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;
import java.util.stream.Collectors;


@Controller
@RequestMapping("api/v1/public/products")
public class ProductController {

    @Autowired
    ProductService_Impl productService;

    @Autowired
    ProductDetailsService_Impl productDetailsService_imp;

    @Autowired
    PartnerService_Impl partnerService_imp;

    @GetMapping
    public ResponseEntity<List<Product>> findProductByName(
            @RequestParam("name") String name,
            @RequestParam(name = "page", required = false, defaultValue = "1") int page,
            @RequestParam(name = "size", required = false, defaultValue = "20") int size,
            @RequestParam(name = "sort", required = false, defaultValue = "DESC") String sort
    ) {
        Sort sortable = productService.sortData(sort);
        if (page < 1) page = 1;
        if (size < 0) size = 0;
        //Pageable là 1 interface, để tạo nó ta sử dụng PageRequest
        Pageable pageable = PageRequest.of(page - 1, size, sortable);
        List<Product> productsByName = productService.findProductByName(name, pageable).getContent();
        productsByName.stream()
                .filter(p -> (p.isStatus() == true))
                .collect(Collectors.toList());
        return new ResponseEntity<>(productsByName, HttpStatus.OK);
    }
    @GetMapping("/featureProductById")
    public ResponseEntity<List<Feature>> showFeatureById(@RequestParam("idProduct") int idProduct){
        List<Feature> lstFeature = productDetailsService_imp.showFeatureById(idProduct);
        return new ResponseEntity<>(lstFeature,HttpStatus.OK);
    }

    @GetMapping(value = "/bySmallCategory")
    public  ResponseEntity<List<Product>> showProduct(
            @RequestParam(name = "id") int id,
            @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(name = "size", required = false, defaultValue = "5") Integer size,
            @RequestParam(name = "type", required = false, defaultValue = "ASC") String type,
            @RequestParam(name = "field", required = false, defaultValue = "name") String field) {
        Sort sortable = productService.sortDataProduct(type,field);
        Pageable pageable = PageRequest.of(page, size, sortable);
        Page<Product> lstProduct = productService.showProduct(id,pageable);
        return new ResponseEntity<>(lstProduct.getContent(),HttpStatus.OK);
    }
    @GetMapping(value = "/byMediumCategory")
    public  ResponseEntity<List<Product>> showProductMedium(
            @RequestParam(name = "idMediumCategory") int id,
            @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(name = "size", required = false, defaultValue = "5") Integer size,
            @RequestParam(name = "type", required = false, defaultValue = "ASC") String type,
            @RequestParam(name = "field", required = false, defaultValue = "name") String field) {
        Sort sortable = productService.sortDataProduct(type,field);
        Pageable pageable = PageRequest.of(page, size, sortable);
        Page<Product> lstProductMedium = productService.showProductByMedium(id,pageable);
        return new ResponseEntity<>(lstProductMedium.getContent(),HttpStatus.OK);
    }
    @GetMapping(value = "/byBigCategory")
    public  ResponseEntity<List<Product>> showProductByBig(
            @RequestParam(name = "id") int id,
            @RequestParam(name = "page", required = false ,defaultValue = "0") Integer page,
            @RequestParam(name = "size", required = false, defaultValue = "5") Integer size,
            @RequestParam(name = "type", required = false, defaultValue = "ASC") String type,
            @RequestParam(name = "field", required = false, defaultValue = "name") String field) {
        Sort sortable = productService.sortDataProduct(type,field);
        Pageable pageable = PageRequest.of(page, size, sortable);
        Page<Product> lstProductBig = productService.showProductByBig(id,pageable);
        return new ResponseEntity<>(lstProductBig.getContent(),HttpStatus.OK);
    }

    @GetMapping(value = "/byBigCategoryBranch")
    public  ResponseEntity<List<Product>> showProductByBigBranch(
            @RequestParam(name = "idBigCategory") int id,
            @RequestParam(name = "branch") int branch,
            @RequestParam(name = "page", required = false ,defaultValue = "0") Integer page,
            @RequestParam(name = "size", required = false, defaultValue = "5") Integer size,
            @RequestParam(name = "type", required = false, defaultValue = "ASC") String type,
            @RequestParam(name = "field", required = false, defaultValue = "name") String field) {
        Sort sortable = productService.sortDataProduct(type,field);
        Pageable pageable = PageRequest.of(page, size, sortable);
        Page<Product> lstProductBig = productService.showProductByBigBranch(id,branch,pageable);
        return new ResponseEntity<>(lstProductBig.getContent(),HttpStatus.OK);
    }

    @GetMapping(value = "/bySmallCategoryBranch")
    public  ResponseEntity<List<Product>> showProductBySmallBranch(
            @RequestParam(name = "idSmallCategory") int id,
            @RequestParam(name = "branch") int branch,
            @RequestParam(name = "page", required = false ,defaultValue = "0") Integer page,
            @RequestParam(name = "size", required = false, defaultValue = "5") Integer size,
            @RequestParam(name = "type", required = false, defaultValue = "ASC") String type,
            @RequestParam(name = "field", required = false, defaultValue = "name") String field) {
        Sort sortable = productService.sortDataProduct(type,field);
        Pageable pageable = PageRequest.of(page, size, sortable);
        Page<Product> lstProductSmall = productService.showProductBySmallBranch(id,branch,pageable);
        return new ResponseEntity<>(lstProductSmall.getContent(),HttpStatus.OK);
    }
    @GetMapping(value = "/byMediumCategoryBranch")
    public  ResponseEntity<List<Product>> showProductByMediumBranch(
            @RequestParam(name = "idMediumCategory") int id,
            @RequestParam(name = "branch") int branch,
            @RequestParam(name = "page", required = false ,defaultValue = "0") Integer page,
            @RequestParam(name = "size", required = false, defaultValue = "5") Integer size,
            @RequestParam(name = "type", required = false, defaultValue = "ASC") String type,
            @RequestParam(name = "field", required = false, defaultValue = "name") String field) {
        Sort sortable = productService.sortDataProduct(type,field);
        Pageable pageable = PageRequest.of(page, size, sortable);
        Page<Product> lstProductMedium = productService.showProductByMediumBranch(id,branch,pageable);
        return new ResponseEntity<>(lstProductMedium.getContent(),HttpStatus.OK);
    }
    @GetMapping(value = "/findProductByPrice")
    public  ResponseEntity<List<Product>> findProductByPrice(
            @RequestParam(name = "id") int id,
            @RequestParam(name = "low") int low,
            @RequestParam(name = "high") int high,
            @RequestParam(name = "page", required = false ,defaultValue = "0") Integer page,
            @RequestParam(name = "size", required = false, defaultValue = "5") Integer size,
            @RequestParam(name = "type", required = false, defaultValue = "ASC") String type,
            @RequestParam(name = "field", required = false, defaultValue = "name") String field) {
        Sort sortable = productService.sortDataProduct(type,field);
        Pageable pageable = PageRequest.of(page, size, sortable);
        Page<Product> lstProductPrice = productService.findProductByPrice(id,low,high,pageable);
        return new ResponseEntity<>(lstProductPrice.getContent(),HttpStatus.OK);
    }
    @GetMapping(value = "/findProductByPriceBranch")
    public  ResponseEntity<List<Product>> findProductByPriceBranch(
            @RequestParam(name = "id") int id,
            @RequestParam(name = "low") int low,
            @RequestParam(name = "high") int high,
            @RequestParam(name = "branch") int branch,
            @RequestParam(name = "page", required = false ,defaultValue = "0") Integer page,
            @RequestParam(name = "size", required = false, defaultValue = "5") Integer size,
            @RequestParam(name = "type", required = false, defaultValue = "ASC") String type,
            @RequestParam(name = "field", required = false, defaultValue = "name") String field) {
        Sort sortable = productService.sortDataProduct(type,field);
        Pageable pageable = PageRequest.of(page, size, sortable);
        Page<Product> lstProductPrice = productService.findProductByPriceBranch(id,low,high,branch,pageable);
        return new ResponseEntity<>(lstProductPrice.getContent(),HttpStatus.OK);
    }

    @GetMapping(value = "/showProductSale")
    public  ResponseEntity<List<Product>> showProductSale(
            @RequestParam(name = "page", required = false ,defaultValue = "0") Integer page,
            @RequestParam(name = "size", required = false, defaultValue = "5") Integer size,
            @RequestParam(name = "type", required = false, defaultValue = "ASC") String type,
            @RequestParam(name = "field", required = false, defaultValue = "name") String field) {
        Sort sortable = productService.sortDataProduct(type,field);
        Pageable pageable = PageRequest.of(page, size, sortable);
        Page<Product> lstProductSale = productService.showProductSale(pageable);
        return new ResponseEntity<>(lstProductSale.getContent(),HttpStatus.OK);
    }
    @GetMapping("/productDetails")
    public ResponseEntity<ProductDetails> showProductDetails(@RequestParam("idProduct") int idProduct){
        ProductDetails productDetails = productDetailsService_imp.showProductDetails(idProduct);
        return new ResponseEntity<>(productDetails,HttpStatus.OK);
    }

    @GetMapping("/productTest")
    public ResponseEntity<TreeMap<Integer,Partner>> testProduct(){
        TreeMap<Integer,Partner> product = productService.test();
        return new ResponseEntity<>(product,HttpStatus.OK);
    }
    @GetMapping("/findProductById")
    public ResponseEntity<Product> findProductById(@RequestParam("id") int id){
        Product product = productService.findById(id);
        return new ResponseEntity<>(product,HttpStatus.OK);
    }

}


