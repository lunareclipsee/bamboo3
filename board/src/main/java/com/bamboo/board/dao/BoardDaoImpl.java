package com.bamboo.board.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bamboo.board.model.BoardDto;
import com.bamboo.board.model.ReplyDto;

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

	// 댓글부분

	// 댓글 리스트

	@Override

	public List<ReplyDto> replyList(int BOARD_IDX) {

		return sqlSession.selectList("board.replyList", BOARD_IDX);

	}

	@Override

	public int replyAdd(ReplyDto replyDto) {

		return sqlSession.insert("board.replyAdd", replyDto);

	}

	@Override

	public int replyRevise(ReplyDto replyDto) {

		return sqlSession.update("board.replyRevise", replyDto);

	}

	@Override

	public int replyDelete(int REPLY_IDX) {

		return sqlSession.delete("board.replyDelete", REPLY_IDX);

	}

}
