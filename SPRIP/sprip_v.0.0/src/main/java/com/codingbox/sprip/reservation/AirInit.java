package com.codingbox.sprip.reservation;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.codingbox.sprip.reservation.plane.Air;
import com.codingbox.sprip.reservation.plane.AirCost;
import com.codingbox.sprip.reservation.plane.Aircraft;

import lombok.RequiredArgsConstructor;

@Component @RequiredArgsConstructor 
public class AirInit {
	
	
	private final EntityManager em;
	
	@Transactional
	public void init() {
		
		Air air1 = new Air("대한항공","한국","ke.webp",AirCost.FULL_SERVICE); 
		Air air2 = new Air("피치항공","일본","mm.webp",AirCost.LOW_COST); 
		Air air3 = new Air("진에어","한국","lj.webp",AirCost.LOW_COST); 
		Air air4 = new Air("티웨이항공","한국","tw.webp",AirCost.LOW_COST); 
		Air air5 = new Air("아시아나항공","한국","oz.webp",AirCost.FULL_SERVICE); 
		
		air1.addAircraft(new Aircraft("A380-800"));
		air1.addAircraft(new Aircraft("A321-neo"));
		air1.addAircraft(new Aircraft("B787-9"));
		air1.addAircraft(new Aircraft("B777-300ER"));
		
		air2.addAircraft(new Aircraft("A320"));
		air2.addAircraft(new Aircraft("A320-neo"));
		air2.addAircraft(new Aircraft("A321LR"));
		
		air3.addAircraft(new Aircraft("B777-200ER"));
		air3.addAircraft(new Aircraft("B737-800"));
		air3.addAircraft(new Aircraft("B737-8"));
		air3.addAircraft(new Aircraft("B737-900"));
		
		air4.addAircraft(new Aircraft("A330-300"));
		air4.addAircraft(new Aircraft("B737-800"));
		air4.addAircraft(new Aircraft("B737-8"));
		
		air5.addAircraft(new Aircraft("A380-800"));
		air5.addAircraft(new Aircraft("B747-400"));
		air5.addAircraft(new Aircraft("A350-900"));
		air5.addAircraft(new Aircraft("B777-200ER"));
		
		em.persist(air1);
		em.persist(air2);
		em.persist(air3);
		em.persist(air4);
		em.persist(air5);
		
	}
}
