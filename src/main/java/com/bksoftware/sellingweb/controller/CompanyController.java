package com.bksoftware.sellingweb.controller;

import com.bksoftware.sellingweb.entities.Company;
import com.bksoftware.sellingweb.service_impl.CompanyService_Impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/company")
public class CompanyController {

    @Autowired
    CompanyService_Impl companyService;

    @GetMapping
    public ResponseEntity<List<Company>> findAllCompanies() {

        List<Company> companys = companyService.findAllcompanys();
        if (companys == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(companys, HttpStatus.OK);
    }
}
