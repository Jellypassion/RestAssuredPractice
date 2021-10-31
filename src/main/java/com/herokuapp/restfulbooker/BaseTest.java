package com.herokuapp.restfulbooker;

import org.json.JSONObject;
import org.testng.annotations.BeforeMethod;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BaseTest {
	
	public static final String RESTFULBOOKER_URI = "https://restful-booker.herokuapp.com";
	protected RequestSpecification spec;

	@BeforeMethod
	public void setUp() {
		spec = new RequestSpecBuilder()
				.setBaseUri(RESTFULBOOKER_URI)
				.build();
	}
	
	public Response createBooking() {
		JSONObject body = new JSONObject();
		body.put("firstname", "Eugene");
		body.put("lastname", "Bezsrochnyi");
		body.put("totalprice", 150);
		body.put("depositpaid", false);
		
		//"bookingdates" should be added as a separate JSON object
		JSONObject bookingDates = new JSONObject();
		bookingDates.put("checkin", "2021-08-11");
		bookingDates.put("checkout", "2021-08-18");
		
		body.put("bookingdates", bookingDates);
		body.put("additionalneeds", "Bitches");
		//Get Response
		Response response = RestAssured.given(spec).contentType(ContentType.JSON)
				.body(body.toString()).post("/booking");
		return response;
	}
	
}
