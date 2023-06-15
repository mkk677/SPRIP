package com.codingbox.sprip.inquiry;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.codingbox.sprip.member.Member;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class QnA {
	
	@Id @GeneratedValue
	private Long qnaid;
	
	private String qnatitle;
	private String qnadetail;
	
	private LocalDateTime createdDate;
	
	@ManyToOne
	@JoinColumn(name="MEMBER_ID")
	private Member member;
	
	
}
