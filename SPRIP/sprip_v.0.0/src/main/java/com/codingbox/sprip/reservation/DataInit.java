package com.codingbox.sprip.reservation;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DataInit {
	
	@Autowired
	private final AirInit airinit;
	private final TravelSpotInit travelinit;
	
	@PostConstruct
	public void init() {
		airinit.init();
		travelinit.init();
	}
	
}
