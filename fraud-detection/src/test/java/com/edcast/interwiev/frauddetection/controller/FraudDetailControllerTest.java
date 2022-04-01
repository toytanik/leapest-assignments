package com.edcast.interwiev.frauddetection.controller;

import com.edcast.interwiev.frauddetection.model.FraudDetail;
import com.edcast.interwiev.frauddetection.service.FraudDetectionService;
import com.edcast.interwiev.frauddetection.util.TestHelper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Write a Test class annotated with @WebMvcTest.
 * We can specify which Controller we want to test
 * in the annotation value itself.*/

@ExtendWith(SpringExtension.class)
@WebMvcTest(FraudDetectionController.class)
public class FraudDetailControllerTest {

    @MockBean
    FraudDetectionService fraudDetectionService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;


    @Test
    @WithMockUser(username = "admin", password = "password", roles = "USER")
    public void shouldCreateCustomerWhenInvokingCreateEndpoint() throws Exception {
        //GIVEN
        FraudDetail expectedFraud = TestHelper.createFraudDetail();
        String fraudContent = objectMapper.writeValueAsString(expectedFraud);

        //WHEN
        Mockito.when(fraudDetectionService.crateFraudDetail(Mockito.any(FraudDetail.class)))
                .thenReturn(expectedFraud);

        //THEN
        mockMvc.perform(post("/api/fraud")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(fraudContent))
                        .andExpect(status().isOk());
    }

}
