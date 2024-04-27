package com.github.itesterok.restfulbooker.tests;

import java.util.List;
import java.util.Map;

import io.restassured.common.mapper.TypeRef;
import org.junit.jupiter.api.Test;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.assertj.core.api.Assertions.assertThat;

public final class GetBookingTest extends BaseTest {

    @Test
    void getAllBookings() {
        // given and when
        var response =
            restAssured.getNew().get(BOOKINGS_URL).as(new TypeRef<List<Map<String, Integer>>>() {
            });

        // then
        assertThat(response).hasSizeGreaterThan(10);
    }

    @Test
    void getSpecificBooking() {
        // given anf when
        var response = restAssured.getNew().pathParams("bookingId", "1").get(SPECIFIC_BOOKING_URL);

        // then
        assertThat(response.getStatusCode()).isEqualTo(200);
        response.then().assertThat().body(matchesJsonSchemaInClasspath("booking.schema.json"));
    }
}
