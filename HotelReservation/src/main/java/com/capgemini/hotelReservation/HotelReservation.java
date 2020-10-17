package com.capgemini.hotelReservation;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HotelReservation {
	private static final Logger log = LogManager.getLogger(HotelReservation.class);
	private static Scanner sc = new Scanner(System.in);
	static ArrayList<Hotel> hotelList = new ArrayList<Hotel>();

	/**
	 * 
	 */
	public void addHotels() {

		Hotel h1 = new Hotel("Lakewood", 110, 90, 0);
		Hotel h2 = new Hotel("Brigewood", 150, 50, 0);
		Hotel h3 = new Hotel("Ridgewood", 220, 150, 0);

		hotelList.add(h1);
		hotelList.add(h2);
		hotelList.add(h3);
	}

	/**
	 * 
	 */
	public void cheapHotel() {

		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd MM yyyy");
		log.info("Enter start date (dd MM yyyy): ");
		String start = sc.nextLine();
		log.info("Enter end date (dd MM yyyy): ");
		String end = sc.nextLine();
		LocalDate startDate = LocalDate.parse(start, dateFormat);
		LocalDate endDate = LocalDate.parse(end, dateFormat);
		int noOfDays = (int) ChronoUnit.DAYS.between(startDate, endDate);
		DayOfWeek startDay = startDate.getDayOfWeek();
		DayOfWeek endDay = endDate.getDayOfWeek();
		long daysWithoutWeekends = noOfDays - 2 * ((noOfDays + startDay.getValue()) / 7);
		long noOfWeekdays = daysWithoutWeekends + (startDay.getValue() == 1 ? 1 : 0) + (endDay.getValue() == 1 ? 1 : 0);
		long noOfWeekends = noOfDays - (noOfWeekdays)+1;
		
		for (Hotel hotel : hotelList) {
			long totalRate = noOfWeekdays * hotel.getWeekdayRate()
					+ noOfWeekends * hotel.getWeekendRate();
			hotel.setTotalRate(totalRate);
			log.info("Total Rate=" + totalRate);
		}
		
		List<Hotel> sortedHotelList =  hotelList.stream().sorted(Comparator.comparing(Hotel::getTotalRate)).collect(Collectors.toList());
		long minCost = sortedHotelList.get(0).getTotalRate();
		String cheapHotel = sortedHotelList.get(0).getName();
		if(sortedHotelList.get(1).getTotalRate()==minCost) {
			log.info(sortedHotelList.get(1).getName()+", TotalRate = $"+minCost);
		
		}
		log.info(cheapHotel+", TotalRate = $"+minCost);
	}

	public static void main(String[] args) {
		HotelReservation hotelReservation = new HotelReservation();

		hotelReservation.addHotels();
		hotelReservation.hotelList.forEach(n -> log.info(n));
		hotelReservation.cheapHotel();

	}
}
