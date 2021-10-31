package com.herokuapp.restfulbooker;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PartialUpdateBookingTests extends BaseTest {

	@Test
	public void partialUpdateBookingTest() {

		// Create a booking
		Response responseToPost = createBooking();
		responseToPost.prettyPrint();
		// get the id of created booking
		var bookingid = responseToPost.jsonPath().getInt("bookingid");
		System.out.printf("Your booking id is: %d %n %nUpdated booking is:%n", bookingid);
		// Modify first name and checkin date
		JSONObject patchBody = new JSONObject();
		patchBody.put("firstname", "Ievgen");
		patchBody.put("bookingdates.checkin", "2021-08-13");
		//var url = "https://restful-booker.herokuapp.com/booking/";
		//var patchUri = url + bookingid;
		Response responseToPatch = RestAssured.given(spec)
				.auth()
				.preemptive()
				.basic("admin", "password123")
				.contentType(ContentType.JSON)
				.body(patchBody.toString())
				.patch("/booking/" + bookingid);
		// Verify changes
		responseToPatch.prettyPrint();
		// Verifications
		Assert.assertEquals(responseToPatch.getStatusCode(), 200, "Response code should be 200 but it's not");
		SoftAssert softAssert = new SoftAssert();

		var firstname = responseToPatch.jsonPath().getString("firstname");
		softAssert.assertEquals(firstname, "Ievgen", "FirstName does not match");

		var lastName = responseToPatch.jsonPath().getString("lastname");
		softAssert.assertEquals(lastName, "Bezsrochnyi", "LastName does not match");

		int totalPrice = responseToPatch.jsonPath().getInt("totalprice");
		softAssert.assertEquals(totalPrice, 150, "Price does not match");

		var isDepositPaid = responseToPatch.jsonPath().getBoolean("depositpaid");
		softAssert.assertFalse(isDepositPaid, "DepositPaid should be false but it's not");

		var checkin = responseToPatch.jsonPath().getString("bookingdates.checkin");
		softAssert.assertEquals(checkin, "2021-08-13", "Checkin date doesn't match");

		var checkout = responseToPatch.jsonPath().getString("bookingdates.checkout");
		softAssert.assertEquals(checkout, "2021-08-18", "Checkout date doesn't match");

		var additionalneeds = responseToPatch.jsonPath().getString("additionalneeds");
		softAssert.assertEquals(additionalneeds, "Bitches", "Additional Needs do not match");

		softAssert.assertAll();
	}

}
