package com.edcast.interwiev.frauddetection.service;

import com.edcast.interwiev.frauddetection.client.PdfCreatorApi;
import com.edcast.interwiev.frauddetection.model.PayloadResponse;
import com.edcast.interwiev.frauddetection.exception.FraudNotFoundException;
import com.edcast.interwiev.frauddetection.exception.IllegalInputFormatError;
import com.edcast.interwiev.frauddetection.model.*;
import com.edcast.interwiev.frauddetection.repository.FraudDetailRepository;
import com.edcast.interwiev.frauddetection.util.CountryNameUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

import static java.util.stream.Collectors.groupingBy;


@Slf4j
@Service
@RequiredArgsConstructor
public class FraudDetectionService {

    private final FraudDetailRepository fraudDetailRepository;
    private final PdfCreatorApi pdfCreatorApi;
    private final CountryNameUtil countryNameUtil;

    public FraudDetail crateFraudDetail(FraudDetail fraudDetail)  {
        log.debug("Request to save FraudDetail : {}", fraudDetail);
        if(fraudDetail.getCard().isBlank() && fraudDetail.getCompany().isEmpty()){
            throw new IllegalInputFormatError("FraudDetail data is empty!!");
        }
            return fraudDetailRepository.save(fraudDetail);

    }

    public Page<FraudDetail> getAllFrauds(int pageIndex, int pageSize) {
        log.debug("Request to get all frauds");
        Page<FraudDetail> response = fraudDetailRepository.findAll(PageRequest.of(pageIndex, pageSize));
        if(response.getSize() > 0) {
            return response;
        }else {
            throw new FraudNotFoundException("There is no any frauds");
        }
    }

    public FraudDetail getFraudDetailById(Long id) {
        log.debug("Request to get FraudDetail by id : {}", id);
        Optional<FraudDetail> getFraud = fraudDetailRepository.findById(id);
        if (getFraud.isPresent()) {
            return getFraud.get();
        } else {
            throw new FraudNotFoundException("FraudDetail not found!!");
        }
    }

    public PayloadResponse crateAPdfFile(FraudDetailRequest fraudDetailRequest) {
        log.debug("Creating a pdf file");
        List<FraudDetail> frauds = fraudDetailRepository.findByDateBetweenAndCompany(fraudDetailRequest.getFrom(),
                fraudDetailRequest.getTo(),fraudDetailRequest.getCompany());
        if(frauds.size() > 0) {
            Map<String,List<FraudDetail>> getFraudMap = frauds.stream().collect(groupingBy(FraudDetail::getCountry));
            Set<Record> records = new HashSet<>();
            for (List<FraudDetail> fraudDetails: getFraudMap.values()){
                Record record = new Record();
                for (FraudDetail fraudDetail : fraudDetails) {
                    record.setCountry(countryNameUtil.getCountryName(fraudDetail.getCountry()));
                    record.setPercentage(BigDecimal.valueOf(fraudDetails.size()* 100L /frauds.size()));
                    record.setTotal(fraudDetails.size());
                    records.add(record);
                }
            }

            Payload payload = Payload.builder()
                    .from(fraudDetailRequest.getFrom())
                    .overall(frauds.size())
                    .to(fraudDetailRequest.getTo())
                    .company(new Company(fraudDetailRequest.getCompany()))
                    .records(records)
                    .build();

            return pdfCreatorApi.createPdf(payload);
        }else {
            throw new FraudNotFoundException("There is no any frauds");
        }

    }
}
