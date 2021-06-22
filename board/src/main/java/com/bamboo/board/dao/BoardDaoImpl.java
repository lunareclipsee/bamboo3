package com.bamboo.board.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bamboo.board.model.BoardDto;

@Repository("BoardDao")
public class BoardDaoImpl implements BoardDao {

	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<BoardDto> postList(BoardDto boardDto) {
		return sqlSession.selectList("board.list", boardDto);
	}

	@Override
	public int postAdd(BoardDto boardDto) {
		return sqlSession.insert("board.add", boardDto);
	}
	
	@Override
	public BoardDto postSelect(BoardDto boardDto) {
		return sqlSession.selectOne("board.select", boardDto);
	}

	@Override
	public int postRevise(BoardDto boardDto) {
		return sqlSession.update("board.revise", boardDto);
	}

	@Override
	public int postDelete(BoardDto boardDto) {
		return sqlSession.delete("board.delete", boardDto);
	}

	@Override
	public boolean pwdCheck(int idx, String password) {

		boolean result = false;
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("idx", idx);
		map.put("password", password);
		
		int cnt = sqlSession.selectOne("board.check", map);
		
		if (cnt == 1) result = true;
		return result;
	}

}
