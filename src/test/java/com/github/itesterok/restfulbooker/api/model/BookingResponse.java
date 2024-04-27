package com.github.itesterok.restfulbooker.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class BookingResponse {

    @JsonProperty("bookingid")
    private Integer bookingId;

    @JsonProperty("booking")
    private Booking booking;
}
