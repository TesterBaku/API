package com.rest.api.get;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import xmlUtil.XmlParser;


public class GETBDDAPI {
	
	//Rest Assured BDD:
/*	given()
	when()
	then()
	and()   */
	
//	@Test
	public void getAPITest_1() {
		
		given().log().all()
		.when().log().all()
		.get("http://ergast.com/api/f1/2017/circuits.json")
		.then().log().all()
			.assertThat()
			.body("MRData.CircuitTable.Circuits.curcuitId", hasSize(20));
		
		
	}
	
//	@Test
	public void getAPICircuitTest_2() {
		
		Response response =
		given().log().all()
		.when().log().all()
		.get("http://ergast.com/api/f1/2017/circuits.json");
		
		int statusCode = response.getStatusCode();
		System.out.println("api response status code is " + statusCode);
		Assert.assertEquals(statusCode, 200);
		
		System.out.println(response.prettyPrint());
	}
	
//	@Test
	public void getAPICircuitTest_content_length() {
		
		RestAssured.baseURI = "http://ergast.com";
		given().log().all()
		.when().log().all()
			.get("/api/f1/2017/circuits.json")
		.then().log().all()
			.assertThat()
				.statusCode(200)
		.and()
			.contentType(ContentType.JSON)
		.and()
		    .header("Content-Length", equalTo("4551"));
		
				
	}
	
//	@Test  //This test is not working because I get 503 code
	public void getJsonAPI_VerifyMDValue() {
		
		String paramValue = "test";
		String expectedMd5value = "asdasdasdasdasdasdas";
		
		
		given().log().all()
			.param("text", paramValue)
		.when().log().all()
			.get("https://md5.jsontest.com")
		.then().log().all()    //then() is a separator between request and response
			.assertThat()
				.body("md5", equalTo("expectedMd5value"));
		
	}
	
	//2017 -- 20
	//2016 -- 21
	//1966 -- 9
	
	@DataProvider(name="getCurcuitYearData")
	public Object[][] getCircuitYearInfo() {
		
		
	return	new Object[][] {
			{"2017", 20},
			{"2016", 21},
			{"1966", 9}	
		};
	}
	
	
//	@Test(dataProvider="getCurcuitYearData")
	public void numberOfCircuitsYearTest(String seasonYear, int circuitNumber) {
		
		given().log().all()
			.pathParam("raceSeason", seasonYear)
		.when()
			.get("http://ergast.com/api/f1/{raceSeason}/circuits.json")
		.then().log().all()
			.assertThat()
				.body("MRData.CircuitTable.Circuits.circuitId", hasSize(circuitNumber));
		//when collection is expected we use hasSize(), otherwise we use equalTo
					
	}
	
	@Test
	public void getUserResponseXml_Test() {
		
		RestAssured.baseURI = "https://gorest.co.in";
		
	Response response =	given().log().all()
			.contentType("application/json")
			.header("Authorization", "Bearer D9KImrSgh-1iRZ12dFFRfolgA3b05iIrtGWV")
			.header("Accept", "application/xml")
		.when().log().all()
			.get("/public-api/users?last_name=Kuhn&first_name=Brenna");
		
	System.out.println(response.getStatusCode());
//	System.out.println(response.prettyPrint());
//	
    //getting xml values using XmlPath from Rest Assured:
	
//	XmlPath xmlPath = response.xmlPath();
//	
//	String successValue = xmlPath.get("response._meta.success");
//	System.out.println(successValue);
//	
//	Assert.assertEquals(successValue, "true");
	
	String responseXmlString = response.prettyPrint();
	XmlParser xp = new XmlParser(responseXmlString);
	
	String value = xp.getTextContent("//response//_meta/success");
	System.out.println(value);
	
	Assert.assertEquals(value, "true");
	
	}
	
	
	
	
	
	
	
	
	
	
	

}
