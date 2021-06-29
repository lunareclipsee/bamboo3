package com.bamboo.board.service;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bamboo.board.dao.UserDao;
import com.bamboo.board.model.UserDto;

@Service // 서비스 클래스 선언
public class UserServiceImpl implements UserService {
	private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserDao userDao;

	@Autowired
	private PasswordEncoder passwordEncoder;

	// 1. 로그인 처리 메소드
	@Override
	public UserDto login(UserDto userDto) {
		String pw = userDao.getUserPw(userDto.getId());
		// log.info("DB 암호화 비밀번호" + pw);
		String rawPw = userDto.getPassword();
		// log.info("입력한 비밀번호" + rawPw);

		if (passwordEncoder.matches(rawPw, pw)) {
			log.info("비밀번호 일치");
			userDto.setPassword(pw);
		} else {
			log.info("비밀번호 불일치");
		}
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
	
	@Override
	public boolean nameCheck(String name) {
		String result = userDao.nameCheck(name);
		if (result == null) {
			return true;
		}
		return false;
	}

	// 3. 회원 가입 **** 중요
	@Override
	public void insertUser(UserDto userDto, HttpServletRequest request) {
		String encodedPassword = passwordEncoder.encode(userDto.getPassword());
		userDto.setPassword(encodedPassword);
		userDao.insertUser(userDto);
	}

	@Override
	public boolean pwCheck(UserDto userDto) {
		String pw = userDao.getUserPw(userDto.getId());
		// log.info("암호화 비밀번호" + pw);
		String rawPw = userDto.getPassword();
		// log.info("비밀번호" + rawPw);

		if (passwordEncoder.matches(rawPw, pw)) {
			// log.info("비밀번호 일치");
			return true;
		} else {
			// log.info("비밀번호 불일치");
			return false;
		}

	}

	@Override
	public UserDto reviseUser(UserDto userDto) {
		String id = userDto.getId();
		
		// 비밀번호 수정안함
		if (userDto.getPassword() == "") {
			String pw = userDao.getUserPw(id);
			userDto.setPassword(pw);
		}

		// 새로운 비밀번호 암호화
		String encodedPassword = passwordEncoder.encode(userDto.getPassword());
		userDto.setPassword(encodedPassword);

		userDao.reviseUser(userDto);
		// 업데이트 된 정보 result에 담아옴
		UserDto result = userDao.getUserInfo(id);

		return result;
	}

	@Override
	public int withdrawUser(String id) {
	
		int result = userDao.withdrawUser(id);
		
		return result;
	}

}
