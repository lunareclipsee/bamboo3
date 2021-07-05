package com.bamboo.board.model;

import java.util.Date;

public class BoardDto {

	private int idx; // 게시글 번호
	private String name; // 닉네임
	private String id; // 유저 아이디
	private String title; // 제목
	private String content; // 내용
	private String password; // 비밀번호
	private String inip; // 접속IP
	private Date indate; // 작성시간
	private int replycount; // 게시글에 달린 댓글 수
	private int groupno; // 그룹번호 원글은 자신의 값
	private int parentno; // 답답글의 부모
	private int groupord; // 원글(답글포함)에 대한 순서
	private int depth; // 답글 계층

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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public int getReplycount() {
		return replycount;
	}

	public void setReplycount(int replycount) {
		this.replycount = replycount;
	}

	public int getGroupno() {
		return groupno;
	}

	public void setGroupno(int groupno) {
		this.groupno = groupno;
	}

	public int getParentno() {
		return parentno;
	}

	public void setParentno(int parentno) {
		this.parentno = parentno;
	}

	public int getGroupord() {
		return groupord;
	}

	public void setGroupord(int groupord) {
		this.groupord = groupord;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	@Override
	public String toString() {
		return "BoardDto [idx=" + idx + ", name=" + name + ", id=" + id + ", title=" + title + ", content=" + content
				+ ", password=" + password + ", inip=" + inip + ", indate=" + indate + ", replycount=" + replycount
				+ ", groupno=" + groupno + ", parentno=" + parentno + ", groupord=" + groupord + ", depth=" + depth
				+ "]";
	}

}
