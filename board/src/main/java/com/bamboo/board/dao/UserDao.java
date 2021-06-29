package com.bamboo.board.dao;

import com.bamboo.board.model.UserDto;

// SpringUser 테이블에 대한 데이터베이스 작업 메소드 소유한 인터페이스

public interface UserDao {

	// 1. 로그인 처리 작업
	// -> 사용자가 입력한 id와 pw를 매개변수로 받아서 일치할 경우 데이터를 리턴
	public UserDto login(UserDto userDto);

	// 2. 아이디 중복 체크
	public String idCheck(String id);

	// 3. 회원 가입
	// UserVO - User 테이블과 연결
	// UserDTO - 회원 가입 form과 연결
	public int insertUser(UserDto userDto);

	public String getUserPw(String id);

	public int reviseUser(UserDto userDto);

	public UserDto getUserInfo(String id);

	public int withdrawUser(String id);

	public String nameCheck(String name);
	
}
