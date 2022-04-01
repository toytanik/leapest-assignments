package com.edcast.interwiev.frauddetection.model;

import lombok.*;

import java.util.Map;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PayloadResponse {

    private Map<String,File> data;
}
