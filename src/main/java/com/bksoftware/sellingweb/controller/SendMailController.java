package com.bksoftware.sellingweb.controller;

import com.bksoftware.sellingweb.entities.UserMail;
import com.bksoftware.sellingweb.service_impl.SendMailService_Impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/public/send-email")
public class SendMailController {

    @Autowired
    SendMailService_Impl sendMailService;


    @Autowired
    private UserMail user;

    @GetMapping()
    public ResponseEntity<Object> sendEmail(
            @RequestParam("email") String email,
            @RequestParam("header") String header,
            @RequestParam("content") String content
    ) {
        user.setEmailAddress(email);
        boolean result = sendMailService.sendMail(user, header, content);
        if (result == true)
            return new ResponseEntity<>("Congratulations! Your mail has been send to the user.", HttpStatus.OK);
        return new ResponseEntity<>("send email fail", HttpStatus.FAILED_DEPENDENCY);
    }
}
