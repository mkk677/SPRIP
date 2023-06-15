package com.codingbox.sprip.mypage;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.codingbox.sprip.member.Member;
import com.codingbox.sprip.member.MemberEditForm;
import com.codingbox.sprip.member.SessionConst;
import com.codingbox.sprip.reservation.hotel.HotelRepository;
import com.codingbox.sprip.reservation.plane.TicketRepository;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MypageController {
	
	private final TicketRepository ticketRepository;
	private final HotelRepository hotelRepository; 
	
	@GetMapping("/mypage")
	public String mypage(Model model, HttpSession session){
		Member member = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
		model.addAttribute("hotels", hotelRepository.findAllHotel(member.getUserid()));
		model.addAttribute("tickets", ticketRepository.findAllTicket(member.getUserid()));
		
		return "mypage/mypage";
	}
	
	@GetMapping("/mypage/edit")
	public String mypageEdit(Model model){
		return "mypage/EditMember";
	}
}
