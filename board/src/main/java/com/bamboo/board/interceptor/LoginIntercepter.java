//package com.bamboo.board.interceptor;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.util.ObjectUtils;
//import org.springframework.web.servlet.HandlerInterceptor;
//
//import com.bamboo.board.model.UserDto;
//
//public class LoginIntercepter implements HandlerInterceptor {
//
//	private static final Logger log = LoggerFactory.getLogger(LoginIntercepter.class);
//
//	@Override
//	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
//			throws Exception {
//
//		log.info("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
//		log.info("LoginInterceptor 진입");
//		log.info("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
//
//		HttpSession session = request.getSession();
//		UserDto login = (UserDto) session.getAttribute("loginUser");
//
//		log.info("asdasd"+login);
//		if (ObjectUtils.isEmpty(login)) {
//			response.sendRedirect("login.do");
//			return false;
//		} else {
////			session.setMaxInactiveInterval(30 * 60);
//			return true;
//		}
//
//	}
//}
