package com.rest.api.delete;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rest.api.post.User;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;



public class DeleteAPITest {

	
	@Test
	public void delet_User_API_Test() {
		
		//post --> delete --->get
		//have to create post call to create the user first --> so have to add it 
		
		RestAssured.baseURI = "https://gorest.co.in/";	
		
		User user = new User("Ahmed", "Ahmedov", "male",  "09-11-1999", "ahmed@gmail.com", "+91-2020002020", "http://www.ahmed.com", "132 Bangalore strt, Haydarabad", "active" );
		
		
		ObjectMapper mapper = new ObjectMapper();
			
		
		String userJson = null;
		
		try {
			userJson = mapper.writeValueAsString(user);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		String userID = given().log().all()
			.contentType(ContentType.JSON)
			.header("Authorization", "Bearer D9KImrSgh-1iRZ12dFFRfolgA3b05iIrtGWV")
			.body(userJson)
		.when().log().all()
			.post("public-api/users")
		.then().log().all()
			.assertThat()
				.contentType(ContentType.JSON)
				.and()
				.extract().path("result.id");
		
		given().log().all()
			.header("Authorization", "Bearer D9KImrSgh-1iRZ12dFFRfolgA3b05iIrtGWV")
		.when().log().all()
			.delete("public-api/users/" + userID)  
		.then().log().all()
			.assertThat()
				.contentType(ContentType.JSON)
				.and()
				.body("result", equalTo(null));
	
		given().log().all()
			.contentType(ContentType.JSON)
			.header("Authorization", "Bearer D9KImrSgh-1iRZ12dFFRfolgA3b05iIrtGWV")
		.when().log().all()
			.get("public-api/users/" + userID)
		.then().log().all()
			.assertThat()
				.contentType(ContentType.JSON)
				.and()
				.body("result.name", equalTo("Not Found"))
				.and()
				.body("result.status", equalTo(404));
		
				
				

	
	
	
	
	
	
	}
	
	
	
	
	
}
