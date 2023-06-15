package com.codingbox.sprip;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.codingbox.sprip.member.Member;
import com.codingbox.sprip.member.SessionConst;

@Controller
public class HomeController {
//	 @GetMapping("/")
//	    public String home() {
//	        return "index"; // "index"는 홈페이지의 뷰 이름입니다.
//	    }
	 
	 @GetMapping("/")
		public String loginhomev3(@SessionAttribute(name=SessionConst.LOGIN_MEMBER, required = false)Member loginMember,
				Model model){
			// session attribute 뒤져서 member에 값을 넣어준다.
			if(loginMember == null) {
				return "index";
			}
			// 세션이 유지되면 model에 db 조회 결과 담아서 return loginHome
			model.addAttribute("member", loginMember);
			return "index";
		}
}
