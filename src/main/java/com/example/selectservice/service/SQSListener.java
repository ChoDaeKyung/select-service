package com.example.selectservice.service;

import com.example.selectservice.S3.S3Uploader;
import com.example.selectservice.dto.*;
import com.example.selectservice.mapper.AdminMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.awspring.cloud.sqs.annotation.SqsListener;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sqs.model.Message;
import software.amazon.awssdk.services.sqs.model.MessageAttributeValue;


import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SQSListener {

    private final AdminMapper adminMapper;
    private final S3Uploader s3Uploader;
    private final ObjectMapper objectMapper;

    @SqsListener("products")
    public void processMessage(Message message) {
        try {
            // 메시지 본문 출력
            String messageBody = message.body();
//            System.out.println("메시지 본문: " + messageBody);

            // 메시지 속성 출력
            Map<String, MessageAttributeValue> attributes = message.messageAttributes();
//            System.out.println("메시지 속성: " + attributes);


            // JSON 파싱
            JsonNode jsonNode = objectMapper.readTree(messageBody);


            // "action" 필드 추출
            String action = jsonNode.get("action").asText();

            System.out.println("action: " + action);

            if(action.equals("addSideMenu")) {
                JsonNode dataNode = jsonNode.get("data");
                String name = dataNode.get("name").asText();
                String category = dataNode.get("category").asText();
                int price = dataNode.get("price").asInt();
                String detail = dataNode.get("detail").asText();

                // 이미지 데이터 추출
                if (!attributes.containsKey("image") || attributes.get("image").binaryValue() == null) {
                    throw new IllegalArgumentException("image 속성이 누락되었습니다.");
                }
                byte[] imageBytes = attributes.get("image").binaryValue().asByteArray();

                // 임시 파일 저장
                String tempFilePath = "/tmp/" + UUID.randomUUID() + ".jpg";
                File tempFile = new File(tempFilePath);
                try (FileOutputStream fos = new FileOutputStream(tempFile)) {
                    fos.write(imageBytes);
                }

                // DTO 생성
                AddSideMenuRequestDTO requestDTO = AddSideMenuRequestDTO.builder()
                        .name(name)
                        .category(category)
                        .price(price)
                        .detail(detail)
                        .imagePath(tempFilePath)
                        .build();

                System.out.println("메시지 처리: " + requestDTO);

                // S3 업로드
                String key = "images/" + UUID.randomUUID() + ".jpg";
                String imagePath = s3Uploader.uploadFile(tempFile, key);

                // DB 저장
                adminMapper.addSideMenu(AddSideMenuRequestDTO.builder()
                        .name(requestDTO.getName())
                        .category(requestDTO.getCategory())
                        .price(requestDTO.getPrice())
                        .detail(requestDTO.getDetail())
                        .imagePath(imagePath)
                        .build());

                System.out.println("DB 저장 성공");
            }
            else if(action.equals("addProduct")) {
                JsonNode dataNode = jsonNode.get("data");
                String name = dataNode.get("name").asText();
                String category = dataNode.get("category").asText();
                int price = dataNode.get("price").asInt();

                // 이미지 데이터 추출
                if (!attributes.containsKey("image") || attributes.get("image").binaryValue() == null) {
                    throw new IllegalArgumentException("image 속성이 누락되었습니다.");
                }
                byte[] imageBytes = attributes.get("image").binaryValue().asByteArray();

                // 임시 파일 저장
                String tempFilePath = "/tmp/" + UUID.randomUUID() + ".jpg";
                File tempFile = new File(tempFilePath);
                try (FileOutputStream fos = new FileOutputStream(tempFile)) {
                    fos.write(imageBytes);
                }

                // DTO 생성
                AddProductRequestDTO requestDTO = AddProductRequestDTO.builder()
                        .name(name)
                        .category(category)
                        .price(price)
                        .imagePath(tempFilePath)
                        .build();

                System.out.println("메시지 처리: " + requestDTO);

                // S3 업로드
                String key = "images/" + UUID.randomUUID() + ".jpg";
                String imagePath = s3Uploader.uploadFile(tempFile, key);

                // DB 저장
                adminMapper.addProduct(AddProductRequestDTO.builder()
                        .name(requestDTO.getName())
                        .category(requestDTO.getCategory())
                        .price(requestDTO.getPrice())
                        .imagePath(imagePath)
                        .build());

                System.out.println("DB 저장 성공");
            }
            else if (action.equals("addCompleteProduct")) {
                JsonNode dataNode = jsonNode.get("data");

                // 데이터 파싱
                String completeName = dataNode.get("completeName").asText();
                List<AddCompleteProductDetailDto> productsList = objectMapper.convertValue(
                        dataNode.get("productsList"),
                        new TypeReference<List<AddCompleteProductDetailDto>>() {}
                );
                int price = dataNode.get("price").asInt();
                String detail = dataNode.get("detail").asText();

                // 이미지 데이터 추출
                if (!attributes.containsKey("image") || attributes.get("image").binaryValue() == null) {
                    throw new IllegalArgumentException("image 속성이 누락되었습니다.");
                }
                byte[] imageBytes = attributes.get("image").binaryValue().asByteArray();

                // 임시 파일 생성
                String tempFilePath = "/tmp/" + UUID.randomUUID() + ".jpg";
                File tempFile = new File(tempFilePath);
                try (FileOutputStream fos = new FileOutputStream(tempFile)) {
                    fos.write(imageBytes);
                }

                // S3 업로드
                String key = "images/" + UUID.randomUUID() + ".jpg";
                String imagePath = s3Uploader.uploadFile(tempFile, key);

                // DTO 생성
                AddCompleteProductRequestDTO requestDTO = AddCompleteProductRequestDTO.builder()
                        .completeName(completeName)
                        .productsList(productsList)
                        .price(price)
                        .detail(detail)
                        .imagePath(imagePath)
                        .build();

                // DB 저장
                adminMapper.addCompleteProduct(requestDTO);

                // 'used' 필드 업데이트
                for (AddCompleteProductDetailDto product : productsList) {
                    UsedUpdateRequestDTO usedUpdateRequestDTO = new UsedUpdateRequestDTO();
                    usedUpdateRequestDTO.setName(product.getName());
                    usedUpdateRequestDTO.setCompleteName(completeName);
                    adminMapper.addUsed(usedUpdateRequestDTO);
                }

                System.out.println("addCompleteProduct 처리 완료");
            }
        } catch (Exception e) {
            System.err.println("메시지 처리 중 오류 발생: " + e.getMessage());
        }
    }
}