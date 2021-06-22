package com.bamboo.board.dao;

import java.util.List;

import com.bamboo.board.model.BoardDto;

public interface BoardDao {

	public List<BoardDto> postList(BoardDto boardDto);

	public int postAdd(BoardDto boardDto);

	public BoardDto postSelect(BoardDto boardDto);

	public int postRevise(BoardDto boardDto);

	public int postDelete(BoardDto boardDto);

	public boolean pwdCheck(int idx, String password);

}
