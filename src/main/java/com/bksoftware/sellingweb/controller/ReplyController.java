package com.bksoftware.sellingweb.controller;


import com.bksoftware.sellingweb.entities.product.Reply;
import com.bksoftware.sellingweb.service_impl.RepLyService_Impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("api/v1/public/replies")
public class ReplyController {

    @Autowired
    RepLyService_Impl repLyService;

    @GetMapping
    public ResponseEntity<List<Reply>> findAllReplies() {

        List<Reply> replies = repLyService.findAllReplies();

        if (replies == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(replies, HttpStatus.OK);

    }


    @PostMapping
    public ResponseEntity<Object> saveReply(@RequestBody Reply reply, HttpServletRequest request) {
        if (reply != null) {
            if (request.getUserPrincipal() != null && !request.getUserPrincipal().getName().equals("")) {
                reply.setRole("ADMIN");
                return new ResponseEntity<>(repLyService.saveReply(reply), HttpStatus.OK);
            } else {
                reply.setRole("VIEWER");
                return new ResponseEntity<>(repLyService.saveReply(reply), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("save fail ", HttpStatus.FAILED_DEPENDENCY);
    }
}
