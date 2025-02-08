package utils;

import static io.restassured.RestAssured.given;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import io.restassured.response.Response;
import specs.RequestSpec;
import specs.ResponseSpec;

public class GraphQLUtility {
    public static Response executeQuery(String query) {
        return given()
                .spec(RequestSpec.getRequestSpec())
                .body("{ \"query\": \"" + query + "\" }")
                .when()
                .post("/graphql")
                .then()
                .spec(ResponseSpec.getResponseSpec()) // Use pre-defined Response Specification
                .extract()
                .response();
    }
    
    public static Response sendGraphQLMutation(String mutationFile, Map<String, Object> variables) {
        String mutation = readGraphQLFile(mutationFile);

        // Constructing the GraphQL request body
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("query", mutation);
        requestBody.put("variables", variables);

        return given()
        		.spec(RequestSpec.getRequestSpec())  // Use pre-defined Request Specification
                .body(requestBody)
                .when()
                .post("/graphql")
                .then()
                .spec(ResponseSpec.getResponseSpec()) // Use pre-defined Response Specification
                .extract()
                .response();
    }
    
    // Method to read a GraphQL mutation/query file from resources
    public static String readGraphQLFile(String fileName) {
        try {
            return new String(Files.readAllBytes(Paths.get("src/test/resources/graphql/" + fileName)));
        } catch (Exception e) {
            throw new RuntimeException("Error reading GraphQL file: " + e.getMessage());
        }
    }
}
