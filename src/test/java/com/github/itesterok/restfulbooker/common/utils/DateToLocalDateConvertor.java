package com.github.itesterok.restfulbooker.common.utils;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public final class DateToLocalDateConvertor {

    private DateToLocalDateConvertor() {
    }

    public static LocalDate dateToLocalDate(Date dateToConvert) {
        return dateToConvert.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
}
