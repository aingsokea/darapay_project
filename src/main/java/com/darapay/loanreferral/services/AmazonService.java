package com.darapay.loanreferral.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public interface AmazonService {
    String[] uploadFileToS3(MultipartFile[] multipartFiles);
}
