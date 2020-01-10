package com.zipcode.socialStream.services;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


public class S3StorageService {
    private static final String AKID = System.getenv("AWS_ACCESS_KEY_ID");
    private static final String SAK = System.getenv("AWS_SECRET_ACCESS_KEY_ID");
    private static final String bucketName = "socialstreamzipcode";

    public static String upload(File file){
        PutObjectRequest request = new PutObjectRequest(bucketName, file.getName(), file);
        AmazonS3 s3client = getS3();
        s3client.putObject(request);
        return "//" + bucketName + ".s3.us-east-2.amazonaws.com/" + file.getName();
    }

    private static AmazonS3 getS3() {
        return AmazonS3ClientBuilder.standard()
            .withRegion(Regions.US_EAST_2)
            .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(AKID, SAK)))
            .build();
    }

    public static File convertToFile(MultipartFile multipartFile, Long id) throws IOException{
        File file = new File("video" + id + StringUtils.cleanPath(multipartFile.getOriginalFilename()));
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(multipartFile.getBytes());
        fos.close();
        return file;
    }
}
