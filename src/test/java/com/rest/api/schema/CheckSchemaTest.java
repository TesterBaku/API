package com.rest.api.schema;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.File;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;

public class CheckSchemaTest {
	
	//https://www.liquid-technologies.com/online-json-to-schema-converter ---> this is for json to schema converter
	
	@Test
	public void bookings_Schema_Test() {
		
		given().log().all()
			.contentType(ContentType.JSON)
			.body(new File("C:\\Users\\rufet\\eclipse-workspace\\NaveenAPITraining\\src\\test\\java\\data\\bookings.json"))
			.when().log().all()
				.post("https://restful-booker.herokuapp.com/booking")
		.then().log().all()
			.assertThat()
				.statusCode(200)
			.and()	
				.body(matchesJsonSchemaInClasspath("BookingsSchema.json"));
		
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	

}
