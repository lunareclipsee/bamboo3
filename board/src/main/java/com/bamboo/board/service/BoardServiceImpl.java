package com.bamboo.board.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bamboo.board.dao.BoardDao;
import com.bamboo.board.model.BoardDto;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	BoardDao BoardDao; // --- Dao 빈 주입

	@Override
	public List<BoardDto> postList(BoardDto boardDto) {
		return BoardDao.postList(boardDto);
	}

	@Override
	public int postAdd(BoardDto boardDto) {
		int resultNum = 0;
		resultNum= BoardDao.postAdd(boardDto);
		return resultNum;
	}
	
	@Override
	public int postBlock(String userIp, int blockTime) {
		int resultNum = 0;
		resultNum= BoardDao.postBlock(userIp, blockTime);
		return resultNum;
	}
	
	@Override
	public BoardDto postSelect(BoardDto boardDto) {
		return BoardDao.postSelect(boardDto);
	}

	@Override
	public int pwdCheck(BoardDto boardDto) {
		int resultNum = 0;
		resultNum = BoardDao.pwdCheck(boardDto);
		return resultNum;
	}
	
	@Override
	public int postRevise(BoardDto boardDto) {
		int resultNum = 0;
		resultNum = BoardDao.postRevise(boardDto);
		return resultNum;
	}

	@Override
	public int postDelete(BoardDto boardDto) {
		int resultNum = 0;
		resultNum= BoardDao.postDelete(boardDto);
		return resultNum;
	}

}
