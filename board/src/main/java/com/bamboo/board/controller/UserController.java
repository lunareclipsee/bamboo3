package com.bamboo.board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bamboo.board.model.UserDto;
import com.bamboo.board.service.UserService;

@Controller // 컨트롤러 클래스 선언
@RequestMapping("/user/*")
public class UserController {
	private static final Logger log = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	// 1. 로그인 페이지로 이동 요청(/user/login) 처리
	@RequestMapping(value = "login", method = { RequestMethod.GET, RequestMethod.POST })
	public void goLogin() {
		log.info("Welcome login!");
//		System.out.println("login 페이지 이동");
		// 단순 페이지 이동
		// 리턴 타입 - void ==> 받은 요청과 같은 이름의 뷰 페이지로 이동
		// user/login.jsp 페이지로 이동
	}

	// 2. 로그인 처리 요청(/user/loginCtr) 처리 메소드
	// -> 로그인 처리 시 로그인 성공 결과는 HttpSession에 저장해야함 ****
	@RequestMapping(value = "loginCtr", method = RequestMethod.POST)
	public String loginCtr(UserDto userDto, HttpSession session, Model model, RedirectAttributes attr) {
		log.info("Welcome loginCtr!");

		String viewUrl = "";

		UserDto login = userService.login(userDto);
		// -> 로그인 실패 : login에 null 저장

		// 로그인 성공 여부 확인
		// login 값이 null이면 로그인 실패
		if (login == null) {
			// (1) 로그인 실패 시 session에 null 저장
			session.setAttribute("login", null);
			System.out.println("실패 " + session.getAttribute("login")); // (2) 로그인 페이지로 다시 이동
			attr.addFlashAttribute("msg", "입력하신 정보가 올바르지 않습니다. 다시입력해 주세요");
			viewUrl = "redirect:login";

		} else { // userVO 값이 null이 아닌 경우 로그인 성공
			session.setAttribute("login", login); // // (1) 로그인 성공 시 session에 userVO 값을 저장
			System.out.println("성공 " + session.getAttribute("login")); // (2) 일단 페인 페이지로 리다이렉트
			// 세션값 확인
			// UserDto sessionUserDto = (UserDto) session.getAttribute("login");
			// System.out.println("세션에 뭐있냐?"+sessionUserDto);
			viewUrl = "redirect:../board/postList";

		}
		return viewUrl;
	}

	// 3. 로그아웃 요청(/user/logout)처리 메소드
	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		log.info("Welcome logout!");
		// 1) session 초기화
		session.invalidate();
		// 2) 메인 페이지로 리다이렉트
		return "redirect:/";

	}

	// 4. 회원가입 페이지로 이동 (/user/join)처리 메소드
	@RequestMapping(value = "join", method = RequestMethod.GET)
	public String join() {
		log.info("Welcome join!");
		return "user/join";
	}

	// 5. 실제 회원 가입 요청 처리 메소드
	@RequestMapping(value = "joinCtr", method = RequestMethod.POST)
	public String joinCtr(
			@RequestParam("id") String id, UserDto userDto, HttpServletRequest request,
			RedirectAttributes attr) {
		log.info("Welcome joinCtr! " + id);
		// 1) service 메소드 실행
		userService.insertUser(userDto, request);
		// 2) 성공 메세지 저장
		attr.addFlashAttribute("msg", "회원 가입을 성공하였습니다.");
		// 3) home 페이지로 리다이렉트
		return "redirect:/";

	}

	@ResponseBody
	@RequestMapping(value = "idCheck", method = RequestMethod.GET)
	public Boolean idCheck(@RequestParam("id") String id) {
		// 1) UserService의 idCheck 메소드를 실행해서 결과 저장
		boolean result = userService.idCheck(id);

		return result;

	}
	
	@ResponseBody
	@RequestMapping(value = "nameCheck", method = RequestMethod.GET)
	public Boolean nameCheck(@RequestParam("name") String name) {
		// 1) UserService의 idCheck 메소드를 실행해서 결과 저장
		boolean result = userService.nameCheck(name);

		return result;

	}

	@RequestMapping(value = "myPage.do", method = RequestMethod.GET)
	public String myPage(HttpSession session, Model model) {
		log.info("Welcome myPage!");

		// 1) 세션값
		UserDto sessionUserDto = (UserDto) session.getAttribute("login");

		model.addAttribute("userInfo", sessionUserDto);

		return "user/myPage";

	}

	// 비밀번호 확인페이지 이동
	@RequestMapping(value = "pwdCheck.do") // */ 으로 revise, delete
																							// 구분해서 같이씀
	public String pwdCheck(@ModelAttribute UserDto UserDto, HttpServletRequest request, Model model) {
		log.info("Welcome User pwdCheck!");
	
		model.addAttribute("userInfo", UserDto);
		model.addAttribute("URL", request.getRequestURL());

		return "user/pwdCheck";
	}

	@ResponseBody
	@RequestMapping(value = "pwdCheckCtr.do", method = RequestMethod.POST)
	public boolean pwCheck(@ModelAttribute UserDto UserDto) {
		log.info("Welcome User pwdCheck.do!");
		// 1) UserService의 idCheck 메소드를 실행해서 결과 저장
		boolean result = userService.pwCheck(UserDto);
System.out.println(result);
		return result;
	}

	// 회원정보 수정페이지
	@RequestMapping(value = "reviseUser.do")
	public String reviseUser(@ModelAttribute UserDto UserDto, Model model) {
		log.info("Welcome infoRevise!");
		model.addAttribute("userInfo", UserDto);

		return "user/reviseUser";
	}

	// 회원정보 수정
	@RequestMapping(value = "reviseUserCtr.do", method = RequestMethod.POST)
	public String reviseUserCtr(@ModelAttribute UserDto userDto, HttpSession session) {
		log.info("Welcome infoReviseCtr! ");
		String viewUrl = "";

		// result 는 업데이된 자료
		UserDto result = userService.reviseUser(userDto);

		session.setAttribute("login", result);

		viewUrl = "redirect:../user/myPage.do";

		return viewUrl;
	}

	// 회원 탈퇴
	@RequestMapping(value = "withdrawCtr.do", method = RequestMethod.POST)
	public String withdrawCtr(HttpSession session, Model model, RedirectAttributes attr) {

		log.info("call withdrawCtr!");

		UserDto sessionUserDto = (UserDto) session.getAttribute("login");

		String id = sessionUserDto.getId();
		int result = userService.withdrawUser(id);

		if (result==1) {
			attr.addFlashAttribute("msg", "회원탈퇴가 완료되었습니다.");
			System.out.println("탈퇴 서비스 완료");
			session.removeAttribute("login");
			session.invalidate();
		} else {
			System.out.println("탈퇴 서비스 실패");
		}

		return "redirect:/";
	}

}
