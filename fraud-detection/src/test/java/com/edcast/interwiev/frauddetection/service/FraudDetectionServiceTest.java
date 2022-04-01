package com.edcast.interwiev.frauddetection.service;

import com.edcast.interwiev.frauddetection.model.FraudDetail;
import com.edcast.interwiev.frauddetection.repository.FraudDetailRepository;
import com.edcast.interwiev.frauddetection.util.TestHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * To unit test the service layer, we must use mock the DAO layer.
 * Then we can run the tests using MockitoExtension.*/

@ExtendWith(MockitoExtension.class)
public class FraudDetectionServiceTest {

    @InjectMocks
    private FraudDetectionService fraudDetectionService;

    @Mock
    FraudDetailRepository fraudDetailRepository;


    @Test
    public void shouldCreateACustomerWhenEnterProperData() {
        //GIVEN
        FraudDetail expectedFraud = TestHelper.createFraudDetail();

        //WHEN
        when(fraudDetailRepository.save(any(FraudDetail.class))).thenReturn(expectedFraud);
        FraudDetail actualFraudDetail = fraudDetectionService.crateFraudDetail(expectedFraud);

        //THEN
        Assertions.assertNotNull(actualFraudDetail);
    }

}
