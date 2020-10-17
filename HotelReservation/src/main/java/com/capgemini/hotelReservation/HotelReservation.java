package com.capgemini.hotelReservation;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HotelReservation {
	private static final Logger log = LogManager.getLogger(HotelReservation.class);
	private static Scanner sc = new Scanner(System.in);
	static ArrayList<Hotel> hotelList = new ArrayList<Hotel>();
	Map<Hotel, Integer> hotelRateMap = new HashMap<>();
	/**
	 * 
	 */
	public void addHotels() {

		Hotel h1 = new Hotel("Lakewood", 110, 90, 3, 0);
		Hotel h2 = new Hotel("Brigewood", 150, 50, 4, 0);
		Hotel h3 = new Hotel("Ridgewood", 220, 150, 5, 0);

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
	//	long startDayValue = startDay.getValue();
	//	long endDayValue = endDay.getValue();
		
		long daysWithoutWeekends = noOfDays - 2 * ((noOfDays + startDay.getValue()) / 7);
		long noOfWeekdays = daysWithoutWeekends + (startDay.equals("SUNDAY") ? 1 : 0) + (endDay.equals("SUNDAY") ? 1 : 0);
		long noOfWeekends = noOfDays - (noOfWeekdays) +1;
		log.info("num of weekdays "+noOfWeekdays+" no of weekends "+noOfWeekends);
		
		for (Hotel hotel : hotelList) {
			long totalRate = noOfWeekdays * hotel.getWeekdayRate()
					+ noOfWeekends * hotel.getWeekendRate();
			hotel.setTotalRate(totalRate);
			log.info("Total Rate=" + totalRate);
		}
		
		List<Hotel> sortedHotelList =  hotelList.stream().sorted(Comparator.comparing(Hotel::getTotalRate)).collect(Collectors.toList());
		long minCost = sortedHotelList.get(0).getTotalRate();
		String cheapHotel = sortedHotelList.get(0).getName();
		for (Hotel hotel : sortedHotelList) {
			if(hotel.getTotalRate()==minCost) {
				if(hotel.getRate()< sortedHotelList.get(0).getRate()) {
					cheapHotel = hotel.getName();
				}
			}
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
