package com.edcast.interwiev.frauddetection.controller;


import com.edcast.interwiev.frauddetection.model.FraudDetail;
import com.edcast.interwiev.frauddetection.service.FraudDetectionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/fraud")
@RequiredArgsConstructor
public class FraudDetectionController {

    private final FraudDetectionService fraudDetectionService;

    @PostMapping
    public ResponseEntity<FraudDetail> createCustomer(@RequestBody  FraudDetail fraudDetail) {
        return ResponseEntity.ok(fraudDetectionService.crateFraudDetail(fraudDetail));
    }


    @GetMapping("/list")
    public ResponseEntity<Page<FraudDetail>> getCustomerByUserName(@RequestParam int pageIndex, @RequestParam int pageSize) {
        return ResponseEntity.ok(fraudDetectionService.getAllFrauds(pageIndex, pageSize));
    }

    @PostMapping("/report/generate")
    public ResponseEntity<Page<FraudDetail>> getAllCustomer(@RequestParam int pageIndex, @RequestParam int pageSize) {
        return ResponseEntity.ok(fraudDetectionService.getAllFrauds(pageIndex, pageSize));
    }

    @GetMapping("/report/{id}")
    public ResponseEntity<FraudDetail> getFraudById(@PathVariable Long id) {
        return ResponseEntity.ok(fraudDetectionService.getFraudDetailById(id));
    }

}