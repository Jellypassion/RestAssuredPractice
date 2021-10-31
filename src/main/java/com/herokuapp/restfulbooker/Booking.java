package com.herokuapp.restfulbooker;

import org.testng.annotations.Test;

public class Booking {

	/*
	 * Body example: { "firstname" : "Jim", "lastname" : "Brown", "totalprice" :
	 * 111, "depositpaid" : true, "bookingdates" : { "checkin" : "2018-01-01",
	 * "checkout" : "2019-01-01" }, "additionalneeds" : "Breakfast" }
	 */
	// Create variables with the same name as fields in JSON
	private String firstname;
	private String lastname;
	private int totalprice;
	private boolean depositpaid;
	// Since bookingdates is a separate JSON object it should be created as a
	// separate class
	private Bookingdates bookingdates;
	private String additionalneeds;

	// Generate a constructor for all fields using right-click->source->generate constructor
	public Booking(String firstname, String lastname, int totalprice, boolean depositpaid, Bookingdates bookingdates,
			String additionalneeds) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.totalprice = totalprice;
		this.depositpaid = depositpaid;
		this.bookingdates = bookingdates;
		this.additionalneeds = additionalneeds;
	}
	
	//Generate an empty constructor
	public Booking() {
	}

	// Generate toString method
	@Override
	public String toString() {
		return "Booking [firstname=" + firstname + ", lastname=" + lastname + ", totalprice=" + totalprice
				+ ", depositpaid=" + depositpaid + ", bookingdates=" + bookingdates + ", additionalneeds="
				+ additionalneeds + "]";
	}

	// Generate getters and setters for all fields using
	// right-click->source->generate getters and setters
	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public int getTotalprice() {
		return totalprice;
	}

	public void setTotalprice(int totalprice) {
		this.totalprice = totalprice;
	}

	public boolean isDepositpaid() {
		return depositpaid;
	}

	public void setDepositpaid(boolean depositpaid) {
		this.depositpaid = depositpaid;
	}

	public Bookingdates getBookingdates() {
		return bookingdates;
	}

	public void setBookingdates(Bookingdates bookingdates) {
		this.bookingdates = bookingdates;
	}

	public String getAdditionalneeds() {
		return additionalneeds;
	}

	public void setAdditionalneeds(String additionalneeds) {
		this.additionalneeds = additionalneeds;
	}

}
