package com.codingbox.sprip.reservation.plane;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class TicketDTO {
	
	private int ticketprice;
 
	private String depart;
	private String arrive;
	
	@Enumerated(EnumType.STRING)
	private SeatClass seatclass;
	
	@Enumerated(EnumType.STRING)
	private RoundOrNot roundornot;
	
	private String startpoint;
	private String finishpoint;
	private Long aircraftid;
 
	private int reservenum;
	private String startdate;
	private String finishdate;
	private int paymentprice;
 	
}
