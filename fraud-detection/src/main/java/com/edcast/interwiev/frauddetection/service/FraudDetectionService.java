package com.edcast.interwiev.frauddetection.service;

import com.edcast.interwiev.frauddetection.exception.FraudAlreadyExist;
import com.edcast.interwiev.frauddetection.exception.FraudNotFoundException;
import com.edcast.interwiev.frauddetection.exception.IllegalInputFormatError;
import com.edcast.interwiev.frauddetection.model.FraudDetail;
import com.edcast.interwiev.frauddetection.model.FraudDetailRequest;
import com.edcast.interwiev.frauddetection.repository.FraudDetailRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Slf4j
@Service
@RequiredArgsConstructor
public class FraudDetectionService {

    private final FraudDetailRepository fraudDetailRepository;

    public FraudDetail crateFraudDetail(FraudDetail fraudDetail) {
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
            throw new FraudNotFoundException("There is no any customer");
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

    public Boolean crateAPdfFile(FraudDetailRequest fraudDetailRequest) {
        return null;
    }
}
