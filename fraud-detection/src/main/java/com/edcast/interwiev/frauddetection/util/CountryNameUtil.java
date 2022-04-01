package com.edcast.interwiev.frauddetection.util;

import org.springframework.stereotype.Component;

import java.util.Locale;
@Component
public class CountryNameUtil {

    public String getCountryName(String code) {
        Locale locale = new Locale("en", code);
        return locale.getDisplayCountry();
    }

}
