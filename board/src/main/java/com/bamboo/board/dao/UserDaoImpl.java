package com.bamboo.board.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bamboo.board.model.BoardDto;
import com.bamboo.board.model.UserDto;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private SqlSession sqlSession;

	// 1. 로그인 SQL 연동
	@Override
	public UserDto login(UserDto userDto) {
		// id와 pw로 검색 했을 때 return 될 수 있는 값은 1개 -> selectOne
		return sqlSession.selectOne("user.login", userDto);
	}

	// 2. 아이디 중복 체크 SQL 연동
	@Override
	public String idCheck(String id) {

		return sqlSession.selectOne("user.idCheck", id);
	}
	
	@Override
	public String nameCheck(String name) {
		
		return sqlSession.selectOne("user.nameCheck", name);
	}

	// 3. 회원 가입 SQL 연동
	@Override
	public int insertUser(UserDto userDto) {

		return sqlSession.insert("user.insertUser", userDto);
	}

	@Override
	public String getUserPw(String id) {
		
		return sqlSession.selectOne("user.getUserPw", id);
	}

	@Override
	public int reviseUser(UserDto userDto) {
		
		return sqlSession.update("user.updateUser", userDto);
	}

	@Override
	public UserDto getUserInfo(String id) {
		
		return sqlSession.selectOne("user.getUserInfo", id);
	}

	@Override
	public int withdrawUser(String id) {

		return sqlSession.delete("user.deleteUser", id);
	}

}
