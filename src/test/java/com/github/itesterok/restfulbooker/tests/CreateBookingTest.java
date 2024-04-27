package com.github.itesterok.restfulbooker.tests;

import com.github.itesterok.restfulbooker.api.model.BookingResponse;
import com.github.itesterok.restfulbooker.common.utils.DataGenerator;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public final class CreateBookingTest extends BaseTest {

    @Test
    void createNewBooking() {
        // given
        var booking = new DataGenerator().generateRandomBooking();

        // when
        var response =
            restAssured
                .getNew()
                .headers("Content-Type", ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(booking)
                .post(BOOKINGS_URL);
        var actualBooking = response.as(BookingResponse.class).getBooking();

        // then
        assertThat(response.getStatusCode()).isEqualTo(200);
        assertThat(actualBooking).isEqualTo(booking);
    }
}
