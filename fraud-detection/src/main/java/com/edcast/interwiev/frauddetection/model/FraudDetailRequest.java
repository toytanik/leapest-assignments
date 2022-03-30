package com.edcast.interwiev.frauddetection.model;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FraudDetailRequest {

    private LocalDate startDate;
    private LocalDate endDate;
    private String company;
}
