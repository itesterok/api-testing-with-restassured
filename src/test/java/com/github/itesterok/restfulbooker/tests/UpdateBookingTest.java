package com.github.itesterok.restfulbooker.tests;

import com.github.itesterok.restfulbooker.api.model.Booking;
import com.github.itesterok.restfulbooker.common.utils.DataGenerator;
import org.junit.jupiter.api.Test;

public final class UpdateBookingTest extends BaseTest {

    @Test
    void updateBooking() {
        // given
        var existingBookingId = 1;
        var existingBookingInfo =
            restAssured
                .getNew()
                .pathParams("bookingId", existingBookingId)
                .get(SPECIFIC_BOOKING_URL)
                .as(Booking.class);

        var updatedBooking = new DataGenerator().generateRandomBooking();

        // when
        var updatedBookingResponse =
            restAssured
                .getNewAuthenticated()
                .pathParams("bookingId", existingBookingId)
                .body(updatedBooking)
                .put(SPECIFIC_BOOKING_URL);
        var updatedBookingInfo = updatedBookingResponse.as(Booking.class);

        // then
        softly.assertThat(updatedBookingResponse.getStatusCode()).isEqualTo(200);
        softly.assertThat(updatedBookingInfo).isNotEqualTo(existingBookingInfo);
        softly.assertThat(updatedBookingInfo).isEqualTo(updatedBooking);
    }
}
