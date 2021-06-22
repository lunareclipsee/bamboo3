package com.bamboo.board.controller;

import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bamboo.board.model.BoardDto;
import com.bamboo.board.service.BoardService;

@Controller
@RequestMapping("/bambooforest/*")
public class BoardController {

	private static final Logger log = LoggerFactory.getLogger(BoardController.class);

	@Autowired
	BoardService BoardService;

	@RequestMapping("/index")
	private String indexMain() {

		return "index";
	}

	// 게시글 리스트
	@RequestMapping(value = "postList.do")
	public String list(BoardDto boardDto, Model model) {
		log.info("Welcome postList.do!");

		List<BoardDto> postList = BoardService.postList(boardDto);

		model.addAttribute("postList", postList);

		return "bambooforest/postList";
	}

	// 게시글 작성
	@RequestMapping(value = "postAdd.do")
	public String add() {
		log.info("Welcome postAdd.do!");

		return "bambooforest/postAdd";
	}

	// 게시글 저장
	@RequestMapping(value = "postAddCtr.do")
	public String add(BoardDto boardDto, HttpServletRequest request, Model model) {
		log.info("Welcome postAddCtr.do!");

		int resultNum = 0;
		boardDto.setInip(request.getLocalAddr()); // ip넣기
		resultNum = BoardService.postAdd(boardDto);

		System.out.println("게시글 작성완료 결과값" + resultNum);

		return "redirect:/bambooforest/postList.do";
	}

	// 게시글 조회
	@RequestMapping(value = "postSelect.do")
	public String select(BoardDto boardDto, Model model) {
		log.info("Welcome postSelect.do!");

		BoardDto resultBoardDto = BoardService.postSelect(boardDto);

		model.addAttribute("boardDto", resultBoardDto);

		return "bambooforest/postRead";
	}

	// 수정 권한조회
	@RequestMapping(value = "*/pwdCheck.do")
	public String pwdCheck(BoardDto boardDtom, HttpServletRequest request, Model model) {
		log.info("Welcome postRevise.do! {}");

		
		model.addAttribute("URL", request.getRequestURL());
		
		return "bambooforest/pwdCheck";
	}

	// 게시글 수정페이지
	@RequestMapping(value = "*/postRevise.do")
	public String revise(BoardDto boardDto, Model model) {
		log.info("Welcome postRevise.do! {}");

		boolean result = BoardService.pwdCheck(boardDto.getIdx(), boardDto.getPassword());
		
		if (result) {
			BoardDto resultBoardDto = BoardService.postSelect(boardDto);
			model.addAttribute("boardDto", resultBoardDto);
			return "bambooforest/postRevise";
		} else if (boardDto.getPassword() == "") {
			model.addAttribute("message", "비밀번호를 입력해주세요.");
			return "bambooforest/pwdCheck";
		} else {
			model.addAttribute("message", "비밀번호가 틀렸습니다.");
			return "bambooforest/pwdCheck";
		}
	}

	// 게시글 수정하기
	@RequestMapping(value = "postReviseCtr.do")
	public String reviseCtr(BoardDto boardDto) {
		log.info("Welcome postReviseCtr.do! ");

		int resultNum = 0;
		resultNum = BoardService.postRevise(boardDto); // 성공 1 실패 0
		int idx = boardDto.getIdx();
		return "redirect:/bambooforest/postSelect.do?idx=" + idx;
	}

	// 게시글 삭제
	@RequestMapping(value = "postDeleteCtr.do")
	public String deleteCtr(BoardDto boardDto, Model model) {
		log.info("call postDeleteCtr! ");
		System.out.println(boardDto);
		int resultNum = 0;
		resultNum = BoardService.postDelete(boardDto);
		// 성공 1 실패 0
		System.out.println("삭제 서비스 완료");

		return "redirect:/bambooforest/postList.do";
	}

}
