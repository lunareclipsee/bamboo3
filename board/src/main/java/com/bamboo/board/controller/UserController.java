package com.bamboo.board.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bamboo.board.model.UserDto;
import com.bamboo.board.service.UserService;

@Controller // 컨트롤러 클래스 선언
@RequestMapping("/bamboo/user/*")
public class UserController {
	private static final Logger log = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	// 1. 로그인 페이지로 이동 요청(/user/login) 처리
	@RequestMapping(value = "login.do", method = RequestMethod.GET)
	public String goLogin() {
		log.info("Welcome login!");
//		System.out.println("login 페이지 이동");
		// 단순 페이지 이동
		// 리턴 타입 - void ==> 받은 요청과 같은 이름의 뷰 페이지로 이동
		// user/login.jsp 페이지로 이동
		return "user/login";
	}

	// 2. 로그인 처리 요청(/user/loginPost) 처리 메소드
	// -> 로그인 처리 시 로그인 성공 결과는 HttpSession에 저장해야함 ****
	@RequestMapping(value = "loginCtr.do", method = RequestMethod.POST)
	public String loginCtr(UserDto userDto, HttpSession session) {
		log.info("Welcome loginCtr.do!");

		String viewUrl = "";

		UserDto login = userService.login(userDto);
		// -> 로그인 실패 : login에 null 저장

		// 로그인 성공 여부 확인
		// login 값이 null이면 로그인 실패
		if (login == null) {
			// (1) 로그인 실패 시 session에 null 저장
			session.setAttribute("login", null);
			System.out.println("실패 " + session.getAttribute("login")); // (2) 로그인 페이지로 다시 이동
			viewUrl = "redirect:login.do";

		} else { // userVO 값이 null이 아닌 경우 로그인 성공
			session.setAttribute("login", login); // // (1) 로그인 성공 시 session에 userVO 값을 저장
			System.out.println("성공 " + session.getAttribute("login")); // (2) 일단 페인 페이지로 리다이렉트
			viewUrl = "redirect:../board/postList.do";

		}
		return viewUrl;
	}

	// 3. 로그아웃 요청(/user/logout)처리 메소드
	@RequestMapping(value = "logout.do", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		log.info("Welcome logout.do!");
		// 1) session 초기화
		session.invalidate();
		// 2) 메인 페이지로 리다이렉트
		return "redirect:/";

	}

	// 4. 회원가입 페이지로 이동 (/user/join)처리 메소드
	@RequestMapping(value = "join.do", method = RequestMethod.GET)
	public String join() {
		log.info("Welcome join.do!");
		return "user/join";
	}

	// 5. 실제 회원 가입 요청 처리 메소드
	@RequestMapping(value = "joinCtr.do", method = RequestMethod.POST)
	public String joinCtr(@RequestParam("id") String id, UserDto userDto, HttpServletRequest request, RedirectAttributes attr) {
		log.info("Welcome joinCtr.do!" + userDto);
		log.info("Welcome joinCtr.do!" + request);
		log.info("Welcome joinCtr.do!" + id);
		// 1) service 메소드 실행
		userService.insertUser(userDto, request);
		// 2) 성공 메세지 저장
		attr.addFlashAttribute("msg", "회원 가입을 성공하였습니다.");
		// 3) home 페이지로 리다이렉트
		return "redirect:/";

	}

	@RequestMapping(value = "idCheck.do", method = RequestMethod.GET)
	@ResponseBody
	public Boolean idCheck(@RequestParam("id") String id) {
		// 1) UserService의 idCheck 메소드를 실행해서 결과 저장
		System.out.println("아이디 들어와썽? " + id);
		boolean result = userService.idCheck(id);
		System.out.println("결과 잘나와? " + result);
		// 2) Map을 선언해서 result의 결과를 "result" 키로 map에 저장

		// 3) map에 저장한 결과를 리턴함
		return result;

	}

}
