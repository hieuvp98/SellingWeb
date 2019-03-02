package com.bksoftware.sellingweb.controller;

import com.bksoftware.sellingweb.entities.AppAdmin;
import com.bksoftware.sellingweb.repository.AppAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/api/admin")
public class AdminController {
    private final AppAdminRepository appAdminRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    public AdminController(AppAdminRepository appAdminRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.appAdminRepository = appAdminRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @GetMapping(value = "/info")
    public ResponseEntity<AppAdmin> getInfo() {
        AppAdmin appAdmin = appAdminRepository.findAll().get(0);
        if (appAdmin == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } else return new ResponseEntity<>(appAdmin, HttpStatus.OK);
    }

    @PostMapping(value = "/change-password",params = {"old","new"})
    public ResponseEntity<String> changePassword(@RequestParam(value = "old") String oldPassword,
                                                 @RequestParam(value = "new") String newPassword, HttpServletRequest request){
        String username = request.getUserPrincipal().getName();
        AppAdmin appAdmin = appAdminRepository.findByUsername(username);
        if (bCryptPasswordEncoder.matches(oldPassword,appAdmin.getPassword())){
            appAdmin.setPassword(bCryptPasswordEncoder.encode(newPassword));
            appAdminRepository.save(appAdmin);
            return new ResponseEntity<>("change success",HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>("password is not correct",HttpStatus.BAD_REQUEST);
    }
}
