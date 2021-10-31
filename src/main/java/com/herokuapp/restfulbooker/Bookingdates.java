package com.herokuapp.restfulbooker;

public class Bookingdates {

	private String checkin;
	private String checkout;

	public Bookingdates(String checkin, String checkout) {
		this.checkin = checkin;
		this.checkout = checkout;
	}
	public Bookingdates() {
	}

	// Generate toString method
	@Override
	public String toString() {
		return "Bookingdates [checkin=" + checkin + ", checkout=" + checkout + "]";
	}

	// Generate getters and setters for all the fields
	public String getCheckin() {
		return checkin;
	}

	public void setCheckin(String checkin) {
		this.checkin = checkin;
	}

	public String getCheckout() {
		return checkout;
	}

	public void setCheckout(String checkout) {
		this.checkout = checkout;
	}

}
