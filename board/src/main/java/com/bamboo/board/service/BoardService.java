package com.bamboo.board.service;

import java.util.List;

import com.bamboo.board.model.BoardDto;
import com.bamboo.board.model.ReplyDto;

public interface BoardService {

	public List<BoardDto> postList(BoardDto boardDto);

	public int postCnt(int idx);

	public int postCurPage(int idx);
	
	public int postAdd(BoardDto boardDto);

	public int postBlock(String userIp, int blockTime);
	
	public BoardDto postSelect(BoardDto boardDto);
	
	public int pwdCheck(BoardDto boardDto);
	
	public int postRevise(BoardDto boardDto);

	public int postDelete(BoardDto boardDto);

	// 댓글 부분
	public List<ReplyDto> replyList(int BOARD_IDX);

	public int replyAdd(ReplyDto replyDto);

	public int replyRevise(ReplyDto replyDto);

	public int replyDelete(int REPLY_IDX);

	public int getGroupno();

	public int setGroupord(BoardDto boardDto);

	public int setAnswerno(BoardDto boardDto);

	public List<BoardDto> postList(int idx, int start, int end);

	public int findChild(int idx);

}
