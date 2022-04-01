package com.edcast.interwiev.frauddetection.util;


import com.edcast.interwiev.frauddetection.model.FraudDetail;

public class TestHelper {

    public static FraudDetail createFraudDetail() {
        return new FraudDetail(1L,"tr","4234 2344 2344 2434","tuncaycompany","11-22-2022");
    }

}
