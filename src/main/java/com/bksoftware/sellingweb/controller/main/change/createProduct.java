package com.bksoftware.sellingweb.controller.main.change;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/admin")
public class createProduct {

    @RequestMapping(value = { "/create-product" }, method = RequestMethod.GET)
    public String createProductPage() { return "createProduct"; }

    //=========================Category=================================
    @GetMapping("/create-big-category")
    public String createBigCategoryPage() {
        return "createBigCategory";
    }

    @GetMapping("/create-medium-category")
    public String createMediumCategoryPage() {
        return "createMediumCategory";
    }

    @GetMapping("/create-small-category")
    public String createSmallCategoryPage() {
        return "createSmallCategory";
    }

}
