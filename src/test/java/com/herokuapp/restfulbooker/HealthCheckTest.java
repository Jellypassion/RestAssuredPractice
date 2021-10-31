package com.herokuapp.restfulbooker;

//add this import manually
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Cookie;
import io.restassured.http.Cookies;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class HealthCheckTest extends BaseTest {
	
	@Test
	public void healthCheckTest() {
		
		given()
			.spec(spec);
		when().
			get("/ping").
		then().
			assertThat().statusCode(201);
	}
	
	@Test
	public void headersAndCookiesCheckTest() {
		// Headers can be added into spec
		Header someHeader = new Header("Some name", "Some value");
		spec.header(someHeader);
		//Cookies can be added to spec
		Cookie someCookie = new Cookie.Builder("Some cookie", "Some value").build();
		spec.cookie(someCookie);
		// Also headers/cookies can be added directly into the request
		Response response = RestAssured.given(spec)
				.cookie("Test cookie name","Test cookies value")
				.header("Test header name", "Test header value")
				.log().all()
				.get("/ping");
		
		// Get Headers
		Headers headers = response.getHeaders();
		System.out.println("Headers: " + headers);
		// Get a specific header name and value
		Header serverHeader1 = headers.get("Server");
		System.out.println(serverHeader1.getName() + ": " + serverHeader1.getValue());
		// Get a specific header value
		String serverHeader2 = response.getHeader("Server");
		System.out.println("Server: " + serverHeader2);
		// Get cookies
		Cookies cookies  = response.getDetailedCookies();
		System.out.println("Cookies: " + cookies);
	}

}
 