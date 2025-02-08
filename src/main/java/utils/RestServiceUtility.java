package utils;

import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import specs.RequestSpec;
import specs.ResponseSpec;

public class RestServiceUtility {
    public static Response getRequest(String endpoint) {
        return given()
                .spec(RequestSpec.getRequestSpec())
                .when()
                .get(endpoint)
                .then()
                .spec(ResponseSpec.getResponseSpec())
                .extract()
                .response();
    }

    public static Response postRequest(String endpoint, Object body) {
        return given()
                .spec(RequestSpec.getRequestSpec())
                .body(body)
                .when()
                .post(endpoint)
                .then()
                .spec(ResponseSpec.getResponseSpec())
                .extract()
                .response();
    }
    
    public static Response patchRequest(String endpoint, String requestBody) {
        return given()
                .spec(RequestSpec.getRequestSpec())
                .body(requestBody)
                .when()
                .patch(endpoint)
                .then()
                .spec(ResponseSpec.getResponseSpec())
                .extract()
                .response();
    }
    
    public static Response putRequest(String endpoint, String requestBody) {
        return given()
                .spec(RequestSpec.getRequestSpec())
                .body(requestBody)
                .when()
                .put(endpoint)
                .then()
                .spec(ResponseSpec.getResponseSpec())
                .extract()
                .response();
    }
}
