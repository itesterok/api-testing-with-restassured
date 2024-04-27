package com.github.itesterok.restfulbooker.common.utils;

import java.util.concurrent.TimeUnit;

import com.github.itesterok.restfulbooker.api.model.Booking;
import com.github.javafaker.Faker;

import static com.github.itesterok.restfulbooker.common.utils.DateToLocalDateConvertor.dateToLocalDate;

public class DataGenerator {

    private final Faker faker = new Faker();

    public final Booking generateRandomBooking() {
        return Booking.builder()
            .firstname(faker.name().firstName())
            .lastname(faker.name().lastName())
            .totalprice(faker.number().numberBetween(0, 999))
            .depositpaid(faker.bool().bool())
            .bookingdates(
                Booking.Bookingdates.builder()
                    .checkin(dateToLocalDate(faker.date().future(1, TimeUnit.DAYS)))
                    .checkout(dateToLocalDate(faker.date().future(50, TimeUnit.DAYS)))
                    .build())
            .additionalneeds(faker.yoda().quote())
            .build();
    }
}
