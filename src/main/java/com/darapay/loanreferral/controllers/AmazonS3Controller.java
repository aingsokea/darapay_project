package com.darapay.loanreferral.controllers;

import com.darapay.loanreferral.services.AmazonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/amazons3")
public class AmazonS3Controller {

    @Autowired
    private AmazonService amazonService;

    @PostMapping("/uploadFile")
    public String[] uploadFile(@RequestParam(value = "files") MultipartFile[] files) {
        return this.amazonService.uploadFileToS3(files);
    }
}
