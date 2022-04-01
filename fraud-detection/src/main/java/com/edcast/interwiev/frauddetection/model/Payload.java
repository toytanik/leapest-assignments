package com.edcast.interwiev.frauddetection.model;

import lombok.*;
import java.util.Set;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Payload {

    private Company company;
    private Set<Record> records;
    private String from;
    private String to;
    private Integer overall;
}
