package com.codingbox.sprip.reservation;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.codingbox.sprip.member.Member;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class Reservation {
	
	private int reservenum;
	private String reservedate;
	private String startdate;
	private String finishdate;
	private int paymentprice;
	
	@Enumerated(EnumType.STRING)
	private ReserveStatus status; 
	
	@ManyToOne @JoinColumn(name="MEMBER_ID")
	private Member member;
	
 	public void MemberSet(Member member) {
		this.member = member;
	}

	public Reservation(int reservenum, String reservedate, String startdate, String finishdate, int paymentprice,
			ReserveStatus status) {
		this.reservenum = reservenum;
		this.reservedate = reservedate;
		this.startdate = startdate;
		this.finishdate = finishdate;
		this.paymentprice = paymentprice;
		this.status = status;
	}

 
}
