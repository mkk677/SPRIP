package com.codingbox.sprip.reservation.plane;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class RandomTime {
	
	Random random = new Random();
	
	public Map<String, String> createRandomTime(){
		
		Map<String, String> time = new HashMap<String, String>();
		
		int depart = random.nextInt(289)*5;
		int duration = random.nextInt(181)*5;
		int arrive = depart + duration;
		String dayplus = "none";
		if(arrive/60 >= 24) {
			arrive -= 60 * 24;
			dayplus = "plus";
		}
		time.put("depart", timeToString(depart));
		time.put("duration",durationToString(duration));
		time.put("arrive",timeToString(arrive));
		time.put("dayplus",dayplus);
		
		return time;
		
	}
	
	public String timeToString(int time) {
		return String.format("%02d", time/60) + ":" + String.format("%02d", time%60);
	}
	
	public String durationToString(int time) {
		return time/60 + "시간 " + time%60 + "분";
	}
	
 
}
