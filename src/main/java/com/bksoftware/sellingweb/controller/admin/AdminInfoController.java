package com.bksoftware.sellingweb.controller.admin;

import com.bksoftware.sellingweb.entities.AppAdmin;
import com.bksoftware.sellingweb.entities.UserMail;
import com.bksoftware.sellingweb.repository.AppAdminRepository;
import com.bksoftware.sellingweb.service_impl.SendMailService_Impl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import java.util.Random;

@RestController
@RequestMapping(value = "/api/v1")
public class AdminInfoController {
    private final AppAdminRepository appAdminRepository;
    private final UserMail userMail;
    private final SendMailService_Impl sendMailService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public AdminInfoController(AppAdminRepository appAdminRepository, UserMail userMail, SendMailService_Impl sendMailService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.appAdminRepository = appAdminRepository;
        this.userMail = userMail;
        this.sendMailService = sendMailService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    // -------------------------------------ADMIN info------------------------------------
    //get info
    @RolesAllowed("ADMIN")
    @GetMapping(value = "/admin/info")
    public ResponseEntity<AppAdmin> getInfo() {
        AppAdmin appAdmin = appAdminRepository.findAll().get(0);
        if (appAdmin == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } else return new ResponseEntity<>(appAdmin, HttpStatus.OK);
    }

    //change password
    @RolesAllowed("ADMIN")
    @PostMapping(value = "/admin/change-password", params = {"old", "new"})
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

    // change info
    @RolesAllowed("ADMIN")
    @PutMapping(value = "/admin/change-info")
    public ResponseEntity<String> changeInfo(@RequestBody AppAdmin appAdmin) {
        try {
            appAdminRepository.save(appAdmin);
            return new ResponseEntity<>("changed", HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>("change error: " + ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    //forget password
    @PostMapping(value = "/public/forget-password", params = "email")
    public ResponseEntity<String> forgetPassword(@RequestParam(value = "email") String email) {
        AppAdmin appAdmin = appAdminRepository.findAll().get(0);
        if (email.equals(appAdmin.getEmail())) {
            userMail.setEmailAddress(email);
            int random = new Random().nextInt((10000 - 1000) + 1) + 1000;
            String content = "Your new password is " + random + ".";
            appAdmin.setPassword(bCryptPasswordEncoder.encode(String.valueOf(random)));
            appAdminRepository.save(appAdmin);
            sendMailService.sendMail(userMail, "New password", content);
            return new ResponseEntity<>("ok", HttpStatus.OK);
        }
        return new ResponseEntity<>("email is wrong", HttpStatus.BAD_REQUEST);
    }
}
