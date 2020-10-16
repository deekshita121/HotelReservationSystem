package com.capgemini.hotelReservation;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HotelReservation {
	private static final Logger log = LogManager.getLogger(HotelReservation.class);
	static ArrayList<Hotel> hotelList = new ArrayList<Hotel>();

	public static void main(String[] args) {
		HotelReservation hotelReservation = new HotelReservation();

		Hotel h1 = new Hotel("Lakewood", 110);
		Hotel h2 = new Hotel("Brigewood", 160);
		Hotel h3 = new Hotel("Ridgewood", 220);

		hotelList.add(h1);
		hotelList.add(h2);
		hotelList.add(h3);
		hotelReservation.hotelList.forEach(n -> log.info(n));

	}
}
