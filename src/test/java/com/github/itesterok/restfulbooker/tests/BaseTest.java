package com.github.itesterok.restfulbooker.tests;

import com.github.itesterok.restfulbooker.common.extensions.config.RestfulBookerConfigurationExtension;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.junit.jupiter.InjectSoftAssertions;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.RegisterExtension;

@ExtendWith(SoftAssertionsExtension.class)
public abstract class BaseTest {
    @RegisterExtension
    protected static final RestfulBookerConfigurationExtension restAssured =
        new RestfulBookerConfigurationExtension();

    protected static final String BOOKINGS_URL = "/booking";

    protected static final String SPECIFIC_BOOKING_URL = "/booking/{bookingId}";

    @InjectSoftAssertions
    protected SoftAssertions softly;
}
