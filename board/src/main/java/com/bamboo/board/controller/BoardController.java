package com.bamboo.board.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

	// 도배방지 
	@RequestMapping(value = "postCnt.do")
	@ResponseBody // 비동기
	public int postCnt(BoardDto boardDto, HttpServletRequest request, Model model) {
		log.info("Welcome postCnt.do!");

		String userIp = request.getLocalAddr();
		int postCnt = 0; // 작성글 수
		int blockTime = 2; // 도배방지 시간
		
		boardDto.setInip(userIp); // ip넣기
		
		postCnt = BoardService.postBlock(userIp, blockTime);
		
		log.info(blockTime+"분 동안 게시글 "+postCnt+"개 올렸음"); 

		return postCnt;

	}
	
	// 게시글 저장
	@RequestMapping(value = "postAddCtr.do")
	public String addCtr(BoardDto boardDto, HttpServletRequest request, Model model) {
		log.info("Welcome postAddCtr.do!");

		String userIp = request.getLocalAddr();
		boardDto.setInip(userIp); // ip넣기
		
		BoardService.postAdd(boardDto);

		return "redirect:/bambooforest/postList.do";
	}

	// 게시글 상세보기
	@RequestMapping(value = "postSelect.do")
	public String select(BoardDto boardDto, Model model) {
		log.info("Welcome postSelect.do! idx=" + boardDto.getIdx() + "번 게시글 조회");

		BoardDto resultBoardDto = BoardService.postSelect(boardDto);

		model.addAttribute("boardDto", resultBoardDto);

		return "bambooforest/postRead";
	}

	// 수정 페이지 이동
	@RequestMapping(value = "*/pwdCheck.do")
	public String pwdCheck(BoardDto boardDtom, HttpServletRequest request, Model model) {
		log.info("Welcome pwdCheck.do!");

		model.addAttribute("URL", request.getRequestURL());
		
		return "bambooforest/pwdCheck";
	}

	// 권한조회
	@RequestMapping(value = "*/pwdCheckCtr.do")
	@ResponseBody // 비동기
	public int pwdcheckCtr(BoardDto boardDto, Model model) {
		log.info("Welcome pwdCheckCtr.do!");
		
		int resultNum = 0;
		resultNum = BoardService.pwdCheck(boardDto);
		
		log.info("1 = 통과o / 0 = 통과x : " + resultNum);

		return resultNum;

	}

	// 게시글 수정페이지
	@RequestMapping(value = "*/postRevise.do")
	public String revise(BoardDto boardDto, Model model) {
		log.info("Welcome postRevise.do!");

		BoardDto resultBoardDto = BoardService.postSelect(boardDto);
		model.addAttribute("boardDto", resultBoardDto);
		
		return "bambooforest/postRevise"; // jsp로 보냄
	}

	// 게시글 수정
	@RequestMapping(value = "postReviseCtr.do")
	public String reviseCtr(BoardDto boardDto) {
		log.info("Welcome postReviseCtr.do! ");

		BoardService.postRevise(boardDto);
		int idx = boardDto.getIdx();
		
		return "redirect:/bambooforest/postSelect.do?idx=" + idx;
	}

	// 게시글 삭제
	@RequestMapping(value = "*/postDeleteCtr.do")
	public String deleteCtr(BoardDto boardDto) {
		log.info("call postDeleteCtr! ");

		BoardService.postDelete(boardDto);
		log.info("idx=" + boardDto.getIdx() + "번 게시글 삭제완료");
		
		return "redirect:/bambooforest/postList.do";
	}
}
