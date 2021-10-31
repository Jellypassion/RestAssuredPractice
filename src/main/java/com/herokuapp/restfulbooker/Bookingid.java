package com.herokuapp.restfulbooker;

public class Bookingid {
	/*
	 * { "bookingid": 1, "booking": { "firstname": "Jim", "lastname": "Brown",
	 * "totalprice": 111, "depositpaid": true, "bookingdates": { "checkin":
	 * "2018-01-01", "checkout": "2019-01-01" }, "additionalneeds": "Breakfast" } }
	 */

	private int bookingid;
	private Booking booking;

	public Bookingid(int bookingid, Booking booking) {
		this.bookingid = bookingid;
		this.booking = booking;
	}

	// Generate an empty constructor
	public Bookingid() {
	}

	@Override
	public String toString() {
		return "Bookingid [bookingid=" + bookingid + ", booking=" + booking + "]";
	}

	public int getBookingid() {
		return bookingid;
	}

	public void setBookingid(int bookingid) {
		this.bookingid = bookingid;
	}

	public Booking getBooking() {
		return booking;
	}

	public void setBooking(Booking booking) {
		this.booking = booking;
	}

}
