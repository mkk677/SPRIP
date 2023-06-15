package com.codingbox.sprip.reservation.plane;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class AirRepository {
	
	@Autowired
	private final EntityManager em;
 
	public void add(Air air) {
		em.persist(air);
	}
	
	public Air findAir(Long airlineId) {
		return em.find(Air.class, airlineId);
	}
 
	public List<Air> findAll() {
		return em.createQuery("select a from Air a", Air.class)
				.getResultList();
	}
	
	public List<Aircraft> findAircrafts(Long airlineId) {
		return em.createQuery("select a from Aircraft a where airlineid = :airlineid", Aircraft.class)
				.setParameter("airlineid", airlineId)
				.getResultList();
	}

	public Aircraft findAirCraft(Long aircraftId) {
		return em.find(Aircraft.class, aircraftId);
	}
}
