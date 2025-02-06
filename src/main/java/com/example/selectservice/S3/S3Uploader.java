package com.example.selectservice.S3;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;

@Service
public class S3Uploader {

    private final S3Client s3Client;
    private final String region;

    @Value("${aws.s3.bucket-name}")
    private String bucketName;

    public S3Uploader(@Value("${aws.credentials.access-key}") String accessKey,
                      @Value("${aws.credentials.secret-key}") String secretKey,
                      @Value("${aws.region}") String region) {
        this.region = region;
        this.s3Client = S3Client.builder()
                .credentialsProvider(StaticCredentialsProvider.create(AwsBasicCredentials.create(accessKey, secretKey)))
                .region(Region.of(region))
                .build();
    }

    // 기존 파일 업로드 메서드
    public String uploadFile(File file, String key) {
        try {
            s3Client.putObject(PutObjectRequest.builder()
                            .bucket(bucketName)
                            .key(key)
                            .build(),
                    software.amazon.awssdk.core.sync.RequestBody.fromFile(file));
            return String.format("https://%s.s3.%s.amazonaws.com/%s", bucketName, region, key);
        } catch (Exception e) {
            throw new RuntimeException("Failed to upload file to S3", e);
        }
    }

    // MultipartFile 업로드 메서드 추가
    public String uploadMultipartFile(MultipartFile multipartFile, String key) {
        File tempFile = convertMultipartFileToFile(multipartFile);

        try {
            // S3 업로드 호출
            return uploadFile(tempFile, key);
        } finally {
            // 업로드 후 임시 파일 삭제
            deleteTempFile(tempFile);
        }
    }

    // MultipartFile을 File로 변환
    private File convertMultipartFileToFile(MultipartFile multipartFile) {
        try {
            File file = Files.createTempFile("upload-", multipartFile.getOriginalFilename()).toFile();
            try (FileOutputStream fos = new FileOutputStream(file)) {
                fos.write(multipartFile.getBytes());
            }
            return file;
        } catch (IOException e) {
            throw new RuntimeException("Failed to convert MultipartFile to File", e);
        }
    }

    // 임시 파일 삭제
    private void deleteTempFile(File file) {
        if (file != null && file.exists()) {
            file.delete();
        }
    }
}