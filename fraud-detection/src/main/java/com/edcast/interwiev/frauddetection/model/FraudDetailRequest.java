package com.edcast.interwiev.frauddetection.model;
import lombok.*;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FraudDetailRequest {

    private String from;
    private String to;
    private String company;
}
