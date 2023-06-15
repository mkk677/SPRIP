package com.codingbox.sprip.reservation.travelspot;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;

@Controller @RequiredArgsConstructor
public class TravelspotController {
	
	private final TravelSpotRepository travelSpotRepository;
	
	@GetMapping("/reservation/travelspot/{spot_page}")
	public String travelspot(Model model, @PathVariable int spot_page) {
		int pagecount = travelSpotRepository.spotCount().intValue();
		model.addAttribute("pagecount", pagecount / 5);
		model.addAttribute("now_page", spot_page);
		model.addAttribute("spots",travelSpotRepository.findpage(spot_page));
		return "reservation/travelspot";
	}
	
	@GetMapping("/reservation/travelspot/continent/{continent}/{c_page}")
	public String findByContinent(Model model, @PathVariable Continent continent, @PathVariable int c_page) {
		int pagecount = travelSpotRepository.spotCount_Continent(continent).intValue();
		model.addAttribute("pagecount", pagecount / 5);
		model.addAttribute("now_page", c_page);
		model.addAttribute("spots",travelSpotRepository.findContinentpage(c_page, continent));
		return "reservation/travelspot";
	}
	
	@GetMapping("/reservation/travelspot/climate/{climate}/{c_page}")
	public String findByClimate(Model model, @PathVariable Climate climate, @PathVariable int c_page) {
		int pagecount = travelSpotRepository.spotCount_Climate(climate).intValue();
		model.addAttribute("pagecount", pagecount / 5);
		model.addAttribute("now_page", c_page);
		model.addAttribute("spots",travelSpotRepository.findClimatepage(c_page, climate));
		return "reservation/travelspot";
	}
	
	@GetMapping("/reservation/travelspot/local/{l_page}")
	public String findLocal(Model model, @PathVariable int l_page) {
		int pagecount = travelSpotRepository.spotCount_Local().intValue();
		model.addAttribute("pagecount", pagecount / 5);
		model.addAttribute("now_page", l_page);
		model.addAttribute("spots",travelSpotRepository.findLocal(l_page));
		return "reservation/travelspot";
	}
	
	@GetMapping("/reservation/travelspot/abroad/{a_page}")
	public String findAbroad(Model model, @PathVariable int a_page) {
		int pagecount = travelSpotRepository.spotCount_Abroad().intValue();
		model.addAttribute("pagecount", pagecount / 5);
		model.addAttribute("now_page", a_page);
		model.addAttribute("spots",travelSpotRepository.findAbroad(a_page));
		return "reservation/travelspot";
	}
}
