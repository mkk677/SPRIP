package com.codingbox.sprip.reservation.hotel;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.codingbox.sprip.member.Member;
import com.codingbox.sprip.member.SessionConst;
import com.codingbox.sprip.reservation.Reservation;
import com.codingbox.sprip.reservation.ReserveStatus;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class HotelController {
	
	private final HotelService hotelService;
	
	@GetMapping("reservation/hotel")
	public String hotel(Model model){
		model.addAttribute("HotelDTO", new HotelDTO());
		return "reservation/hotel";
	}
	
	@PostMapping("reservation/hotel/hotelReserve")
	public String hotelReserve(@ModelAttribute HotelDTO hotelDTO, HttpSession session){
		Hotel hotel = new Hotel();
		Reservation reservation = new Reservation(hotelDTO.getReservenum(),hotelDTO.getReservedate(),
				hotelDTO.getStartdate(),hotelDTO.getFinishdate(),hotelDTO.getPaymentprice(),ReserveStatus.RESERVED);
		reservation.MemberSet((Member) session.getAttribute(SessionConst.LOGIN_MEMBER));
		hotel.setAddr(hotelDTO.getAddr());
		hotel.setHotelname(hotelDTO.getHotelname());
		hotel.setHotelprice(hotelDTO.getHotelprice());
		hotel.setHotelphone(hotelDTO.getHotelphone());
		hotel.setHotelplaceid(hotelDTO.getHotelplaceid());
		hotel.setReservation(reservation);
		hotelService.reserve(hotel);
		return "redirect:/mypage";
	}
	
	@GetMapping("reservation/hotel/cancel/{id}")
	public String hotelCancel(@PathVariable Long id){
		hotelService.cancel(id);
		return "redirect:/mypage";
	}
}
