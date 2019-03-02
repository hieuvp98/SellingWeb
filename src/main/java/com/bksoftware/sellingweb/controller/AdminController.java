package com.bksoftware.sellingweb.controller;

import com.bksoftware.sellingweb.entities.AppAdmin;
import com.bksoftware.sellingweb.repository.AppAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;

@RestController
public class AdminController {
    private final AppAdminRepository appAdminRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public AdminController(AppAdminRepository appAdminRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.appAdminRepository = appAdminRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @RolesAllowed("ADMIN")
    @GetMapping(value = "/api/v1/admin/info")
    public ResponseEntity<AppAdmin> getInfo(HttpServletRequest request) {
        String username = request.getUserPrincipal().getName();
        AppAdmin appAdmin = appAdminRepository.findByUsername(username);
        if (appAdmin == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } else return new ResponseEntity<>(appAdmin, HttpStatus.OK);
    }

    @RolesAllowed("ADMIN")
    @PostMapping(value = "/api/v1/admin/change-password", params = {"old", "new"})
    public ResponseEntity<String> changePassword(@RequestParam(value = "old") String oldPassword,
                                                 @RequestParam(value = "new") String newPassword, HttpServletRequest request) {
        String username = request.getUserPrincipal().getName();
        AppAdmin appAdmin = appAdminRepository.findByUsername(username);
        if (bCryptPasswordEncoder.matches(oldPassword, appAdmin.getPassword())) {
            appAdmin.setPassword(bCryptPasswordEncoder.encode(newPassword));
            appAdminRepository.save(appAdmin);
            return new ResponseEntity<>("change success", HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>("password is not correct", HttpStatus.BAD_REQUEST);
    }
}
