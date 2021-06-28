package com.bamboo.board.model;

import org.apache.ibatis.type.Alias;

@Alias("UserDto")
public class UserDto {

	private String id; // 아이디
	private String name; // 닉네임
	private String password; // 비밀번호

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "userDto [id=" + id + ", name=" + name + ", password=" + password + "]";
	}

}
