package com.rest.api.authentications;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class OAuth1APISTest {
	
	@Test
	public void TwitterStatusAPI_OAuth1_Test() {
		
		RequestSpecification request = RestAssured.given()
			.auth()
				.oauth("AlqyUxFZDkSrNlUYxdLOLGpvj", 
						"xBqrdhr87LNdj5pLaONnrqe2p1iSFvxizoT7llUAQmjNWzWZhO", 
						"1272413160025124864-kfRNrnVozSF1qgs0tZFkjYkZuXrvYg", 
						"iksuyswhyjLafkf1uzqvmkxHiULqTNn5k60trDi0z2ron");
		
		
		Response response = request.post("https://api.twitter.com/1.1/statuses/update.json?status=hey this is my tweet from API!!!");
		
		System.out.println(response.getStatusCode());
		System.out.println(response.prettyPrint());
		
		
		//OAuth 1 requires Scribe in the classpath --> add Scribe dependency
		//https://github.com/rest-assured/rest-assured/wiki/Usage
	}
	
	
	
	
	
	
	

}
