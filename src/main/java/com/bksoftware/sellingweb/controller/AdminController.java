package com.bksoftware.sellingweb.controller;

import com.bksoftware.sellingweb.entities.AppAdmin;
import com.bksoftware.sellingweb.repository.AppAdminRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/admin")
public class AdminController {
    private final AppAdminRepository appAdminRepository;

    public AdminController(AppAdminRepository appAdminRepository) {
        this.appAdminRepository = appAdminRepository;
    }
    @GetMapping(value = "/info")
    public ResponseEntity<AppAdmin> getInfo(){
        AppAdmin appAdmin = appAdminRepository.findAll().get(0);
        if (appAdmin == null){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        else return new ResponseEntity<>(appAdmin,HttpStatus.OK);
    }
}
