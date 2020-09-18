package com.rest.api.get;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import jdk.nashorn.internal.scripts.JS;

public class GET_Non_BDD_API_Test {

	//prepare the request
	//hit the API
	//get the response
	//fetch the values from response
	
	
	
//	@Test
	public void getUser_Non_BDD_Test() {
		
		RestAssured.baseURI = "https://gorest.co.in";
		RequestSpecification request = RestAssured.given();
		
		request.header("Authorization", "Bearer D9KImrSgh-1iRZ12dFFRfolgA3b05iIrtGWV");
		
		Response response = request.get("/public-api/users");
		
		System.out.println(response.prettyPrint());
		System.out.println(response.getStatusCode());
		System.out.println(response.getHeader("Server"));
		
	}
	
//	@Test
	public void getUser_Non_BDD_WithQueryParams_Test() {
		
		RestAssured.baseURI = "https://gorest.co.in";
		RequestSpecification request = RestAssured.given();
		
		request.queryParam("first_name", "John");
		request.queryParam("gender", "male");
		
		request.header("Authorization", "Bearer D9KImrSgh-1iRZ12dFFRfolgA3b05iIrtGWV");
		
		Response response = request.get("/public-api/users");
		
		System.out.println(response.prettyPrint());
		System.out.println(response.getStatusCode());
		System.out.println(response.getHeader("Server"));
		
	}
	
	
	
	
	@Test
	public void getUser_Non_BDD_HasMap_QueryParams_Test() {
		
		RestAssured.baseURI = "https://gorest.co.in";
		RequestSpecification request = RestAssured.given();
		request.header("Authorization", "Bearer D9KImrSgh-1iRZ12dFFRfolgA3b05iIrtGWV");
		
		//we can store null values and keys in HashMap. HashMap is non-synchronized ---> not thread safe
		Map<String, String> params = new HashMap<String, String>();
		
		params.put("first_name", "John");
		params.put("gender", "male");
		
		request.queryParams(params);
		
		Response response = request.get("/public-api/users");
		
		System.out.println(response.prettyPrint());
		System.out.println(response.getStatusCode());
		System.out.println(response.getHeader("Server"));
		
		JsonPath js = response.jsonPath();
		System.out.println(js.getString("_meta.success"));
		Assert.assertEquals(js.getString("_meta.success"), true);
		
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals(response.getHeader("Server"), "nginx");
		
	}
	
	
	
	
	
	
	
	
}
