package com.codingbox.sprip.reservation.plane;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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
public class TicketController {
	
	private final TicketService ticketService;
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	
	@GetMapping("reservation/plane")
	public String plane(Model model){
		model.addAttribute("ticketFormDTO", new TicketFormDTO());
		model.addAttribute("travelspots",ticketService.getAllTravelSpot());
		return "reservation/plane";
	}
	
	@PostMapping("plane/findTicket")
	public String planeFind(@Validated(TicketFormValidationSequence.class) TicketFormDTO ticketFormDTO, 
			BindingResult result, Model model) {
		
		if (result.hasErrors()) {
			model.addAttribute("ticketFormDTO", ticketFormDTO);
			model.addAttribute("travelspots",ticketService.getAllTravelSpot());
			System.out.println(result);
			return "reservation/plane";
		}
		
		List<Map<String, String>> flighttimes = new ArrayList<>();
		RandomTime randomTime = new RandomTime();
		
		flighttimes.add(randomTime.createRandomTime());
		flighttimes.add(randomTime.createRandomTime());
		flighttimes.add(randomTime.createRandomTime());

		model.addAttribute("travelspots",ticketService.getAllTravelSpot());
		model.addAttribute("airlines",ticketService.getAllAirLine());
		model.addAttribute("flighttimes", flighttimes);
		model.addAttribute("ticketprice", ticketService.ticketPrice(ticketFormDTO.getFinishpoint(), ticketFormDTO.getSeatclass()));
		
		return "reservation/plane_list";
	}
	
	@PostMapping("plane/payment")
	public String planePayment(Model model, TicketFormDTO ticketFormDTO ) throws ParseException {
		
		Long airid = ticketFormDTO.getAirid();
		Air air = ticketService.findAirLine(airid);
		Aircraft aircraft = ticketService.setAirCraft(airid);
		
		TicketDTO ticketDTO = new TicketDTO();

        int ticketprice = ticketService.ticketPrice(ticketFormDTO.getFinishpoint(), ticketFormDTO.getSeatclass());
        int paymentprice = ticketService.paymentPrice(ticketprice, ticketFormDTO.getReservenum(), 
        		air.getAircost(), ticketFormDTO.getRoundornot());
        		
        ticketDTO.setTicketprice(ticketprice);
        ticketDTO.setDepart(ticketFormDTO.getDepart());
        ticketDTO.setArrive(ticketFormDTO.getArrive());
        ticketDTO.setSeatclass(SeatClass.valueOf(ticketFormDTO.getSeatclass()));
        ticketDTO.setRoundornot(RoundOrNot.valueOf(ticketFormDTO.getRoundornot()));
        ticketDTO.setStartpoint(ticketFormDTO.getStartpoint());
        ticketDTO.setFinishpoint(ticketFormDTO.getFinishpoint());

        ticketDTO.setReservenum(ticketFormDTO.getReservenum());
        ticketDTO.setStartdate(ticketFormDTO.getStartdate());
        
        ticketDTO.setFinishdate(ticketFormDTO.getFinishdate());
        if(ticketFormDTO.getDayplus().equals("plus")) {
        	Date fdate = format.parse(ticketFormDTO.getFinishdate());
        	Calendar cal = Calendar.getInstance();
    		cal.setTime(fdate);
    		cal.add(Calendar.DATE, 1);
    		fdate = cal.getTime();
        	String finishdate = format.format(fdate);
        	ticketDTO.setFinishdate(finishdate);
        }
        
        ticketDTO.setPaymentprice(paymentprice);
		
		model.addAttribute("air", air);
		model.addAttribute("aircraft", aircraft);
		model.addAttribute("duration", ticketFormDTO.getDuration());
		model.addAttribute("ticketDTO", ticketDTO);
		
		return "reservation/plane_payment";
	}
	
	@PostMapping("plane/reserve")
	public String planeReserve(@ModelAttribute TicketDTO ticketDTO, HttpSession session) {
		
		Ticket ticket = new Ticket();
		Reservation reservation = new Reservation();
		
		Date date = new Date();
		reservation.setReservedate(format.format(date));
		reservation.setStatus(ReserveStatus.RESERVED);
		reservation.setStartdate(ticketDTO.getStartdate());
		reservation.setFinishdate(ticketDTO.getFinishdate());
		reservation.setReservenum(ticketDTO.getReservenum());
		reservation.setPaymentprice(ticketDTO.getPaymentprice());
		reservation.MemberSet((Member) session.getAttribute(SessionConst.LOGIN_MEMBER));
		
		ticket.setSeatclass(ticketDTO.getSeatclass());
		ticket.setRoundornot(ticketDTO.getRoundornot());
		ticket.setTicketprice(ticketDTO.getTicketprice());
		ticket.setArrive(ticketDTO.getArrive());
		ticket.setDepart(ticketDTO.getDepart());
		ticket.setReservation(reservation);
		
        ticket.startPointSet(ticketService.findTravelSpot(ticketDTO.getStartpoint()));
        ticket.finishPointSet(ticketService.findTravelSpot(ticketDTO.getFinishpoint()));
        ticket.AircraftSet(ticketService.findAirCraft(ticketDTO.getAircraftid()));
        
		ticketService.reserve(ticket);
		
		return "redirect:/mypage";
	}
	
	@GetMapping("reservation/ticket/cancel/{id}")
	public String TicketCancel(@PathVariable Long id){
		ticketService.cancel(id);
		return "redirect:/mypage";
	}
 
}
