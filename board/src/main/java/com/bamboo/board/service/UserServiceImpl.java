package com.bamboo.board.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bamboo.board.dao.UserDao;
import com.bamboo.board.model.UserDto;

@Service // 서비스 클래스 선언
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Autowired
    private PasswordEncoder passwordEncoder;
	
	// 1. 로그인 처리 메소드
	@Override
	public UserDto login(UserDto userDto) {

		return userDao.login(userDto);
	}

	// 2. 아이디 중복 체크
	@Override
	public boolean idCheck(String id) {
		// 1) userDao.iDcheck 실행해서 리턴 값 가져오기
		String result = userDao.idCheck(id);
		// 2) 중복 여부 확인
		if (result == null) {
			// 중복되는 아이디가 없는 경우 result == null
			return true;
		}
		// result 가 null이 아니면 아이디 중복
		return false;
	}

	// 3. 회원 가입 **** 중요
	@Override
	public void insertUser(UserDto userDto, HttpServletRequest request) {
		String encodedPassword = passwordEncoder.encode(userDto.getPassword());
		userDto.setPassword(encodedPassword);
		userDao.insertUser(userDto);
	}
}
