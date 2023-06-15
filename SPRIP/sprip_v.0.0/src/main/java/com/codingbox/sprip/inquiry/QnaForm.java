package com.codingbox.sprip.inquiry;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.codingbox.sprip.member.Member;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class QnaForm {
	private String qnatitle;
	private String qnadetail;

}
