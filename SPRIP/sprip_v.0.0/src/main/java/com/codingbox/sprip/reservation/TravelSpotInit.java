package com.codingbox.sprip.reservation;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.codingbox.sprip.reservation.travelspot.Climate;
import com.codingbox.sprip.reservation.travelspot.Continent;
import com.codingbox.sprip.reservation.travelspot.TravelSpot;

import lombok.RequiredArgsConstructor;

@Component @RequiredArgsConstructor 
public class TravelSpotInit {
	
	private final EntityManager em;
	
	@Transactional
	public void init() {
		
		List<TravelSpot> spots = new ArrayList<>();
		spots.add(new TravelSpot(Continent.ASIA, "한국", "서울", Climate.TEMPERATE));
		spots.add(new TravelSpot(Continent.ASIA, "한국", "부산", Climate.TEMPERATE));
		spots.add(new TravelSpot(Continent.ASIA, "한국", "강릉", Climate.TEMPERATE));
		spots.add(new TravelSpot(Continent.ASIA, "한국", "제주", Climate.TROPICAL));
		spots.add(new TravelSpot(Continent.ASIA, "한국", "용인", Climate.TEMPERATE));
		
		spots.add(new TravelSpot(Continent.ASIA, "일본", "도쿄", Climate.TEMPERATE));
		spots.add(new TravelSpot(Continent.ASIA, "일본", "오사카", Climate.TEMPERATE));
		spots.add(new TravelSpot(Continent.ASIA, "일본", "오키나와", Climate.TROPICAL));
		spots.add(new TravelSpot(Continent.ASIA, "일본", "홋카이도", Climate.SUBARCTIC));
		spots.add(new TravelSpot(Continent.ASIA, "몽골", "울란바토르", Climate.DRY));
		spots.add(new TravelSpot(Continent.ASIA, "대만", "타이베이", Climate.TROPICAL));
		spots.add(new TravelSpot(Continent.ASIA, "인도네시아", "발리", Climate.TROPICAL));
		spots.add(new TravelSpot(Continent.ASIA, "중국", "베이징", Climate.SUBARCTIC));
		spots.add(new TravelSpot(Continent.ASIA, "필리핀", "마닐라", Climate.TROPICAL));
		spots.add(new TravelSpot(Continent.ASIA, "베트남", "하노이", Climate.TROPICAL));
		spots.add(new TravelSpot(Continent.ASIA, "싱가포르", "싱가포르", Climate.TROPICAL));
		spots.add(new TravelSpot(Continent.ASIA, "튀르키예", "앙카라", Climate.TEMPERATE));
		spots.add(new TravelSpot(Continent.ASIA, "이란", "테헤란", Climate.DRY));
		spots.add(new TravelSpot(Continent.ASIA, "아랍에미리트", "아부다비", Climate.TROPICAL));
		spots.add(new TravelSpot(Continent.ASIA, "카타르", "도하" , Climate.TROPICAL));
		
		spots.add(new TravelSpot(Continent.EUROPE, "스페인", "세비야" , Climate.TEMPERATE));
		spots.add(new TravelSpot(Continent.EUROPE, "프랑스", "파리" , Climate.TEMPERATE));
		spots.add(new TravelSpot(Continent.EUROPE, "영국", "런던" , Climate.TEMPERATE));
		spots.add(new TravelSpot(Continent.EUROPE, "독일", "베를린" , Climate.TEMPERATE));
		spots.add(new TravelSpot(Continent.EUROPE, "네덜란드", "암스테르담" , Climate.TEMPERATE));
		spots.add(new TravelSpot(Continent.EUROPE, "벨라루스", "민스크" , Climate.SUBARCTIC));
		spots.add(new TravelSpot(Continent.EUROPE, "스위스", "베른" , Climate.SUBARCTIC));
		spots.add(new TravelSpot(Continent.EUROPE, "이탈리아", "로마" , Climate.TEMPERATE));
		spots.add(new TravelSpot(Continent.EUROPE, "스페인", "마드리드" , Climate.TEMPERATE));
		spots.add(new TravelSpot(Continent.EUROPE, "스웨덴", "스톡홀름" , Climate.TEMPERATE));
		
		spots.add( new TravelSpot(Continent.NORTH_AMERICA, "미국", "라스베이거스" , Climate.DRY));
		spots.add(new TravelSpot(Continent.NORTH_AMERICA, "미국", "뉴욕" , Climate.TEMPERATE));
		spots.add(new TravelSpot(Continent.NORTH_AMERICA, "캐나다", "토론토" , Climate.SUBARCTIC));
		spots.add(new TravelSpot(Continent.NORTH_AMERICA, "캐나다", "밴쿠버" , Climate.TEMPERATE));
		spots.add(new TravelSpot(Continent.NORTH_AMERICA, "멕시코", "멕시코시티" , Climate.TROPICAL));
		
		spots.add(new TravelSpot(Continent.SOUTH_AMERICA, "브라질", "리우데자네이루" , Climate.TROPICAL));
		spots.add(new TravelSpot(Continent.SOUTH_AMERICA, "아르헨티나", "부에노스아이레스" , Climate.TEMPERATE));
		spots.add(new TravelSpot(Continent.SOUTH_AMERICA, "우루과이", "몬테비데오" , Climate.TEMPERATE));
		spots.add(new TravelSpot(Continent.SOUTH_AMERICA, "페루", "쿠스코" , Climate.TEMPERATE));
		spots.add(new TravelSpot(Continent.SOUTH_AMERICA, "볼리비아", "오루로" , Climate.DRY));
		
		spots.add(new TravelSpot(Continent.AFRICA, "이집트", "카이로" , Climate.DRY)); 
		spots.add(new TravelSpot(Continent.AFRICA, "남아프리카공화국", "프리토리아" , Climate.TROPICAL)); 
		spots.add(new TravelSpot(Continent.AFRICA, "모로코", "라바트" , Climate.TEMPERATE)); 
		spots.add(new TravelSpot(Continent.AFRICA, "케냐", "나이로비" , Climate.TROPICAL)); 
		spots.add(new TravelSpot(Continent.AFRICA, "탄자니아", "잔지바르" , Climate.TROPICAL)); 

		spots.add(new TravelSpot(Continent.OCEANIA, "오스트레일리아", "멜버른" , Climate.TEMPERATE)); 
		spots.add(new TravelSpot(Continent.OCEANIA, "오스트레일리아", "시드니" , Climate.TEMPERATE)); 
		spots.add(new TravelSpot(Continent.OCEANIA, "뉴질랜드", "오클랜드" , Climate.TEMPERATE)); 
		spots.add(new TravelSpot(Continent.OCEANIA, "파푸아뉴기니", "포트모르즈비" , Climate.TROPICAL)); 
		spots.add(new TravelSpot(Continent.OCEANIA, "하와이", "호놀룰루" , Climate.TROPICAL)); 
		
		for(TravelSpot spot : spots) {
			em.persist(spot);
		}
	}
	
}
