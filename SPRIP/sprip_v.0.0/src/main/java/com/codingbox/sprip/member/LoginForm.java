package com.codingbox.sprip.member;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginForm {

	@NotNull(message="아이디를 입력해주세요.")
	private String loginId;
	
	@NotNull(message = "비밀번호를 입력해주세요.")
	private String loginpw;
}
