package com.bksoftware.sellingweb.controller.main.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/admin")
public class homeAdminController {

    @RequestMapping(value = { "/", "/home" }, method = RequestMethod.GET)
    public String homePage() {
        return "homeAdmin";
    }

    @RequestMapping(value = { "/product" }, method = RequestMethod.GET)
    public String ProductPage() { return "product"; }

    @RequestMapping(value = { "/login" }, method = RequestMethod.GET)
    public String pageLogin() {
        return "login";
    }

}
