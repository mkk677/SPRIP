package com.codingbox.sprip.reservation.plane;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter 
@AllArgsConstructor @NoArgsConstructor
public class TicketFormDTO {
	
	@NotBlank 
	private String roundornot;
	
	@NotNull(message="예매 인원을 선택해 주세요", groups = TicketFormValidationGroup.NumAndSeatGroup.class)
	@Min(value = 1, message="예매 인원을 선택해 주세요")
	private Integer reservenum;
	
	@NotBlank(message="좌석 등급을 선택해 주세요", groups = TicketFormValidationGroup.NumAndSeatGroup.class)
	private String seatclass;
	
	@NotBlank(message="출발지를 선택해 주세요", groups = TicketFormValidationGroup.SpotAndDateGroup .class)
	private String startpoint;
	
	@NotBlank(message="도착지를 선택해 주세요", groups = TicketFormValidationGroup.SpotAndDateGroup.class)
	private String finishpoint;
	
	@NotBlank(message="출발 날짜를 선택해 주세요", groups = TicketFormValidationGroup.SpotAndDateGroup.class)
	private String startdate;
	
	@NotBlank(message="오는 날짜를 선택해 주세요", groups = TicketFormValidationGroup.SpotAndDateGroup.class)
	private String finishdate;
	
	private Long airid;
	private String depart;
	private String duration;
	private String arrive;
	private String dayplus;
	
}
