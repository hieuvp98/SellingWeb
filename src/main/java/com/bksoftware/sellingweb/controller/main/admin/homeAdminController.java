package com.bksoftware.sellingweb.controller.main.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/admin")
public class homeAdminController {

    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    public String pageLogin() {
        return "login";
    }

    @RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
    public String homePage() {
        return "homeAdmin";
    }

    @RequestMapping(value = {"/product"}, method = RequestMethod.GET)
    public String ProductPage() {
        return "product";
    }

    @GetMapping("/details-product")
    public String detailsProductPage() {
        return "detailsProduct";
    }



    //=========================Category=================================
    @GetMapping("/big-category")
    public String bigCategoryPage() {
        return "bigCategory";
    }

    @GetMapping("/medium-category")
    public String mediumCategoryPage() {
        return "mediumCategory";
    }

    @GetMapping("/small-category")
    public String smallCategoryPage() {
        return "smallCategory";
    }

    //=========================Category=================================
    @GetMapping("/partner")
    public String partnerPage() {
        return "partner";
    }

}
