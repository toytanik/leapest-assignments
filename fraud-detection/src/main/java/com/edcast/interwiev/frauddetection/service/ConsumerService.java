package com.edcast.interwiev.frauddetection.service;

import com.edcast.interwiev.frauddetection.model.FraudDetail;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class ConsumerService {

    private final FraudDetectionService fraudDetectionService;
    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "fraud-detected")
    public void processMessage(String content) throws JsonProcessingException {
        FraudDetail fraudDetail = objectMapper.readValue(content,FraudDetail.class);
        fraudDetectionService.crateFraudDetail(fraudDetail);
    }
}
