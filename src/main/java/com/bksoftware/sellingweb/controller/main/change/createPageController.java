package com.bksoftware.sellingweb.controller.main.change;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class createPageController {
    //=========================Category=================================
    @GetMapping("/create-category")
    public String createCategoryPage() {
        return "createCategory";
    }

    @GetMapping("/update-category/{id}")
    public String updateCategoryPage(@PathVariable int id) {
        return "updateCategory";
    }


    //=========================Category=================================

    @GetMapping("/create-product")
    public String createProductPage() {
        return "createProduct";
    }

    @GetMapping("/create-details-product")
    public String createDetailsProductPage() {
        return "createDetailsProduct";
    }
}
