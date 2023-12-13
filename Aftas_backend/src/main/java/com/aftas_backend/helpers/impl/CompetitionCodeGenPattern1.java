package com.aftas_backend.helpers.impl;
import com.aftas_backend.helpers.CompetitionCodeGen;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
@Component
public class CompetitionCodeGenPattern1 implements CompetitionCodeGen {
    @Override
    public String generateCode(String location, LocalDate date) {
//        get just first three letters of location
        String locationCode = location.substring(0, 3).toLowerCase();
//        get last two digits of year
        String yearCode = String.valueOf(date.getYear()).substring(2);
//        get month
        String monthCode = String.valueOf(date.getMonthValue());
//        get day
        String dayCode = String.valueOf(date.getDayOfMonth());
        String code = locationCode + "-" + yearCode +"-" + monthCode +"-" + dayCode;
        return code;

    }

}
