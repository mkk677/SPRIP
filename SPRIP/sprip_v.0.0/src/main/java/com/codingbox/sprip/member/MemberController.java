package com.codingbox.sprip.member;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.codingbox.sprip.member.validation.ValidationSequence;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MemberController {
	
	private final MemberService memberService;
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	// 회원가입

	@GetMapping("/members/register")
	public String registerForm(Model model){
		model.addAttribute("memberForm", new MemberForm());
	return "members/register";	
	}

	// 회원 가입
	@PostMapping("/members/register")
	public String register(@Validated(ValidationSequence.class) MemberForm form, BindingResult result, Model model)throws IllegalAccessException {
		
		if (result.hasErrors()) {
			// 실패시 입력 데이터 값 유지
			model.addAttribute("memberForm", form);
			
			
			return "members/register";
		}
		
		Member member = new Member();
		member.setUserid(form.getUserid());
		member.setUsername(form.getUsername());
		member.setUserpw(form.getUserpw());
		member.setUserphone(form.getUserphone());
		member.setUseremail(form.getUseremail());
		
		memberService.register(member);
		return "redirect:/members/login";	// 로그인 페이지로 넘기기
	}
	
	
    @ResponseBody // 값 변환을 위해 꼭 필요함
	@GetMapping("/members/idcheck") // 아이디 중복확인을 위한 값으로 따로 매핑
	public int overlappedID(Member Member) throws Exception{
		int result = memberService.overlappedID(Member); // 중복확인한 값을 int로 받음

		return result;
	}
	
    //회원 정보 수정
	@GetMapping("/members/{userid}/edit")
	public String editMemberForm(@PathVariable("userid") String userid, Model model) {
		
		System.out.println(userid);
		
		Member member = memberService.findOne(userid);
		System.out.println(member.getUserid());
		MemberForm form = new MemberForm();
		form.setUserid(member.getUserid());
		
		System.out.println(form.getUserid());
		form.setUsername(member.getUsername());
		form.setUserpw(member.getUserpw());
		form.setUserphone(member.getUserphone());
		form.setUseremail(member.getUseremail());
		model.addAttribute("form", form);
		
		return "mypage/EditMember";
	}
	
	//회원 정보 수정
	@PostMapping("/members/{userid}/edit")
	public String editMember(@ModelAttribute("form")Member form ) {
		Member member = new Member();
		
		member.setUserid(form.getUserid());
		member.setUsername(form.getUsername());
		member.setUserpw(form.getUserpw());
		member.setUserphone(form.getUserphone());
		member.setUseremail(form.getUseremail());
		
		memberService.editMember(form.getUserid(),form);
		
		return "redirect:/mypage"; 
		
	}
	
	
//	// 로그인 
		@GetMapping("/members/login")
		public String loginForm(Model model) {
			model.addAttribute("loginForm", new LoginForm());
			return "members/login";
		}
		
		@PostMapping("/members/login")
		public String login(@ModelAttribute("loginForm") LoginForm form, 
				BindingResult bindingResult, HttpServletRequest request, 
				@RequestParam(defaultValue = "/") String redirectURL, Model model) {
			
			System.out.println(bindingResult);
			
			if(bindingResult.hasErrors()) {
				return "members/login";
			}
			
			// 로그인 창에 입력한 아이디 값과 비번 검증 위해 Member 엔티티에 저장되어있는
			// 아이디와 비번 값 서비스에서 불러오기
			
			Member loginMember = memberService.login2(form.getLoginId(), form.getLoginpw());
			
			if(loginMember == null) {
				bindingResult.reject("loginMember", "아이디와 비밀번호가 맞지 않습니다.");
				return "members/login";
			}
			
			// 엔티티 저장 데이터값 불러오냐..........?
			System.out.println(loginMember.getUserid());
			System.out.println(loginMember.getUserpw());
			System.out.println(loginMember);
			
			// 로그인 입력창 데이터값은...?
			System.out.println(form.getLoginId());
			System.out.println(loginMember.getUserid());
			
			System.out.println(form.getLoginpw());
			System.out.println(loginMember.getUserpw());
						
			// HttpSession 통해 세션 생성
			HttpSession session = request.getSession();
			session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);	
			
			String userId = loginMember.getUserid();
			
			// 디버깅 로그 통해 세션이랑 요청 생성되고 가는지 검증
			logger.debug("Request: {}", request);
		    logger.debug("Session: {}", (Member) session.getAttribute(SessionConst.LOGIN_MEMBER));
		    
		    logger.debug("User ID: {}", userId);
			
		    // 메인페이지 이동
			return "redirect:" + redirectURL;
		}

		
	// 로그아웃
	
	@GetMapping("/members/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
    
    
    // 회원 탈퇴
    @PostMapping("/members/{userid}/delete")
    public String deleteMember(@PathVariable String userid, @ModelAttribute("member") Member form, HttpSession session) {
        memberService.deleteMember(userid, form);
        session.invalidate();
        return "redirect:/";
    }
    

	
}
