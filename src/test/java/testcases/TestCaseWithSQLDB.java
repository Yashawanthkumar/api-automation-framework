package testcases;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import constants.APIEndpoints;
import io.restassured.response.Response;
import utils.DBUtilities;
import utils.RestServiceUtility;

public class TestCaseWithSQLDB {
	
	@Test
    public void testCreateUserFromDB() {
        //  Step 1: Fetch test data from the database
        String sql = "SELECT name, job FROM users WHERE id = 1";
        Map<String, Object> userData = DBUtilities.getSingleRowData(sql);

        //  Step 2: Extract values from DB result
        String name = (String) userData.get("name");
        String job = (String) userData.get("job");

        //  Step 3: Create JSON payload dynamically
        String userPayload = String.format("{ \"name\": \"%s\", \"job\": \"%s\" }", name, job);

        //  Step 4: Call API with data from DB
        Response response = RestServiceUtility.postRequest(APIEndpoints.CREATE_USER, userPayload);

        //  Step 5: Validate response
        Assert.assertEquals(response.getStatusCode(), 201);
        Assert.assertEquals(response.jsonPath().getString("name"), name);
        Assert.assertEquals(response.jsonPath().getString("job"), job);
    }
}
