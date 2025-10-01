package dev.pets.spec;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;


public class BaseSpec {
    private BaseSpec() {
    }

    private static final String BASE_URL = "https://petstore.swagger.io";
    private static final String BASE_PATH = "/v2";

    public static String getBaseUrl() {
        return System.getProperty("baseUrl", BASE_URL);
    }

    public static String getBasePath() {
        return System.getProperty("basePath", BASE_PATH);
    }

    public static RequestSpecification getRequestSpecification() {
        var httpConfig = HttpClientConfig.httpClientConfig()
                .setParam("http.connection.timeout", 5000)
                .setParam("http.socket.timeout", 5000)
                .setParam("http.connection-manager.timeout", 5000);

        return new RequestSpecBuilder()
                .setBaseUri(getBaseUrl())
                .setBasePath(getBasePath())
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .setConfig(RestAssuredConfig.config().httpClient(httpConfig))
                .log(LogDetail.URI)
                .log(LogDetail.METHOD)
                .build();
    }

    public static ResponseSpecification ok200() {
        return new ResponseSpecBuilder()
                .expectStatusCode(200)
                .build();
    }
}
