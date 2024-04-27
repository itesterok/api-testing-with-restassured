package com.github.itesterok.restfulbooker.common.extensions.config;

import com.github.itesterok.restfulbooker.api.model.AuthRequest;
import com.github.itesterok.restfulbooker.common.utils.PropertiesConfig;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import static io.restassured.RestAssured.given;

public class RestfulBookerConfigurationExtension implements BeforeAllCallback {

    private PropertiesConfig config;

    @Override
    public void beforeAll(ExtensionContext extensionContext) {
        config = ConfigFactory.create(PropertiesConfig.class);
    }

    public RequestSpecification getNewAuthenticated() {
        return getNew().cookies("token", getToken());
    }

    public RequestSpecification getNew() {
        return given()
            .filters(new RequestLoggingFilter(), new ResponseLoggingFilter(), new AllureRestAssured())
            .baseUri(config.hostname())
            .contentType(ContentType.JSON)
            .headers("Content-Type", ContentType.JSON)
            .headers("Accept", ContentType.JSON);
    }

    private String getToken() {
        return getNew()
            .body(AuthRequest.builder().username(config.username()).password(config.password()).build())
            .post("/auth")
            .jsonPath()
            .get("token");
    }
}
