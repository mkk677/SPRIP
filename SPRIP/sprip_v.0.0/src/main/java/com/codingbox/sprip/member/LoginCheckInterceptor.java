package com.codingbox.sprip.member;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

public class LoginCheckInterceptor implements HandlerInterceptor {

    private static final List<String> whitelist = Arrays.asList(
            "/", "/members/register", "/members/login", "/members/logout",
            "/reservation/plane", "/reservation/hotel",
            "/reservation/travelspot/**", "/plane/findTicket",
            "/qna/service", "/members/idcheck",
            "/css/*",
            "/js/**",
            "/img/**"
    );

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();

        if (!isLoginCheckPath(requestURI)) {
            return true; // 화이트 리스트에 해당하는 경로는 인증 체크를 수행하지 않고 통과
        }

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute(SessionConst.LOGIN_MEMBER) == null) {
            response.sendRedirect("/members/login?redirectURL=" + requestURI);
            return false; // 인증되지 않은 사용자는 리다이렉트하고 이후 작업을 처리하지 않음
        }

        return true; // 인증된 사용자는 통과
    }

    private boolean isLoginCheckPath(String requestURI) {
        return !whitelist.stream().anyMatch(path -> path.endsWith("/**") ? requestURI.startsWith(path.substring(0, path.length() - 3)) : requestURI.equals(path));
    }
}