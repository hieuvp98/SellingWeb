package com.bksoftware.sellingweb.controller.main.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/login")
public class LoginAdminController {

    @GetMapping
    public String loginPage(){
        return "login";
    }
}
