package com.github.itesterok.restfulbooker.api.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Builder
@Data
@Jacksonized
public class Booking {
    @JsonProperty("firstname")
    private String firstname;

    @JsonProperty("lastname")
    private String lastname;

    @JsonProperty("totalprice")
    private int totalprice;

    @JsonProperty("depositpaid")
    private boolean depositpaid;

    @JsonProperty("bookingdates")
    private Bookingdates bookingdates;

    @JsonProperty("additionalneeds")
    private String additionalneeds;

    @Builder
    @Data
    @Jacksonized
    public static class Bookingdates {
        @JsonProperty("checkin")
        private LocalDate checkin;

        @JsonProperty("checkout")
        private LocalDate checkout;
    }
}
