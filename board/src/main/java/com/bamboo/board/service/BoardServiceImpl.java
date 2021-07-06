package com.bamboo.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bamboo.board.dao.BoardDao;
import com.bamboo.board.model.BoardDto;
import com.bamboo.board.model.ReplyDto;


@Service
public class BoardServiceImpl implements BoardService {

	
	@Autowired
	BoardDao BoardDao; // --- Dao 빈 주입

	@Override
	public int postCnt(int idx) {
		
		return BoardDao.postCnt(idx);
	}

	@Override
	public int postCurPage(int idx) {
		
		return BoardDao.postCurPage(idx);
	}
	
	@Override
	public List<BoardDto> postList(BoardDto boardDto) {

		return BoardDao.postList(boardDto);
	}

	@Override
	public int postAdd(BoardDto boardDto) {

		return BoardDao.postAdd(boardDto);
	}
	
	@Override
	public int setGroupord(BoardDto boardDto) {

		return BoardDao.setGroupord(boardDto);
	}

	@Override
	public int setAnswerno(BoardDto boardDto) {
	
		return BoardDao.setAnswerno(boardDto);
	}

	@Override
	public int postBlock(String userIp, int blockTime) {

		return BoardDao.postBlock(userIp, blockTime);
	}

	@Override
	public BoardDto postSelect(BoardDto boardDto) {
	
		return BoardDao.postSelect(boardDto);
	}

	@Override
	public int pwdCheck(BoardDto boardDto) {

		return BoardDao.pwdCheck(boardDto);
	}

	@Override
	public int postRevise(BoardDto boardDto) {

		return BoardDao.postRevise(boardDto);
	}

	@Override
	public int postDelete(BoardDto boardDto) {

		return BoardDao.postDelete(boardDto);
	}
	
	//자식찾기
	@Override
	public int findChild(int idx) {

		return BoardDao.findChild(idx);
	}

	// 댓글 리스트

	@Override
	public List<ReplyDto> replyList(int BOARD_IDX) {

		return BoardDao.replyList(BOARD_IDX);
	}

	@Override
	public int replyAdd(ReplyDto replyDto) {

		return BoardDao.replyAdd(replyDto);
	}

	@Override
	public int replyRevise(ReplyDto replyDto) {

		return BoardDao.replyRevise(replyDto);
	}

	@Override
	public int replyDelete(int REPLY_IDX) {

		return BoardDao.replyDelete(REPLY_IDX);
	}

	@Override
	public int getGroupno() {

		return BoardDao.getGroupno();
	}

	@Override
	public List<BoardDto> postList(int idx, int start, int end) {
	
		List<BoardDto> postList = BoardDao.postList(idx, start, end);
		
		return postList;
	}

}
