package com.edcast.interwiev.frauddetection.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FraudDetail {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String country;
    private String card;
    private String company;

    private String date;

}
