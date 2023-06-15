package com.codingbox.sprip.reservation.hotel;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service @RequiredArgsConstructor
@Transactional(readOnly = true)
public class HotelService {

	private final HotelRepository hotelRepository;
	
	@Transactional
	public void reserve(Hotel hotel) {
		hotelRepository.reserve(hotel);
	}
	
	@Transactional
	public void cancel(Long HotelId) {
		hotelRepository.cancel(HotelId);
	}
	
	
}
