package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import constants.APIEndpoints;
import io.restassured.response.Response;
import models.User;
import utils.ObjectMapperUtil;
import utils.RestServiceUtility;

public class TestCaseWithSerialAnDeserialization {
	
	@Test
    public void testCreateUserWithSerialization() {
        // Step 1: Create a User object (Java Object)
        User user = new User("John Doe", "Software Engineer");

        //  Step 2: Convert User object to JSON
        String requestBody = ObjectMapperUtil.toJson(user);

        //  Step 3: Call API
        Response response = RestServiceUtility.postRequest(APIEndpoints.CREATE_USER, requestBody);

        //  Step 4: Validate response status
        Assert.assertEquals(response.getStatusCode(), 201);

        //  Step 5: Deserialize JSON response to User object
        User responseUser = ObjectMapperUtil.fromJson(response.asString(), User.class);

        //  Step 6: Validate response data
        Assert.assertEquals(responseUser.getName(), user.getName());
        Assert.assertEquals(responseUser.getJob(), user.getJob());
    }

    @Test
    public void testFetchUserWithDeserialization() {
        String userId = "123";  // Example user ID
        String endpoint = APIEndpoints.UPDATE_USER.replace("{userId}", userId);

        //  Step 1: Call GET API
        Response response = RestServiceUtility.getRequest(endpoint);

        //  Step 2: Validate response status
        Assert.assertEquals(response.getStatusCode(), 200);

        //  Step 3: Deserialize JSON response into User object
        User responseUser = ObjectMapperUtil.fromJson(response.asString(), User.class);

        //  Step 4: Print fetched user details
        System.out.println("Fetched User: " + responseUser);

        //  Step 5: Add assertions if required (based on expected data)
        Assert.assertNotNull(responseUser.getName(), "User name should not be null");
        Assert.assertNotNull(responseUser.getJob(), "User job should not be null");

        // 🔹 Step 6: Validate specific expected values (modify based on expected API response)
        Assert.assertEquals(responseUser.getName(), "John Doe", "User name did not match expected value!");
        Assert.assertEquals(responseUser.getJob(), "Software Engineer", "User job did not match expected value!");
    }
}
