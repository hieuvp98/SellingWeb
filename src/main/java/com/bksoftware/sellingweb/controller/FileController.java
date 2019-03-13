package com.bksoftware.sellingweb.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;


@RestController
@RequestMapping("api/v1/public/")
public class FileController {

    private static final String UPLOAD_DIRECTORY = "/home/caots/Desktop";


    //method for uploading single file
    @RequestMapping(value = "/upload-file", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> uploadFile(@RequestParam("file") MultipartFile file) {

        multiPartFile(file);
        return new ResponseEntity<>("file Uploaded successfully", HttpStatus.OK);
    }

    private void multiPartFile(@RequestParam("file") MultipartFile file) {
        File uploadedFile = new File(UPLOAD_DIRECTORY, file.getOriginalFilename());
        OutputStream stream;
        try {

            uploadedFile.createNewFile();
            stream = new BufferedOutputStream(new FileOutputStream(uploadedFile));
            stream.write(file.getBytes());
            stream.flush();
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //method for uploading multiple files
    @RequestMapping(value = "/uploadmultiFiles", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> uploadmultiFile(@RequestParam("files") MultipartFile[] files) {

        for (MultipartFile multipartFile : files) {

            multiPartFile(multipartFile);

        }

        return new ResponseEntity<>("All file Uploaded successfully", HttpStatus.OK);
    }



}
