package com.herokuapp.restfulbooker;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class GetBookingIdsTest extends BaseTest {

	// restful-booker.herokuapp.com/apidoc/index.html#api-Booking-GetBookings
	@Test
	public void getBookingIdsWithoutFilterTest() {
		// get response with booking ids
		Response response = RestAssured.given(spec).get("/booking");
		response.prettyPrint();
		// Verify the response code is 200
		Assert.assertEquals(response.getStatusCode(), 200, "Response code should be 200 but it's not");
		// Verify that response body contains at least one booking
		List<Integer> bookingIds = response.jsonPath().getList("bookingid");
		Assert.assertFalse(bookingIds.isEmpty(), "List of bookings is empty but it shouldn't be");
		for (var item : bookingIds) {
			System.out.print(item + " ");
		}
	}

	@Test
	public void getBookingIdsWithFilterTest() {
		// Add query parameter to spec
		spec.queryParam("firstname", "Jim");
		spec.queryParam("lastname", "Smith");
		
		// get response with booking ids
		Response response = RestAssured.given(spec).get("/booking");
		response.prettyPrint();
		// Verify the response code is 200
		Assert.assertEquals(response.getStatusCode(), 200, "Response code should be 200 but it's not");
		// Verify that response body contains at least one booking
		List<Integer> bookingIds = response.jsonPath().getList("bookingid");
		Assert.assertFalse(bookingIds.isEmpty(), "List of bookings is empty but it shouldn't be");
		for (var item : bookingIds) {
			System.out.print(item + " ");
		}
		System.out.println("");
	}

}
