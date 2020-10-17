package com.capgemini.hotelReservation;

public class Hotel {

	private String name;
	private int weekdayRate;
	private int weekendRate;
	private int rate;
	private long totalRate;

	public Hotel(String name, int weekdayRate, int weekendRate, int rate, long totalRate) {
		super();
		this.name = name;
		this.weekdayRate = weekdayRate;
		this.weekendRate = weekendRate;
		this.rate = rate;
		this.totalRate = totalRate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getWeekdayRate() {
		return weekdayRate;
	}

	public void setWeekdayRate(int weekdayRate) {
		this.weekdayRate = weekdayRate;
	}
	
	public int getWeekendRate() {
		return weekendRate;
	}

	public void setWeekendRate(int weekendRate) {
		this.weekendRate = weekendRate;
	}
	
	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}
	
	public long getTotalRate() {
		return totalRate;
	}

	public void setTotalRate(long totalRate) {
		this.totalRate = totalRate;
	}
	
	@Override
	public String toString() {
		return "Hotel : \nName = " + name + "\nWeekday Rate = $" + weekdayRate + "\nWeekend Rate = $" + weekendRate +"\nRate = " + rate +"\n";
	}
}
