package testcases;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import constants.APIEndpoints;
import io.restassured.response.Response;
import utils.GraphQLUtility;
import utils.RestServiceUtility;

public class BasicAPITest {

	@Test
	public void testGetUsers() {
		Response response = RestServiceUtility.getRequest(APIEndpoints.GET_USERS);
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertTrue(response.jsonPath().getList("data").size() > 0, "Users list is empty!");
	}

	@Test
	public void testCreateUser() {
		String userPayload = "{ \"name\": \"John Doe\", \"job\": \"developer\" }";
		Response response = RestServiceUtility.postRequest(APIEndpoints.CREATE_USER, userPayload);
		Assert.assertEquals(response.getStatusCode(), 201);
	}

	@Test
	public void testUpdateUserWithPatch() {
		String requestBody = "{ \"job\": \"Senior QA Engineer\" }"; // Partial update
		Response response = RestServiceUtility.patchRequest(APIEndpoints.UPDATE_USER, requestBody);

		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals(response.jsonPath().getString("job"), "Senior QA Engineer");
	}

	@Test
	public void testReplaceUserWithPut() {
		String requestBody = "{ \"name\": \"John Doe\", \"job\": \"Lead Developer\" }"; // Full replacement
		Response response = RestServiceUtility.putRequest(APIEndpoints.UPDATE_USER, requestBody);

		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals(response.jsonPath().getString("name"), "John Doe");
		Assert.assertEquals(response.jsonPath().getString("job"), "Lead Developer");
	}

	@Test
	public void testGraphQLQuery() {
		String query = "{ users { id name email } }";
		Response response = GraphQLUtility.executeQuery(query);
		Assert.assertEquals(response.getStatusCode(), 200);
	}

	@Test
	public void testCreateUserMutation() {
		// Step 1: Set dynamic variables for mutation
		Map<String, Object> variables = new HashMap<>();
		variables.put("name", "John Doe");
		variables.put("job", "Software Engineer");

		// Step 2: Call the GraphQL Mutation
		Response response = GraphQLUtility.sendGraphQLMutation("createUserMutation.graphql", variables);

		// Step 3: Extract Response Data
		String id = response.jsonPath().getString("data.createUser.id");
		String name = response.jsonPath().getString("data.createUser.name");
		String job = response.jsonPath().getString("data.createUser.job");
		String createdAt = response.jsonPath().getString("data.createUser.createdAt");

		// Step 4: Assertions
		Assert.assertNotNull(id, "User ID should not be null");
		Assert.assertEquals(name, "John Doe", "User name did not match expected value!");
		Assert.assertEquals(job, "Software Engineer", "User job did not match expected value!");
		Assert.assertNotNull(createdAt, "CreatedAt timestamp should not be null");

		System.out.println("User Created: " + name + " - " + job);
	}
}