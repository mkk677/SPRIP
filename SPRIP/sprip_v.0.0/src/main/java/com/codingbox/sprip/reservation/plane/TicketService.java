package com.codingbox.sprip.reservation.plane;

import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codingbox.sprip.reservation.travelspot.Continent;
import com.codingbox.sprip.reservation.travelspot.TravelSpot;
import com.codingbox.sprip.reservation.travelspot.TravelSpotRepository;

import lombok.RequiredArgsConstructor;

@Service @RequiredArgsConstructor
@Transactional(readOnly = true)
public class TicketService {

	private final TicketRepository ticketRepository;
	private final TravelSpotRepository travelSpotRepository;
	private final AirRepository airRepository;
	
	@Transactional
	public void reserve(Ticket ticket) {
		ticketRepository.reserve(ticket);
	}
	
	@Transactional
	public void cancel(Long ticketId) {
		ticketRepository.cancel(ticketId);
	}
	
	public TravelSpot findTravelSpot(String city) {
		return travelSpotRepository.findSpot(city);
	}
	
	public List<TravelSpot> getAllTravelSpot() {
		return travelSpotRepository.findAll();
	}
	
	public Air findAirLine(Long id) {
		return airRepository.findAir(id);
	}
	
	public List<Air> getAllAirLine() {
		return airRepository.findAll();
	}
	
	public Aircraft findAirCraft(Long id) {
		return airRepository.findAirCraft(id);
	}
	
	public Aircraft setAirCraft(Long airId) {
		List<Aircraft> aircrafts = airRepository.findAircrafts(airId);
		Random random = new Random();
		int randnum = random.nextInt(aircrafts.size());
		return aircrafts.get(randnum);
	}
	
	public int ticketPrice(String city, String seatclass) {
		
		int ticketPrice = 0;
		Continent continent = travelSpotRepository.findContinent(city).getContinent();
		SeatClass sClass = SeatClass.valueOf(seatclass);
		
		switch(continent) {
			case ASIA :	ticketPrice = 245000; break;
			case NORTH_AMERICA : ticketPrice = 2050000; break;
			case SOUTH_AMERICA : ticketPrice = 305000; break;
			case OCEANIA : ticketPrice = 745000; break;
			case AFRICA : ticketPrice = 1550000; break;
			case EUROPE : ticketPrice = 1450000; break;
		}
		switch(sClass) {
			case ECONOMY  :	break;
			case BUSINESS : ticketPrice *= 3; break;
			case FIRST : ticketPrice *= 4; break; 
		}
		
		return ticketPrice;
	}

	public int paymentPrice(int ticketprice, int reservenum, AirCost aircost, String roundornot) {
		
		int paymentPrice = ticketprice * reservenum;
		RoundOrNot rNot = RoundOrNot.valueOf(roundornot);
		
		if(aircost == AirCost.FULL_SERVICE) {
			paymentPrice *= 1.5;
		}
		if(rNot == RoundOrNot.ROUND) {
			paymentPrice *= 2;
		}
		
		return paymentPrice;
	}

 
	
}
