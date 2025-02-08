package specs;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import config.ConfigReader;

public class RequestSpec {
    public static RequestSpecification getRequestSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(ConfigReader.getProperty("baseURL"))
                .addHeader("Content-Type", "application/json")
                .build();
    }
}
