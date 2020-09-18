package com.rest.api.put;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rest.api.post.User;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class PUTAPITest {
	
	//Create a user with post call
	//user info
	//update the info with put call
	
	//2 types of assertion
	//a. other attributes should remain same
	//b. the attribute which has been changed, needs to be checked
	
	@Test
	public void update_User_PUT_API_Test() {
	//1. Create a POST Request with Payload
	User user = new User("Umang", "Sharma", "male",  "01-12-1998", "umang6@gmail.com", "+91-998989898", "http://www.umang.com", "132 Bangalore strt, Bangalore", "active" );
	
	//Convert this POJO to Json -- using Jackson API -- ObjectMapper
	
	ObjectMapper mapper = new ObjectMapper();
	
	String userJson = null;
	
	try {
		userJson = mapper.writeValueAsString(user);
	} catch (JsonProcessingException e) {
		e.printStackTrace();
	}
	
	System.out.println("POST call JSON is: " + userJson);
	
	//write POST Call:
	RestAssured.baseURI = "https://gorest.co.in/";
	
	String userID = given().log().all()
		.contentType(ContentType.JSON)
		.header("Authorization", "Bearer D9KImrSgh-1iRZ12dFFRfolgA3b05iIrtGWV")
		.body(userJson)
	.when().log().all()
		.post("public-api/users")
	.then().log().all()
		.assertThat()
			.contentType(ContentType.JSON)
			.extract().path("result.id");
	
	System.out.println(userID);
	
	
	//Call the PUT API:
	user.setEmail("umang.sharma9@gmail.com");
	user.setAddress("Haydar, Pune");
	user.setPhone("+91-67676767676");
	
String updatedUserJson = null;
	
	try {
		updatedUserJson = mapper.writeValueAsString(user);
	} catch (JsonProcessingException e) {
		e.printStackTrace();
	}
	
	given().log().all()
		.contentType(ContentType.JSON)
		.header("Authorization", "Bearer D9KImrSgh-1iRZ12dFFRfolgA3b05iIrtGWV")
		.body(updatedUserJson)
	.when().log().all()
		.put("public-api/users/" + userID)
	.then().log().all()
		.assertThat()
				.contentType(ContentType.JSON)
			.and()
				.body("result.email", equalTo(user.getEmail()))
			.and()
				.body("result.id", equalTo(userID))
			.and()
				.body("result.first_name", equalTo(user.getFirst_name()));
			
	
	
	given().log().all()
		.contentType(ContentType.JSON)
		.header("Authorization", "Bearer D9KImrSgh-1iRZ12dFFRfolgA3b05iIrtGWV")
	.when().log().all()
		.get("public-api/users/" + userID)
	.then().log().all()
		.assertThat()
			.statusCode(200)
			.and()
			.contentType(ContentType.JSON)
			.and()
			.body("result.first_name", equalTo(user.getFirst_name()))
			.and()
			.body("result.email", equalTo(user.getEmail()));
		
	//post --> get
	//post --> put --> get
	
	//have to automate mail address change so the test doesn't fail because of that
	
	
	
	}

}
