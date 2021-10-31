package com.herokuapp.restfulbooker;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;

public class GetBookingTests extends BaseTest{

	@Test
	public void getBookingByIdTest() {
		//var url = "https://restful-booker.herokuapp.com/booking/";
		//var bookingId = 1;
		//get the response
		//Response response = RestAssured.get(url + bookingId);
			
		// Create a new booking using POST /createBooking request and get its ID from response
		Response responseFromPost = createBooking();
		var bookingId = responseFromPost.jsonPath().getInt("bookingid");
		// Set path parameter
		spec.pathParam("bookingid", bookingId);
		//get the response to GET /bookingById (booking id is defined by path parameter)
		Response response = RestAssured.given(spec).get("/booking/{bookingid}");
		System.out.println("Your booking ID is: " + bookingId + "\nResponse body:");
		response.prettyPrint();
		//Verify response code is 200
		Assert.assertEquals(response.getStatusCode(), 200, "Response code should be 200 but it's not");
		//Verify that correct info returned for the bookingId
		SoftAssert softAssert = new SoftAssert();
		var firstName = response.jsonPath().getString("firstname");
		softAssert.assertEquals(firstName, "Eugene", "FirstName doesn't match");
		
		var lastName = response.jsonPath().getString("lastname");
		softAssert.assertEquals(lastName, "Bezsrochnyi", "LastName doesn't match");
		
		int totalPrice = response.jsonPath().getInt("totalprice");
		softAssert.assertEquals(totalPrice, 150, "Price doesn't match");
		 
		boolean isDepositPaid = response.jsonPath().getBoolean("depositpaid");
		softAssert.assertTrue(!isDepositPaid, "DepositPaid should be true but it's false");
		
		var checkin = response.jsonPath().getString("bookingdates.checkin");
		softAssert.assertEquals(checkin, "2021-08-11", "Checkin date doesn't match");
		
		var checkout = response.jsonPath().getString("bookingdates.checkout");
		softAssert.assertEquals(checkout, "2021-08-18", "Checkout date doesn't match");
				
		softAssert.assertAll();
				
	}
	
	@Test
	public void getBookingByIdXmlTest() {
		//var url = "https://restful-booker.herokuapp.com/booking/";
		//var bookingId = 1;
		//get the response
		//Response response = RestAssured.get(url + bookingId);
			
		// Create a new booking using POST /createBooking request and get its ID from response
		Response responseFromPost = createBooking();
		var bookingId = responseFromPost.jsonPath().getInt("bookingid");
		// Set path parameter
		spec.pathParam("bookingid", bookingId);
		//Create a heeader to get response in XML
		Header acceptXml = new Header("Accept", "application/xml");
		spec.header(acceptXml);
		//get the XML response to GET /bookingById
		Response response = RestAssured.given(spec).get("/booking/{bookingid}");
		System.out.println("Your booking ID is: " + bookingId + "\nResponse body:");
		response.prettyPrint();
		//Verify response code is 200
		Assert.assertEquals(response.getStatusCode(), 200, "Response code should be 200 but it's not");
		//Verify that correct info returned for the bookingId
		SoftAssert softAssert = new SoftAssert();
		var firstName = response.xmlPath().getString("booking.firstname");
		softAssert.assertEquals(firstName, "Eugene", "FirstName doesn't match");
		
		var lastName = response.xmlPath().getString("booking.lastname");
		softAssert.assertEquals(lastName, "Bezsrochnyi", "LastName doesn't match");
		
		int totalPrice = response.xmlPath().getInt("booking.totalprice");
		softAssert.assertEquals(totalPrice, 150, "Price doesn't match");
		 
		boolean isDepositPaid = response.xmlPath().getBoolean("booking.depositpaid");
		softAssert.assertTrue(!isDepositPaid, "DepositPaid should be true but it's false");
		
		var checkin = response.xmlPath().getString("booking.bookingdates.checkin");
		softAssert.assertEquals(checkin, "2021-08-11", "Checkin date doesn't match");
		
		var checkout = response.xmlPath().getString("booking.bookingdates.checkout");
		softAssert.assertEquals(checkout, "2021-08-18", "Checkout date doesn't match");
				
		softAssert.assertAll();
				
	}
	
}
