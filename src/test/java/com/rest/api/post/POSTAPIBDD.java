package com.rest.api.post;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class POSTAPIBDD {

	// If you have curl command you can use "Import" --> Raw Text in Postman to
	// convert it to a request

//	@Test
	public void tokenPostBDDAPI_JSONString_Test() {

		RestAssured.baseURI = "https://restful-booker.herokuapp.com";

		given().log().all()
		  .contentType(ContentType.JSON)
		  .body("{\r\n" + "    \"username\" : \"admin\",\r\n" + "    \"password\" : \"password123\"\r\n" + "}")
		.when().log().all()
			 .post("/auth")
		.then().log().all()
			.assertThat()
				 .statusCode(200);

	}

//	@Test
	public void tokenPOSTBDDAPI_File_TEST() {

		RestAssured.baseURI = "https://restful-booker.herokuapp.com";

		String tokenID = given().log().all()
			.contentType(ContentType.JSON)
			.body(new File("C:\\Users\\rufet\\eclipse-workspace\\NaveenAPITraining\\src\\test\\java\\data\\credentials.json"))
		.when().log().all()
		.post("/auth").then().log().all()
			.extract().path("token");
		
		Assert.assertNotNull(tokenID);

	}
	
	@Test
	public void createUser_Post_API_JSONString_Test() {
		
		RestAssured.baseURI = "https://gorest.co.in";
		
		
		given().log().all()
			.contentType(ContentType.JSON)
			.header("Authorization", "Bearer D9KImrSgh-1iRZ12dFFRfolgA3b05iIrtGWV")
			.body("{\r\n" + 
					" 	\"first_name\": \"Rufat\",\r\n" + 
					" 	\"last_name\": \"Malikov\",\r\n" + 
					" 	\"gender\": \"male\",\r\n" + 
					" 	\"dob\": \"1988-09-16\",\r\n" + 
					" 	\"email\": \"testerbaku112@yahoo.com\",\r\n" + 
					" 	\"phone\": \"+95137522026\",\r\n" + 
					" 	\"website\": \"http://www.sauer.biz/\",\r\n" + 
					" 	\"address\": \"Tustin Village Way\\nTustin, IA 35513-1149\",\r\n" + 
					" 	\"status\": \"inactive\",\r\n" + 
					" 	\"_links\": {\r\n" + 
					" 		\"self\": {\r\n" + 
					" 			\"href\": \"https://gorest.co.in/public-api/users/1763\"\r\n" + 
					" 		},\r\n" + 
					" 		\"edit\": {\r\n" + 
					" 			\"href\": \"https://gorest.co.in/public-api/users/1763\"\r\n" + 
					" 		},\r\n" + 
					" 		\"avatar\": {\r\n" + 
					" 			\"href\": \"https://lorempixel.com/250/250/people/?39181\"\r\n" + 
					" 		}\r\n" + 
					" 	}\r\n" + 
					" }")
			.when().log().all()
			    .post("public-api/users")
			.then().log().all()
				.assertThat()
					.body("_meta.success", equalTo(true));
		
		
		
		
		
	}

}
