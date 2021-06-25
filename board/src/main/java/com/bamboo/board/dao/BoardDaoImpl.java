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
	public int postBlock(String userIp, int blockTime) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("inip", userIp);
		map.put("blockTime", blockTime);
				
		return sqlSession.selectOne("board.cnt", map);
	}
	
	@Override
	public BoardDto postSelect(BoardDto boardDto) {
		return sqlSession.selectOne("board.select", boardDto);
	}

	@Override
	public int pwdCheck(BoardDto boardDto) {
		return sqlSession.selectOne("board.check", boardDto);
	}
	
	@Override
	public int postRevise(BoardDto boardDto) {
		return sqlSession.update("board.revise", boardDto);
	}

	@Override
	public int postDelete(BoardDto boardDto) {
		return sqlSession.delete("board.delete", boardDto);
	}

}
