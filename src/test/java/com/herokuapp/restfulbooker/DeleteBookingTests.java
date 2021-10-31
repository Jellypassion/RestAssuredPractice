package com.herokuapp.restfulbooker;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class DeleteBookingTests extends BaseTest {

	@Test
	public void deleteBookingTest() {
		// Create a booking
		Response responseToPost = createBooking();
		responseToPost.prettyPrint();
		// get the id of created booking
		var bookingid = responseToPost.jsonPath().getInt("bookingid");
		System.out.printf("Your booking id is: %d %n %n", bookingid);
		// Delete the created booking
		//var delUri = "https://restful-booker.herokuapp.com/booking/" + bookingid;
		Response delResponse = RestAssured.given(spec)
				.auth()
				.preemptive()
				.basic("admin", "password123")
				.delete("/booking/" + bookingid);
		System.out.print("Response to DELETE Request: " + delResponse.getStatusCode() + " ");
		delResponse.print();
		// Verify changes
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(delResponse.getStatusCode(), 201, "Response code to DELETE request is incorrect");
		var getReponse = RestAssured.given(spec).get("/booking/" + bookingid);
		softAssert.assertEquals(getReponse.getStatusCode(), 404, "Response code to GET request is incorrect");
		softAssert.assertAll();
		if (getReponse.getStatusCode() == 404)
			System.out.println("Response to GET request: " + getReponse.getStatusCode() + " "
					+ getReponse.getBody().asPrettyString());
	}

}
