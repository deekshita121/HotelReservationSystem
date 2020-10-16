package com.capgemini.hotelReservation;

public class Hotel {

	private String name;
	private int rate;

	public Hotel(String name, int rate) {
		super();
		this.name = name;
		this.rate = rate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}

	@Override
	public String toString() {
		return "Hotel : \nName = " + name + "\nRate = $" + rate + "\n";
	}
}
