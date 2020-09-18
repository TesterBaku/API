package com.rest.api.authentications;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.authentication.FormAuthConfig;


public class AuthAPIs {
	
	//basic auth:
	//username/password
	
	
	//basic
	//digest
	//form
	//Oauth1
	//Oauth2
	
//	@Test
	public void basic_Auth_WithPreemptive_API_Test() {
		
		given().log().all()
		.auth()
		.preemptive()
			.basic("admin", "admin")
		.when().log().all()
			.get("https://the-internet.herokuapp.com/basic_auth")
		.then().log().all()
			.assertThat()
				.statusCode(200);
	}
		
	
	@Test
	public void basic_Auth_API_Test() {
		
		given().log().all()
		.auth()
			.basic("admin", "admin")
		.when().log().all()
			.get("https://the-internet.herokuapp.com/basic_auth")
		.then().log().all()
			.assertThat()
				.statusCode(200);
	}
	
	//digest authentication is http authentication ---> username and password is hashed(coded) --> this is more secure auth
	@Test
	public void basic_Auth_digest_API_Test() {
		
		given().log().all()
		.auth()
			.digest("admin", "admin")
		.when().log().all()
			.get("https://the-internet.herokuapp.com/basic_auth")
		.then().log().all()
			.assertThat()
				.statusCode(200);
	}
	
	//username and password is taken from some form 
	@Test
	public void basic_Auth_form_API_Test() {
		
		given().log().all()
		.auth()
			.form("admin", "admin", new FormAuthConfig("https://classic.freecrm.com/system/authenticate.cfm", "username", "password"))
		.when().log().all()
			.get("https://classic.freecrm.com/system/authenticate.cfm")
		.then().log().all()
			.assertThat()
				.statusCode(200);
	}
	
	
	
	
	
		//Oauth2.0
	//If you are using:
	//1. with header: append your token with Bearer keyword
	//2. with oauth2() method: No need to add Bearer, just pass the token value
		
//		@Test
		public void OAuth2_API_Test(){
			
			given().log().all()
				.auth()
				    .oauth2("D9KImrSgh-1iRZ12dFFRfolgA3b05iIrtGWV")
			.when().log().all()
				.get("https://gorest.co.in/public-api/users?first_name=Tom")
			.then().log().all()
				.assertThat()
					.statusCode(200);	
		}	
		
		
//		@Test
		public void OAuth_API_Test_With_AuthHeader() {
			
			RestAssured.baseURI = "https://gorest.co.in";
			
			given().log().all()
				.contentType("application/json")
				.header("Authorization", "Bearer D9KImrSgh-1iRZ12dFFRfolgA3b05iIrtGWV")
			.when().log().all()
				.get("/public-api/users?first_name=Tom")
			.then().log().all()
				.statusCode(200)
				.and()
				.header("Server", "nginx");
			
		}
		
//		@Test
		public void OAuth_API_WithTwoQuesryParams_Test() {
			
			
			RestAssured.baseURI = "https://gorest.co.in";
			
			given().log().all()
				.contentType("application/json")
				.header("Authorization", "Bearer D9KImrSgh-1iRZ12dFFRfolgA3b05iIrtGWV")
				.queryParam("first_name", "John")
				.queryParam("gender", "male")
			.when().log().all()
				.get("/public-api/users")
			.then().log().all()
				.statusCode(200)
				.and()
				.header("Server", "nginx");
		}
		
		
		
		
	}

	
	
	
	
	
	

