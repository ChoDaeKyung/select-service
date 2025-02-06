package com.example.selectservice.service;

import com.example.selectservice.S3.S3Uploader;
import com.example.selectservice.dto.*;
import com.example.selectservice.mapper.AdminMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.MessageAttributeValue;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;

import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final AdminMapper adminMapper;
    private final S3Uploader s3Uploader;
    private final ObjectMapper objectMapper;

    private final SqsClient sqsClient;
    private final String queueUrl = "https://sqs.ap-northeast-2.amazonaws.com/879381276515/products";

    @Transactional
    public void addCompleteProduct(AddCompleteProductRequestDTO requestDTO, MultipartFile image) throws IOException {

        // 메시지 본문 생성
        String messageBody = objectMapper.writeValueAsString(Map.of(
                "action", "addCompleteProduct",
                "data", Map.of(
                        "completeName", requestDTO.getCompleteName(),
                        "productsList", requestDTO.getProductsList(),
                        "price", requestDTO.getPrice(),
                        "detail", requestDTO.getDetail()
                )
        ));

        // 메시지 속성 생성 (이미지 바이너리 포함)
        Map<String, MessageAttributeValue> messageAttributes = Map.of(
                "image", MessageAttributeValue.builder()
                        .dataType("Binary")
                        .binaryValue(SdkBytes.fromByteArray(image.getBytes()))
                        .build()
        );

        // SQS 메시지 전송
        try {
            SendMessageRequest sendMessageRequest = SendMessageRequest.builder()
                    .queueUrl(queueUrl)
                    .messageBody(messageBody)
                    .messageAttributes(messageAttributes)
                    .build();

            sqsClient.sendMessage(sendMessageRequest);
            System.out.println("SQS 메시지 전송 성공");
        } catch (Exception e) {
            throw new RuntimeException("SQS 메시지 전송 실패", e);
        }
    }

    @Transactional
    public void addProduct(AddProductRequestDTO requestDTO, MultipartFile image) throws IOException {

        String messageBody = objectMapper.writeValueAsString(Map.of(
                "action", "addProduct",
                "data", Map.of(
                        "name", requestDTO.getName(),
                        "category", requestDTO.getCategory(),
                        "price", requestDTO.getPrice()
                )
        ));

        Map<String, MessageAttributeValue> messageAttributes = Map.of(
                "image", MessageAttributeValue.builder()
                        .dataType("Binary")
                        .binaryValue(SdkBytes.fromByteArray(image.getBytes()))
                        .build()
        );

        try {
            SendMessageRequest sendMessageRequest = SendMessageRequest.builder()
                    .queueUrl(queueUrl)
                    .messageBody(messageBody) // JSON 본문 사용
                    .messageAttributes(messageAttributes)
                    .build();

            sqsClient.sendMessage(sendMessageRequest);
            System.out.println("SQS 메시지 전송 성공");

        } catch (Exception e) {
            throw new RuntimeException("SQS 메시지 전송 실패", e);
        }
    }

    @Transactional
    public void addSideMenu(AddSideMenuRequestDTO requestDTO, MultipartFile image) throws IOException {
        // 본문에 DTO 데이터와 액션 정보를 JSON으로 직렬화
        String messageBody = objectMapper.writeValueAsString(Map.of(
                "action", "addSideMenu",
                "data", Map.of(
                        "name", requestDTO.getName(),
                        "category", requestDTO.getCategory(),
                            "price", requestDTO.getPrice(),
                        "detail", requestDTO.getDetail()
                )
        ));

        Map<String, MessageAttributeValue> messageAttributes = Map.of(
                "image", MessageAttributeValue.builder()
                        .dataType("Binary")
                        .binaryValue(SdkBytes.fromByteArray(image.getBytes()))
                        .build()
        );

        try {
            SendMessageRequest sendMessageRequest = SendMessageRequest.builder()
                    .queueUrl(queueUrl)
                    .messageBody(messageBody) // JSON 본문 사용
                    .messageAttributes(messageAttributes)
                    .build();

            sqsClient.sendMessage(sendMessageRequest);
            System.out.println("SQS 메시지 전송 성공");

        } catch (Exception e) {
            throw new RuntimeException("SQS 메시지 전송 실패", e);
        }
    }
}
