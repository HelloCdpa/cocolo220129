package com.phl.cocolo.interceptor;

import com.phl.cocolo.common.SessionConst;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginCheckInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        //사용자가 요청한 주소
        String requestURI = request.getRequestURI();
        //session은 request 객체 안에 들어있음 -> 서블릿이 관리해 줌
        // 서블릿이 있어야 웹서버 구축 - 스프링부트, 스프링 프레임 워크로 서블릿을 편하게 쓴다!
        //세션을 가져옴
        HttpSession session = request.getSession();
        //세션에 로그인 정보가 있는지 확인(세션 로그인 값이 있으면~ 로그인 한 것 없으면 ~안 한 것)
        if(session.getAttribute(SessionConst.LOGIN_EMAIL) == null){
            //미로그인 상태
            //로그인 하지 않은 경우 바로 로그인 페이지로 보내고 로그인 후 요청 화면을 보여줌.
            //requestURI 사용자가 요청한 주소값
//            session.setAttribute("redirectURL", requestURI);
            response.sendRedirect("/member/login");

            return false;
        } else {
            //로그인 상태
            return true;
        }
    }

}
