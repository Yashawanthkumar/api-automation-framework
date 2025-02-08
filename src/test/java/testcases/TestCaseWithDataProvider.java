package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import constants.APIEndpoints;
import io.restassured.response.Response;
import utils.ExternalDataReader;
import utils.RestServiceUtility;

public class TestCaseWithDataProvider {

	@Test(dataProvider = "userJsonDataProvider", dataProviderClass = ExternalDataReader.class)
    public void testCreateUserUsingJsonData(String name, String job) {
        String userPayload = String.format("{ \"name\": \"%s\", \"job\": \"%s\" }", name, job);
        Response response = RestServiceUtility.postRequest(APIEndpoints.CREATE_USER, userPayload);

        Assert.assertEquals(response.getStatusCode(), 201);
        Assert.assertEquals(response.jsonPath().getString("name"), name);
        Assert.assertEquals(response.jsonPath().getString("job"), job);
    }
	
	@Test(dataProvider = "userDataProvider", dataProviderClass = ExternalDataReader.class)
    public void testCreateUserUsingExcelData(String name, String job) {
        String userPayload = String.format("{ \"name\": \"%s\", \"job\": \"%s\" }", name, job);
        Response response = RestServiceUtility.postRequest(APIEndpoints.CREATE_USER, userPayload);

        Assert.assertEquals(response.getStatusCode(), 201);
        Assert.assertEquals(response.jsonPath().getString("name"), name);
        Assert.assertEquals(response.jsonPath().getString("job"), job);
    }
}
