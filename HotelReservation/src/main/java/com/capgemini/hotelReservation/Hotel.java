package com.capgemini.hotelReservation;

public class Hotel {

	private String name;
	private int weekdayRate;
	private int weekendRate;
	private int rewardsWeekdayRate;
	private int rewardsWeekendRate;
	private long totalRate;
	private int rate;

	public Hotel(String name, int weekdayRate, int weekendRate, int rewardsWeekdayRate, int rewardsWeekendRate, int rate, long totalRate) {
		super();
		this.name = name;
		this.weekdayRate = weekdayRate;
		this.weekendRate = weekendRate;
		this.rewardsWeekdayRate = rewardsWeekdayRate;
		this.rewardsWeekendRate = rewardsWeekendRate;
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
	
	public int getRewardsWeekdayRate() {
		return rewardsWeekdayRate;
	}

	public void setRewardsWeekdayRate(int rewardsWeekdayRate) {
		this.rewardsWeekdayRate = rewardsWeekdayRate;
	}
	
	public int getRewardsWeekendRate() {
		return rewardsWeekendRate;
	}

	public void setRewardsWeekendRate(int rewardsWeekendRate) {
		this.rewardsWeekendRate = rewardsWeekendRate;
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
		return "Hotel : \nName = " + name + "\nWeekday Rate = $" + weekdayRate + "\nWeekend Rate = $" + weekendRate + "\nWeekday Rate for rewards customer = $" + rewardsWeekdayRate + "\nWeekend Rate for rewards customer = $" + rewardsWeekendRate +"\nRate = " + rate +"\n";
	}
}
