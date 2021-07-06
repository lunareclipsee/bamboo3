package com.bamboo.board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
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
import com.bamboo.board.util.Paging;

@Controller
@RequestMapping("/board/*")
public class BoardController {

	private static final Logger log = LoggerFactory.getLogger(BoardController.class);

	@Autowired
	BoardService BoardService;
	
	// 계층형 게시글 리스트
	@RequestMapping(value = "postList", method = RequestMethod.GET)
	public String list(@RequestParam(defaultValue = "1") int curPage,
			@RequestParam(defaultValue = "0") int idx, HttpSession session, Model model) {
		log.info("Welcome postList!");
		
		int totalCount = 0;

		totalCount = BoardService.postCnt(idx);

		Paging postPaging = new Paging(totalCount, curPage);
		
		int start = postPaging.getPageBegin();
		int end = postPaging.getPageEnd();
		
		UserDto login = (UserDto) session.getAttribute("login");
		
		List<BoardDto> postList = BoardService.postList(idx, start, end);
		
		Map<String, Object> pagingMap = new HashMap<>();
		pagingMap.put("totalCount", totalCount);
		pagingMap.put("postPaging", postPaging);
		
		model.addAttribute("login", login);
		model.addAttribute("postList", postList);
		model.addAttribute("pagingMap", pagingMap);
		
		return "board/postList";
	}

	// 게시글 작성
	@RequestMapping(value = "postAdd.do")
	public String add(@ModelAttribute BoardDto boardDto, Model model) {
		log.info("Welcome postAdd!" + boardDto);
		return "board/postAdd";
	}

	// 게시글 저장
	@RequestMapping(value = "postAddCtr.do")
	public String addCtr(BoardDto boardDto, HttpServletRequest request, Model model) {
		log.info("Welcome postAddCtr!");
		System.out.println("들어오는게있음?"+boardDto);
		HttpSession session = request.getSession();
		UserDto login = (UserDto) session.getAttribute("login");
		
		boardDto.setId(login.getId());
		boardDto.setName(login.getName());
		boardDto.setInip(request.getLocalAddr()); // ip넣기

		if (boardDto.getGroupno() < 1) {
			// 새글쓰기
			// 다음 auto inc 값 가져옴
			int newGroupno = BoardService.getGroupno();
			boardDto.setGroupno(newGroupno);
		} else {
			// 답글달기
			// 기존에 있는 값들 1씩 올림
			BoardService.setGroupord(boardDto);

			// 그 빈자리에 들어갈 값
			int newGroupord = boardDto.getGroupord() + 1;
			int newDepth = boardDto.getDepth() + 1;
			int newPaentno = boardDto.getIdx();

			boardDto.setGroupord(newGroupord);
			boardDto.setDepth(newDepth);
			boardDto.setParentno(newPaentno);
			// 답의 이전 답글의 Groupord를 하나씩 올려야함
		}

		BoardService.postAdd(boardDto);

		return "redirect:postList";
	}

	// 도배방지
	@ResponseBody // 비동기
	@RequestMapping(value = "postCnt")
	public int postCnt(@ModelAttribute BoardDto boardDto, HttpServletRequest request, Model model) {
		log.info("Welcome postCnt!"+boardDto); // HttpServletRequest ip 받아오기위해
		// js에서 갯수 조정했음
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
	public String select(BoardDto boardDto, int curPage, HttpSession session, Model model) {
		log.info("Welcome postSelect! idx=" + boardDto.getIdx() + "번 게시글 조회");

		UserDto login = (UserDto) session.getAttribute("login");
		BoardDto resultBoardDto = BoardService.postSelect(boardDto);

		model.addAttribute("login", login);
		model.addAttribute("curPage", curPage);
		model.addAttribute("boardDto", resultBoardDto);

		return "board/postRead";
	}

	// 게시글 수정페이지
	// 비로그인 막아야함
	@RequestMapping(value = "postRevise.do", method = RequestMethod.POST)
	public String revise(@ModelAttribute BoardDto boardDto, HttpSession session, Model model) {
		log.info("Welcome postRevise!");

		UserDto login = (UserDto) session.getAttribute("login");
		// 혹시 변경된 아이디 있을지도 세션엔 비번도 있음
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

		int children = BoardService.findChild(boardDto.getIdx());
		System.out.println("자식이 어떻게 되세요? "+children);
		if (children != 0) {
			log.info("자식이 있으면 살아야지 업데이트 ㄱ");
			boardDto.setTitle("<del style='color:red'>삭제된 게시글입니다.</del>");
			BoardService.postRevise(boardDto);
		} else {
			log.info("자식없음? 가차없이 삭제");
			BoardService.postDelete(boardDto);
		}
		
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

	// 댓글 수정
	@ResponseBody
	@RequestMapping(value = "replyRevise.do", method = RequestMethod.POST)
	public int replyRevise(@ModelAttribute ReplyDto replyDto, HttpSession session, Model model) {

		int result = BoardService.replyRevise(replyDto);

		model.addAttribute("Reply", replyDto);

		return result;
	}

	// 댓글 삭제
	@ResponseBody
	@RequestMapping(value = "replyDelete.do", method = RequestMethod.POST)
	public int replyDelete(@ModelAttribute ReplyDto replyDto, HttpSession session) {

		int result = BoardService.replyDelete(replyDto.getReply_idx());

		return result;
	}

}
