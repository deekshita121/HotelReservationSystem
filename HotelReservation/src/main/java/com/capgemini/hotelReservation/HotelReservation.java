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

		Hotel h1 = new Hotel("Lakewood", 110, 90, 3);
		Hotel h2 = new Hotel("Brigewood", 150, 50, 4);
		Hotel h3 = new Hotel("Ridgewood", 220, 150, 5);

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
		long noOfWeekends = noOfDays - (noOfWeekdays);
		
		long minCost = hotelList.get(0).getWeekdayRate() * noOfWeekdays
				+ hotelList.get(0).getWeekendRate() * noOfWeekends;
		String cheapHotel = hotelList.get(0).getName();

		for (int i = 1; i < hotelList.size(); i++) {
			if (hotelList.get(i).getWeekdayRate() * noOfWeekdays
					+ hotelList.get(i).getWeekendRate() * noOfWeekends < minCost) {
				minCost = hotelList.get(i).getWeekdayRate() * noOfWeekdays
						+ hotelList.get(i).getWeekendRate() * noOfWeekends;
				cheapHotel = hotelList.get(i).getName();
			}
		}

		log.info(cheapHotel + ", Total rate :$" + minCost);

	}

	public static void main(String[] args) {
		HotelReservation hotelReservation = new HotelReservation();

		hotelReservation.addHotels();
		hotelReservation.hotelList.forEach(n -> log.info(n));
		hotelReservation.cheapHotel();

	}
}
