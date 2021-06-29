package com.bamboo.board.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bamboo.board.model.BoardDto;
import com.bamboo.board.service.BoardService;

@Controller
@RequestMapping("/board/*")
public class BoardController {

	private static final Logger log = LoggerFactory.getLogger(BoardController.class);

	@Autowired
	BoardService BoardService;

	// 게시글 리스트
	@RequestMapping(value = "postList", method = RequestMethod.GET)
	public String list(@ModelAttribute BoardDto boardDto, Model model) {
		log.info("Welcome postList!");

		List<BoardDto> postList = BoardService.postList(boardDto);

		model.addAttribute("postList", postList);

		return "board/postList";
	}

	// 게시글 작성
	@RequestMapping(value = "postAdd")
	public String add() {
		log.info("Welcome postAdd!");

		return "board/postAdd";
	}

	// 도배방지 
	@RequestMapping(value = "postCnt")
	@ResponseBody // 비동기
	public int postCnt(@ModelAttribute BoardDto boardDto, HttpServletRequest request, Model model) {
		log.info("Welcome postCnt!");   		//HttpServletRequest ip 받아오기위해 

		String userIp = request.getLocalAddr();
		int postCnt = 0; // 작성글 수
		int blockTime = 10; // 도배방지 시간
		
		boardDto.setInip(userIp); // ip넣기
		
		postCnt = BoardService.postBlock(userIp, blockTime);
		
		log.info(blockTime+"분 동안 게시글 "+postCnt+"개 올렸음"); 

		return postCnt;

	}
	
	// 게시글 저장
	@RequestMapping(value = "postAddCtr")
	public String addCtr(@ModelAttribute BoardDto boardDto, HttpServletRequest request, Model model) {
		log.info("Welcome postAddCtr!");

		String userIp = request.getLocalAddr();
		boardDto.setInip(userIp); // ip넣기
		
		BoardService.postAdd(boardDto);

		return "redirect:postList";
	}

	// 게시글 상세보기
	@RequestMapping(value = "postSelect")
	public String select(@ModelAttribute BoardDto boardDto, Model model) {
		log.info("Welcome postSelect! idx=" + boardDto.getIdx() + "번 게시글 조회");

		BoardDto resultBoardDto = BoardService.postSelect(boardDto);

		model.addAttribute("boardDto", resultBoardDto);

		return "board/postRead";
	}

	// 비밀번호 확인페이지 이동
	@RequestMapping(value = "*/pwdCheck") //   */ 으로 revise, delete 구분해서 같이씀
	public String pwdCheck(@ModelAttribute BoardDto boardDtom, HttpServletRequest request, Model model) {
		log.info("Welcome pwdCheck!");

		model.addAttribute("URL", request.getRequestURL());
		
		return "board/pwdCheck";
	}

	// 비밀번호를 통한 권한조회
	@RequestMapping(value = "*/pwdCheckCtr")
	@ResponseBody // 이게 있어야 ajax에서 받을수 있다.
	public int pwdcheckCtr(@ModelAttribute BoardDto boardDto, Model model) {
		log.info("Welcome pwdCheckCtr!");
		
		int resultNum = 0;
		resultNum = BoardService.pwdCheck(boardDto);
		
		log.info("1 = 통과o / 0 = 통과x : " + resultNum);

		return resultNum;

	}

	// 게시글 수정페이지
	@RequestMapping(value = "*/postRevise")
	public String revise(@ModelAttribute BoardDto boardDto, Model model) {
		log.info("Welcome postRevise!");

		BoardDto resultBoardDto = BoardService.postSelect(boardDto);
		model.addAttribute("boardDto", resultBoardDto);
		
		return "board/postRevise"; 
	}

	// 게시글 수정
	@RequestMapping(value = "postReviseCtr")
	public String reviseCtr(@ModelAttribute BoardDto boardDto) {
		log.info("Welcome postReviseCtr! ");

		BoardService.postRevise(boardDto);
		int idx = boardDto.getIdx();
		log.info("idx=" + boardDto.getIdx() + "번 게시글 수정완료");
		
		return "redirect:postSelect?idx=" + idx;
	}

	// 게시글 삭제
	@RequestMapping(value = "*/postDeleteCtr")
	public String deleteCtr(@ModelAttribute BoardDto boardDto) {
		log.info("call postDeleteCtr! ");

		BoardService.postDelete(boardDto);
		log.info("idx=" + boardDto.getIdx() + "번 게시글 삭제완료");
		
		return "redirect:../postList";
	}
}
