package com.edcast.interwiev.frauddetection.model;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Record {

    private String country;
    private BigDecimal percentage;
    private Integer total;
}
