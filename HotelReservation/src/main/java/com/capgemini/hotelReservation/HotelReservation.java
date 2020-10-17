package com.capgemini.hotelReservation;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HotelReservation {
	private static final Logger log = LogManager.getLogger(HotelReservation.class);
	private static Scanner sc = new Scanner(System.in);
	private static final String Date_Pattern = ("[0-9]{2}\\s[0-9]{2}\\s[0-9]{4}");
	private static final String Customer_Type = ("[Re]{2}[gw]{1}[ua]{1}[lr]{1}[ad]{1}[rs]{1}");
	static ArrayList<Hotel> hotelList = new ArrayList<Hotel>();

	/**
	 * 
	 */
	public void addHotels() {

		Hotel h1 = new Hotel("Lakewood", 110, 90, 80, 80, 3, 0);
		Hotel h2 = new Hotel("Bridgewood", 150, 50, 110, 50, 4, 0);
		Hotel h3 = new Hotel("Ridgewood", 220, 150, 100, 40, 5, 0);

		hotelList.add(h1);
		hotelList.add(h2);
		hotelList.add(h3);
	}

	/**
	 * @throws InvalidInputException
	 * 
	 */
	public void addTotalRate() throws InvalidInputException {

		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd MM yyyy");
		log.info("Enter start date (dd MM yyyy): ");
		String start = sc.nextLine();
		if (!start.matches(Date_Pattern))
			throw new InvalidInputException("Invalid format");
		log.info("Enter end date (dd MM yyyy): ");
		String end = sc.nextLine();
		if (!end.matches(Date_Pattern))
			throw new InvalidInputException("Invalid format");
		LocalDate startDate = LocalDate.parse(start, dateFormat);
		LocalDate endDate = LocalDate.parse(end, dateFormat);
		int noOfDays = (int) ChronoUnit.DAYS.between(startDate, endDate);
		DayOfWeek startDay = startDate.getDayOfWeek();
		DayOfWeek endDay = endDate.getDayOfWeek();

		long daysWithoutWeekends = noOfDays - 2 * ((noOfDays + startDay.getValue()) / 7);
		long noOfWeekdays = daysWithoutWeekends + (startDay.equals("SUNDAY") ? 1 : 0)
				+ (endDay.equals("SUNDAY") ? 1 : 0);
		long noOfWeekends = noOfDays - (noOfWeekdays) + 1;
		log.info("num of weekdays " + noOfWeekdays + " no of weekends " + noOfWeekends);

		System.out.println("Enter the type of customer Rewards or Regular");
		String typeOfCustomer = sc.nextLine();
		if (!typeOfCustomer.matches(Customer_Type))
			throw new InvalidInputException("Invalid format");
		if (typeOfCustomer.equals("Regular")) {

			for (Hotel hotel : hotelList) {
				long totalRate = noOfWeekdays * hotel.getWeekdayRate() + noOfWeekends * hotel.getWeekendRate();
				hotel.setTotalRate(totalRate);
				log.info("Total Rate=" + totalRate);
			}
		} else {
			for (Hotel hotel : hotelList) {
				long totalRate = noOfWeekdays * hotel.getRewardsWeekdayRate()
						+ noOfWeekends * hotel.getRewardsWeekendRate();
				hotel.setTotalRate(totalRate);
				log.info("Total Rate=" + totalRate);
			}
		}
	}

	/**
	 * 
	 */
	public void cheapHotel() {
		List<Hotel> sortedHotelList = hotelList.stream().sorted(Comparator.comparing(Hotel::getTotalRate))
				.collect(Collectors.toList());
		long minCost = sortedHotelList.get(0).getTotalRate();
		String cheapHotel = sortedHotelList.get(0).getName();
		for (Hotel hotel : sortedHotelList) {
			if (hotel.getTotalRate() == minCost) {
				if (hotel.getRate() < sortedHotelList.get(0).getRate()) {
					cheapHotel = hotel.getName();
				}
			}
		}
		log.info(cheapHotel + ", TotalRate = $" + minCost);
	}

	/**
	 * 
	 */
	public void highRatedHotel() {
		List<Hotel> sortedHotelList = hotelList.stream().sorted(Comparator.comparing(Hotel::getTotalRate))
				.collect(Collectors.toList());
		int count = 0;
		for (Hotel hotel : sortedHotelList) {
			count++;
		}
		count--;
		log.info(sortedHotelList.get(count).getName() + ", TotalRate = $" + sortedHotelList.get(count).getRate());
	}

	/**
	 * 
	 */
	public void cheapBestRatedHotel() {
		List<Hotel> sortedHotelList = hotelList.stream().sorted(Comparator.comparing(Hotel::getTotalRate))
				.collect(Collectors.toList());
		long minCost = sortedHotelList.get(0).getTotalRate();
		int rate = sortedHotelList.get(0).getRate();
		String cheapHotel = sortedHotelList.get(0).getName();
		for (Hotel hotel : sortedHotelList) {
			if (hotel.getTotalRate() == minCost) {
				if (hotel.getRate() < sortedHotelList.get(0).getRate()) {
					cheapHotel = hotel.getName();
					rate = hotel.getRate();
					minCost = hotel.getTotalRate();
				}
			}
		}
		log.info(cheapHotel + ", TotalRate = $" + minCost + " ,Rating : " + rate);
	}

	public static void main(String[] args) throws InvalidInputException {
		
		HotelReservation hotelReservation = new HotelReservation();
		hotelReservation.addHotels();
		hotelReservation.hotelList.forEach(n -> log.info(n));
		hotelReservation.addTotalRate();
		hotelReservation.cheapBestRatedHotel();
	  //  hotelReservation.highRatedHotel();

	}
}
