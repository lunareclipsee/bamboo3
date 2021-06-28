package com.bamboo.board.service;

import javax.servlet.http.HttpServletRequest;

import com.bamboo.board.model.UserDto;

public interface UserService {

	// 1. 로그인 처리 메소드 선언
	public UserDto login(UserDto userDto);

	// 2. 아이디 중복체크 처리
	// 아이디 중복 -> false, 중복아님 true로 리턴 예정
	public boolean idCheck(String id);

	// 3. 회원 가입
	public void insertUser(UserDto UserDto, HttpServletRequest request);

}
