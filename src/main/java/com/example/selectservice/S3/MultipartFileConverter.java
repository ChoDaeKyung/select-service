package com.example.selectservice.S3;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class MultipartFileConverter {

    public static MultipartFile convertStringToMultipartFile(String filePath) {
        try {
            File file = new File(filePath);
            try (FileInputStream inputStream = new FileInputStream(file)) {
                byte[] content = inputStream.readAllBytes();
                return new CustomMultipartFile(
                        file.getName(),
                        file.getName(),
                        "application/octet-stream",
                        content
                );
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to convert String to MultipartFile", e);
        }
    }
}