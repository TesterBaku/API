package com.rest.api.get;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;

public class ResponseSpecBuilderTest {

	
	
	
	//t1 t2 t3 t4 ....... t100
	//Common things:
	//status code
	//content type
	//header
	
	//So we prepare what we expect as a response and use
	//this variable for assertion
		
			ResponseSpecBuilder res = new ResponseSpecBuilder();
			ResponseSpecification resSpec_200_OK = res.expectContentType(ContentType.JSON)
			.expectStatusCode(200)
			.expectHeader("Server", "nginx")
			.build();
		
//			ResponseSpecification resSpec_400_BAD_Request = 
//					res.expectStatusCode(400)
//					.expectHeader("Server", "nginx")
//					.build();
//			
//			ResponseSpecification resSpec_401_AUTH_Fail = 
//					res.expectStatusCode(401)
//					.expectHeader("Server", "nginx")
//					.build();
	
			
			
	
	@Test
	public void ResponseSpecTest() {
		
		RestAssured.baseURI = "https://gorest.co.in";
		
		given()
		.header("Authorization", "Bearer D9KImrSgh-1iRZ12dFFRfolgA3b05iIrtGWV")
		.when()
			.get("/public-api/users")
		.then()
			.assertThat()
				.spec(resSpec_200_OK);
		
	}
	
//	@Test
//	public void ResponseSpec_Auth_Fail_Test() {
//		
//		RestAssured.baseURI = "https://gorest.co.in";
//		
//		given()
//		.header("Authorization", "Bearer D9KImrSgh-1iRZ12dFFRfolgA3b05iIrtGWV11111")
//		.when()
//			.get("/public-api/users")
//		.then()
//			.assertThat()
//				.spec(resSpec_401_AUTH_Fail);
//		
//	}
	
	
	
	
}
