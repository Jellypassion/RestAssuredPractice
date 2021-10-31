package com.herokuapp.restfulbooker;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UpdateBookingTests extends BaseTest {

	@Test
	public void updateBookingTest() {
		// Create a booking
		Response responseToPost = createBooking();
		responseToPost.print();
		// get the id of creted booking
		var bookingid = responseToPost.jsonPath().getInt("bookingid");
		// Update Booking
		JSONObject body = new JSONObject();
		body.put("firstname", "Ivan");
		body.put("lastname", "Petrov");
		body.put("totalprice", 20);
		body.put("depositpaid", true);
		// "bookingdates" should be added as a separate JSON object
		JSONObject bookingDates = new JSONObject();
		bookingDates.put("checkin", "2021-08-20");
		bookingDates.put("checkout", "2021-08-27");
		body.put("bookingdates", bookingDates);
		body.put("additionalneeds", "");
		//var url = "https://restful-booker.herokuapp.com/booking/";
		//var putUri = url + bookingid;
		// Auth credentials:
		// "username" : "admin",
		// "password" : "password123"

		// Get Response (also auth methods should be added)
		Response responseToPut = RestAssured.given(spec)
				.auth()
				.preemptive()
				.basic("admin", "password123")
				.contentType(ContentType.JSON)
				.body(body.toString())
				.put("/booking/" + bookingid);
		responseToPut.prettyPrint();
		// Verifications
		Assert.assertEquals(responseToPut.getStatusCode(), 200, "Response code should be 200 but it's not");
		// Verify that correct info returned in response (verifications are similar to
		// "getBookingByIdTest()")
		SoftAssert softAssert = new SoftAssert();

		var firstName = responseToPut.jsonPath().getString("firstname");
		softAssert.assertEquals(firstName, "Ivan", "FirstName does not match");

		var lastName = responseToPut.jsonPath().getString("lastname");
		softAssert.assertEquals(lastName, "Petrov", "LastName does not match");

		int totalPrice = responseToPut.jsonPath().getInt("totalprice");
		softAssert.assertEquals(totalPrice, 20, "Price does not match");

		var isDepositPaid = responseToPut.jsonPath().getBoolean("depositpaid");
		softAssert.assertTrue(isDepositPaid, "DepositPaid should be true but it's false");

		var checkin = responseToPut.jsonPath().getString("bookingdates.checkin");
		softAssert.assertEquals(checkin, "2021-08-20", "Checkin date doesn't match");

		var checkout = responseToPut.jsonPath().getString("bookingdates.checkout");
		softAssert.assertEquals(checkout, "2021-08-27", "Checkout date doesn't match");

		var additionalneeds = responseToPut.jsonPath().getString("additionalneeds");
		softAssert.assertEquals(additionalneeds, "", "Additional Needs do not match");

		softAssert.assertAll();

	}

}
