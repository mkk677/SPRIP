package com.codingbox.sprip.reservation.hotel;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.codingbox.sprip.reservation.Reservation;
import com.codingbox.sprip.reservation.ReserveStatus;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class HotelRepository {
	
	@Autowired
	private final EntityManager em;
	
	public void reserve(Hotel hotel) {
		em.persist(hotel);
	}
	
	public Hotel findHotelReserve(Long hotelId) {
		return em.find(Hotel.class, hotelId);
	}
	
	public List<Hotel> findAllHotel(String memberId) {
		return em.createQuery("select h from Hotel h where h.reservation.member.userid = :memberid", Hotel.class)
				.setParameter("memberid", memberId)
				.getResultList();
	}
	
	public void cancel(Long hotelId) {
		Hotel hotel = em.find(Hotel.class, hotelId);
		hotel.getReservation().setStatus(ReserveStatus.CANCELED);
	}
}
