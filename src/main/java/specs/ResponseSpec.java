package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;

public class ResponseSpec {
    public static ResponseSpecification getResponseSpec() {
        return new ResponseSpecBuilder()
                .expectStatusCode(200)
                .build();
    }
}
