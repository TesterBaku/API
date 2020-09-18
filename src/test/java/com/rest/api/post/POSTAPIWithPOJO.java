package com.rest.api.post;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class POSTAPIWithPOJO {
	
	//create a user
	//POST - URL
	//Request JSON body
	//User Java Class (POJO) ---> JSON Object
	//Encapsulation (Hiding the data members) --> private variables ---> public (getter and setter methods)
	//POJO = Plain Old Java Object  -- Java Class --> private variables ---> public (getter and setter methods)
	
	
	@Test
	public void createUser_With_Pojo_Test() {
		
		User user = new User("Christina", "John", "female", "01-01-1990", 
				"tom123456@gmail.com", "+1-999-999-9999", "http://www.naveenautomationlabs.com",
				"123 New Avenue, SFO, CA", "active");
		
		//convert Pojo to JSON --> Serialization  ---> Jackson API (add maven dependency)
		
		ObjectMapper mapper = new ObjectMapper();
		
		String userJson = null;
		try {
			userJson = mapper.writeValueAsString(user);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		System.out.println(userJson);
		
		RestAssured.baseURI = ("https://gorest.co.in/");
		
		given().log().all()
		  .contentType(ContentType.JSON)
		  .header("Authorization", "Bearer D9KImrSgh-1iRZ12dFFRfolgA3b05iIrtGWV")
		  .body(userJson)
		.when().log().all()
			.post("public-api/users")
		.then().log().all()
			.assertThat()
				.contentType(ContentType.JSON)
			.and()
				.body("result.first_name", equalTo(user.getFirst_name()));
		
	
		
	}
	
	

}
