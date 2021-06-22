package com.bamboo.board.model;

import java.util.Date;

public class BoardDto {

	private int idx;
	private String name;
	private String title;
	private String content;
	private String password;
	private String inip;
	private Date indate;

	public BoardDto() {
		super();
	}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getInip() {
		return inip;
	}

	public void setInip(String inip) {
		this.inip = inip;
	}

	public Date getIndate() {
		return indate;
	}

	public void setIndate(Date indate) {
		this.indate = indate;
	}

	@Override
	public String toString() {
		return "BoardDto [idx=" + idx + ", name=" + name + ", title=" + title + ", content=" + content + ", password="
				+ password + ", inip=" + inip + ", indate=" + indate + "]";
	}

}
