package com.github.itesterok.restfulbooker.tests;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import io.restassured.common.mapper.TypeRef;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DeleteBookingTest extends BaseTest {

    private static List<Map<String, Integer>> findByBookingId(
        List<Map<String, Integer>> bookings, Integer bookingId) {
        return bookings.stream()
            .filter(booking -> booking.get("bookingid").equals(bookingId))
            .collect(Collectors.toList());
    }

    @Test
    void deleteBooking() {
        // given
        var allBookings =
            restAssured.getNew().get(BOOKINGS_URL).as(new TypeRef<List<Map<String, Integer>>>() {
            });
        var bookingId = allBookings.get(allBookings.size() - 1).get("bookingid");

        // when
        var response =
            restAssured
                .getNewAuthenticated()
                .pathParams("bookingId", bookingId)
                .delete(SPECIFIC_BOOKING_URL);

        // then
        assertThat(response.getStatusCode()).isEqualTo(201);
        assertThat(
            findByBookingId(
                restAssured.getNew().get(BOOKINGS_URL).as(new TypeRef<>() {
                }), bookingId))
            .isNullOrEmpty();
    }
}
