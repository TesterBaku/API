package com.rest.api.authentications;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;



public class OAuth2APITest {
	
//	@Test
	public void checkOAuth2_APITest() {
		
		RequestSpecification request = 
			RestAssured.
			given()
				.auth()
					.oauth2("971770896ff8e5931c1f0f9abd61e086ca07bd91");
		
		Response response = request.post("http://coop.apps.symfonycasts.com/api/1100/chickens-feed");
		
		System.out.println(response.getStatusCode());
		System.out.println(response.prettyPrint());
		
	}
	
	
	
	//1. Generate a token at the runtime by using token API
	//2. String tokenID from the response
	//3. hit the next api with this tokenID
	
	@Test     //for POST call
	public void getAuth2TokenAPITest() {
		
		RequestSpecification request = 
				RestAssured.given()
		           .formParam("client_id", "NaveenAPICourse")
		           .formParam("client_secret", "9d173b617a3bf7de5c0aa27cde52a1a3")
		           .formParam("grant_type", "client_credentials");
		
		Response response = request.post("http://coop.apps.symfonycasts.com/token");
		
		System.out.println(response.getStatusCode());
		System.out.println(response.prettyPrint());
		
		String tokenID = response.jsonPath().getString("access_token");
		System.out.println("API token id is: " + tokenID);
		
		RequestSpecification request1 = 
				RestAssured.
				given()
					.auth()
						.oauth2(tokenID);
			
			Response response1 = request.post("http://coop.apps.symfonycasts.com/api/1100/chickens-feed");
			
			System.out.println(response1.getStatusCode());
			System.out.println(response1.prettyPrint());
	}
	
	
	
	
	
	
	
	
	
	

}
