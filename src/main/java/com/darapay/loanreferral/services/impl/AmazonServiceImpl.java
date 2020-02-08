package com.darapay.loanreferral.services.impl;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.darapay.loanreferral.services.AmazonService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

@Service
@Repository
public class AmazonServiceImpl implements AmazonService {

    private AmazonS3 s3client;

    @Value("${cloud.aws.region.endpointurl}")
    private String endpointUrl;
    @Value("${app.awsServices.bucketName}")
    private String bucketName;
    @Value("${cloud.aws.credentials.accessKey}")
    private String accessKey;
    @Value("${cloud.aws.credentials.secretKey}")
    private String secretKey;
    @PostConstruct
    private void initializeAmazon() {
        AWSCredentials credentials = new BasicAWSCredentials(this.accessKey, this.secretKey);
        this.s3client = new AmazonS3Client(credentials);
    }

    public String[] uploadFileToS3(MultipartFile[] multipartFiles) {
        String[] filesUrl = new String[multipartFiles.length];
        String fileUrl;
        int i = 0;
        try {
            for (MultipartFile multipartFile: multipartFiles) {
                File file = convertMultiPartToFile(multipartFile);
                String fileName = generateFileName(multipartFile);
                uploadFileTos3bucket(fileName, file);
                fileUrl = "https://" + bucketName + endpointUrl + "/" + fileName;
                file.delete();
                filesUrl[i] = fileUrl.toString();
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return filesUrl;
    }

    private File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }


    private String generateFileName(MultipartFile multiPart) {
        return new Date().getTime() + "-" + multiPart.getOriginalFilename().replace(" ", "_");
    }

    private void uploadFileTos3bucket(String fileName, File file) {
        s3client.putObject(new PutObjectRequest(bucketName, fileName, file)
                .withCannedAcl(CannedAccessControlList.PublicRead));
    }

    public String deleteFileFromS3Bucket(String fileUrl) {
        String fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
        s3client.deleteObject(new DeleteObjectRequest(bucketName, fileName));
        return "Successfully deleted";
    }
}
