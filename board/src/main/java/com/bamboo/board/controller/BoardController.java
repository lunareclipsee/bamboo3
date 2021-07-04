package com.bamboo.board.controller;

import java.util.List;

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

import com.bamboo.board.model.BoardDto;
import com.bamboo.board.model.ReplyDto;
import com.bamboo.board.model.UserDto;
import com.bamboo.board.service.BoardService;

@Controller
@RequestMapping("/board/*")
public class BoardController {

	private static final Logger log = LoggerFactory.getLogger(BoardController.class);

	@Autowired
	BoardService BoardService;

	// 계층형 게시글 리스트
	@RequestMapping(value = "postList", method = RequestMethod.GET)
	public String list(@ModelAttribute BoardDto boardDto, HttpSession session, Model model) {
		log.info("Welcome postList!");
		
		UserDto temp = (UserDto) session.getAttribute("login");
		List<BoardDto> postList = BoardService.postList(boardDto);
		System.out.println(postList);
		model.addAttribute("login", temp);
		model.addAttribute("postList", postList);

		return "board/postList";
	}
	
//	// 게시글 리스트
//	@RequestMapping(value = "postList", method = RequestMethod.GET)
//	public String list(@ModelAttribute BoardDto boardDto, HttpSession session, Model model) {
//		log.info("Welcome postList!");
//		UserDto temp = (UserDto) session.getAttribute("login");
//		List<BoardDto> postList = BoardService.postList(boardDto);
//		System.out.println(postList);
//		model.addAttribute("login", temp);
//		model.addAttribute("postList", postList);
//		
//		return "board/postList";
//	}

	// 게시글 작성
	@RequestMapping(value = "postAdd.do")
	public String add(@ModelAttribute BoardDto boardDto, HttpSession session, Model model) {
		log.info("Welcome postAdd!");
		UserDto temp = (UserDto) session.getAttribute("login");
		String userName = temp.getName();
		String userId = temp.getId();

		model.addAttribute("userName", userName);
		model.addAttribute("userId", userId);
		return "board/postAdd";
	}
	
	// 게시글 저장
	@RequestMapping(value = "postAddCtr.do")
	public String addCtr(@ModelAttribute BoardDto boardDto, HttpServletRequest request, Model model) {
		log.info("Welcome postAddCtr!");
		
		//그룹넘버 가져오기
		int groupno = BoardService.getGroupno(); 
		String userIp = request.getLocalAddr();
		
		boardDto.setGroupno(groupno);
		boardDto.setInip(userIp); // ip넣기

		BoardService.postAdd(boardDto);

		return "redirect:postList";
	}

	// 도배방지
	@ResponseBody // 비동기
	@RequestMapping(value = "postCnt")
	public int postCnt(@ModelAttribute BoardDto boardDto, HttpServletRequest request, Model model) {
		log.info("Welcome postCnt!"); // HttpServletRequest ip 받아오기위해

		String userIp = request.getLocalAddr();
		int postCnt = 0; // 작성글 수
		int blockTime = 1; // 도배방지 시간

		boardDto.setInip(userIp); // ip넣기

		postCnt = BoardService.postBlock(userIp, blockTime);

		log.info(blockTime + "분 동안 게시글 " + postCnt + "개 올렸음");

		return postCnt;

	}

	// 게시글 상세보기
	@RequestMapping(value = "postSelect.do")
	public String select(@ModelAttribute BoardDto boardDto, HttpSession session, Model model) {
		log.info("Welcome postSelect! idx=" + boardDto.getIdx() + "번 게시글 조회");

		UserDto login = (UserDto) session.getAttribute("login");
		BoardDto resultBoardDto = BoardService.postSelect(boardDto);
		
		model.addAttribute("login", login);
		model.addAttribute("boardDto", resultBoardDto);
		
		return "board/postRead";
	}
	
	// 게시글 수정페이지
	// 비로그인 막아야함
	@RequestMapping(value = "postRevise.do", method = RequestMethod.POST)
	public String revise(@ModelAttribute BoardDto boardDto, HttpSession session, Model model) {
		log.info("Welcome postRevise!");

		UserDto login = (UserDto) session.getAttribute("login");
		//혹시 변경된 아이디 있을지도 세션엔 비번도 있음
		BoardDto resultBoardDto = BoardService.postSelect(boardDto);
		resultBoardDto.setName(login.getName()); 
		
		model.addAttribute("boardDto", resultBoardDto);

		return "board/postRevise";
	}

	// 게시글 수정
	// 비로그인 막아야함
	@RequestMapping(value = "postReviseCtr.do", method = RequestMethod.POST)
	public String reviseCtr(@ModelAttribute BoardDto boardDto) {
		log.info("Welcome postReviseCtr! ");
		BoardService.postRevise(boardDto);
		int idx = boardDto.getIdx();
		log.info("idx=" + boardDto.getIdx() + "번 게시글 수정완료");

		return "redirect:postSelect.do?idx=" + idx;
	}

	// 게시글 삭제
	// 비로그인 막아야함
	@RequestMapping(value = "postDeleteCtr.do")
	public String deleteCtr(@ModelAttribute BoardDto boardDto) {
		log.info("call postDeleteCtr! " + boardDto);

		BoardService.postDelete(boardDto);
		log.info("idx=" + boardDto.getIdx() + "번 게시글 삭제완료");

		return "redirect:./postList";
	}

	// 댓글 불러오기
	@ResponseBody
	@RequestMapping(value = "replyList.do", method = RequestMethod.POST)
	public List<ReplyDto> getBoardContent(@RequestParam int board_idx, Model model) {

		List<ReplyDto> replyList = BoardService.replyList(board_idx);
	
		return replyList;
	}

	// 댓글 저장
	@ResponseBody
	@RequestMapping(value = "replyAdd.do", method = RequestMethod.POST)
	public int replyAdd(@ModelAttribute ReplyDto replyDto, HttpSession session, Model model) {
		
		UserDto temp = (UserDto) session.getAttribute("login");
		String userName = temp.getName();
		
		replyDto.setReply_name(userName);
		int result = BoardService.replyAdd(replyDto);
				
		model.addAttribute("Reply", replyDto);
		
		return result;
	}
	
	//댓글 수정
	@ResponseBody
	@RequestMapping(value = "replyRevise.do", method = RequestMethod.POST)
	public int replyRevise(@ModelAttribute ReplyDto replyDto, HttpSession session, Model model) {
		
		int result = BoardService.replyRevise(replyDto);
		
		model.addAttribute("Reply", replyDto);
		
		return result;
	}
	
	//댓글 삭제
	@ResponseBody
	@RequestMapping(value = "replyDelete.do", method = RequestMethod.POST)
	public int replyDelete(@ModelAttribute ReplyDto replyDto, HttpSession session) {
		
		int result = BoardService.replyDelete(replyDto.getReply_idx());
		
		return result;
	}

}
