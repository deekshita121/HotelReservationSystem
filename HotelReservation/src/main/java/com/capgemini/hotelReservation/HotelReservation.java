package com.capgemini.hotelReservation;

import java.util.ArrayList;
import java.util.Scanner;

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

		Hotel h1 = new Hotel("Lakewood", 110);
		Hotel h2 = new Hotel("Brigewood", 160);
		Hotel h3 = new Hotel("Ridgewood", 220);

		hotelList.add(h1);
		hotelList.add(h2);
		hotelList.add(h3);
	}

	/**
	 * 
	 */
	public void cheapHotel() {
		log.info("Enter dates in the format with a comma (dd mmm yyy) ");
		String date = sc.nextLine();
		String days[] = date.split(",");
		int minCost = hotelList.get(0).getRate();
		String cheapHotelName = hotelList.get(0).getName();
		for (int i = 1; i < hotelList.size(); i++)
			if (hotelList.get(i).getRate() < minCost) {
				minCost = hotelList.get(i).getRate();
				cheapHotelName = hotelList.get(i).getName();
			}
		log.info(cheapHotelName + ", Total Rates: $" + minCost);

	}

	public static void main(String[] args) {
		HotelReservation hotelReservation = new HotelReservation();

		hotelReservation.addHotels();
		hotelReservation.hotelList.forEach(n -> log.info(n));
		hotelReservation.cheapHotel();

	}
}
