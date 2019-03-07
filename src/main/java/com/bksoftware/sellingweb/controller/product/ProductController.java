package com.bksoftware.sellingweb.controller.product;

import com.bksoftware.sellingweb.entities.product.Product;
import com.bksoftware.sellingweb.service_impl.product.FeedbackService_Impl;
import com.bksoftware.sellingweb.service_impl.product.ProductService_Impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;


@Controller
@RequestMapping("api/v1/public/products")
public class ProductController {

    @Autowired
    ProductService_Impl productService;

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

}


