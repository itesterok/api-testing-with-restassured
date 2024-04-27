package com.github.itesterok.restfulbooker.tests;

import com.github.itesterok.restfulbooker.api.model.Booking;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;

public final class PartialUpdateBookingTest extends BaseTest {

    private final Faker faker = new Faker();

    @Test
    void partialUpdateBooking() {
        // given
        var existingBookingId = 2;
        var existingBookingInfo =
            restAssured
                .getNewAuthenticated()
                .pathParams("bookingId", existingBookingId)
                .patch(SPECIFIC_BOOKING_URL)
                .as(Booking.class);
        var newFirstName = faker.name().firstName();
        var newLastName = faker.name().lastName();

        existingBookingInfo.setFirstname(newFirstName);
        existingBookingInfo.setLastname(newLastName);

        // when
        var updatedBookingResponse =
            restAssured
                .getNewAuthenticated()
                .pathParams("bookingId", existingBookingId)
                .body(
                    """
                        {"firstname": "%s", "lastname": "%s"}"""
                        .formatted(newFirstName, newLastName))
                .patch(SPECIFIC_BOOKING_URL);
        var updatedBookingInfo = updatedBookingResponse.as(Booking.class);

        // then
        softly.assertThat(updatedBookingResponse.getStatusCode()).isEqualTo(200);
        softly.assertThat(updatedBookingInfo).isEqualTo(existingBookingInfo);
    }
}
