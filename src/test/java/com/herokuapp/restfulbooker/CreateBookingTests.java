package com.herokuapp.restfulbooker;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class CreateBookingTests extends BaseTest {

	@Test
	public void createBookingTest() {
		// Create JSON body to send in request
		/*
		 * "firstname" : "Jim", "lastname" : "Brown", "totalprice" : 111, "depositpaid"
		 * : true, "bookingdates" : { "checkin" : "2018-01-01", "checkout" :
		 * "2019-01-01" }, "additionalneeds" : "Breakfast"
		 */
		Response response = createBooking();
		response.print();

		// Verifications
		Assert.assertEquals(response.getStatusCode(), 200, "Response code should be 200 but it's not");
		// Verify that correct info returned in response (verifications are similar to
		// "getBookingByIdTest()")
		SoftAssert softAssert = new SoftAssert();
		var bookingId = response.jsonPath().getInt("bookingid");
		softAssert.assertNotNull(bookingId, "bookingid is NULL");
		softAssert.assertTrue(bookingId > 0, "bookingid number is incorrect");
		System.out.printf("Booking id is: %d\n", bookingId);

		var firstName = response.jsonPath().getString("booking.firstname");
		softAssert.assertEquals(firstName, "Eugene", "FirstName does not match");

		var lastName = response.jsonPath().getString("booking.lastname");
		softAssert.assertEquals(lastName, "Bezsrochnyi", "LastName does not match");

		int totalPrice = response.jsonPath().getInt("booking.totalprice");
		softAssert.assertEquals(totalPrice, 150, "Price does not match");

		var isDepositPaid = response.jsonPath().getBoolean("booking.depositpaid");
		softAssert.assertFalse(isDepositPaid, "DepositPaid should be false but it's true");

		var checkin = response.jsonPath().getString("booking.bookingdates.checkin");
		softAssert.assertEquals(checkin, "2021-08-11", "Checkin date doesn't match");

		var checkout = response.jsonPath().getString("booking.bookingdates.checkout");
		softAssert.assertEquals(checkout, "2021-08-18", "Checkout date doesn't match");

		var additionalneeds = response.jsonPath().getString("booking.additionalneeds");
		softAssert.assertEquals(additionalneeds, "Bitches", "Additional Needs do not match");

		softAssert.assertAll();
	}

	@Test
	public void createBookingWithPOJOTest() {
		// Create body using POJOs
		Bookingdates bookingdates = new Bookingdates("2021-08-11", "2021-08-21");
		Booking booking = new Booking("Denzel", "Dallas", 200, true, bookingdates, "Lots of cocaine");
		//Print booking object
		System.out.println(booking.toString());
		// Get Response
		Response response = RestAssured.given(spec).contentType(ContentType.JSON).body(booking).post("/booking");
		// Verifications
		Assert.assertEquals(response.getStatusCode(), 200, "Response code should be 200 but it's not");
		// Verify that correct info returned in response (verifications are similar to
		// "getBookingByIdTest()")
		SoftAssert softAssert = new SoftAssert();
		var bookingId = response.jsonPath().getInt("bookingid");
		softAssert.assertNotNull(bookingId, "bookingid is NULL");
		softAssert.assertTrue(bookingId > 0, "bookingid number is incorrect");
		System.out.println("Booking id is: " + bookingId);
		System.out.println("Response is:\n" + response.print());
		
		var firstName = response.jsonPath().getString("booking.firstname");
		softAssert.assertEquals(firstName, "Denzel", "FirstName does not match");

		var lastName = response.jsonPath().getString("booking.lastname");
		softAssert.assertEquals(lastName, "Dallas", "LastName does not match");

		int totalPrice = response.jsonPath().getInt("booking.totalprice");
		softAssert.assertEquals(totalPrice, 200, "Price does not match");

		var isDepositPaid = response.jsonPath().getBoolean("booking.depositpaid");
		softAssert.assertTrue(isDepositPaid, "DepositPaid should be true but it's not");

		var checkin = response.jsonPath().getString("booking.bookingdates.checkin");
		softAssert.assertEquals(checkin, "2021-08-11", "Checkin date doesn't match");

		var checkout = response.jsonPath().getString("booking.bookingdates.checkout");
		softAssert.assertEquals(checkout, "2021-08-21", "Checkout date doesn't match");

		var additionalneeds = response.jsonPath().getString("booking.additionalneeds");
		softAssert.assertEquals(additionalneeds, "Lots of cocaine", "Additional Needs do not match");

		softAssert.assertAll();
	}
	
	@Test
	public void createBookingWithPOJODeserializeTest() {
		// Create body using POJOs
		Bookingdates bookingdates = new Bookingdates("2021-08-11", "2021-08-21");
		Booking booking = new Booking("Denzel", "Dallas", 200, true, bookingdates, "Lots of cocaine");
		//Print booking object
		System.out.println(booking.toString());
		// Get Response
		Response response = RestAssured.given(spec).contentType(ContentType.JSON).body(booking).post("/booking");
		// Convert response body as the BookingId class
		Bookingid bookingId = response.as(Bookingid.class);
		// Verifications
		Assert.assertEquals(response.getStatusCode(), 200, "Response code should be 200 but it's not");
		// Verify fields
		System.out.println("Request body : " + booking.toString());
		System.out.println("Response body: " + bookingId.getBooking().toString());
				
		Assert.assertEquals(bookingId.getBooking().toString(), booking.toString());
		
	}

}
