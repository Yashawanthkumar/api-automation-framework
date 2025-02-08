package constants;

public class APIEndpoints {
    public static final String BASE_URL = "/api";
    public static final String UPDATE_USER = BASE_URL + "/users/{userId}"; // Update User Endpoint
    public static final String GET_USERS = BASE_URL + "/users";
    public static final String CREATE_USER = BASE_URL + "/users";
    public static final String GRAPHQL_ENDPOINT = BASE_URL + "/graphql";
}
