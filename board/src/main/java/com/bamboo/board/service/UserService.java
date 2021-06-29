package com.bamboo.board.service;

import javax.servlet.http.HttpServletRequest;

import com.bamboo.board.model.UserDto;

public interface UserService {

	// 1. 로그인 처리 메소드 선언
	public UserDto login(UserDto userDto);

	// 2. 아이디, 닉네임 중복체크 처리
	// 아이디 중복 -> false, 중복아님 true로 리턴 예정
	public boolean idCheck(String id);

	// 닉네임 중복 -> false, 중복아님 true로 리턴 예정
	public boolean nameCheck(String name); 
	
	// 3. 회원 가입
	public void insertUser(UserDto UserDto, HttpServletRequest request);

	// 4. 회원정보변경 비밀번호 확인 

	public boolean pwCheck(UserDto userDto);

	// 5. 회원정보 수정
	public UserDto reviseUser(UserDto userDto);

	public int withdrawUser(String id);


}
