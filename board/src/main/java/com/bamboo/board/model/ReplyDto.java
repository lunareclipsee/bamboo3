package com.bamboo.board.model;

import java.util.Date;

public class ReplyDto {

	private int reply_idx; // 댓글 번호
	private int board_idx; // 댓글이 등록될 게시글 번호
	private String reply_name; // 댓글 작성자 닉네임
	private String reply_content; // 내용
	private Date reply_indate; // 작성시간

	public int getReply_idx() {
		return reply_idx;
	}

	public void setReply_idx(int reply_idx) {
		this.reply_idx = reply_idx;
	}

	public int getBoard_idx() {
		return board_idx;
	}

	public void setBoard_idx(int board_idx) {
		this.board_idx = board_idx;
	}

	public String getReply_name() {
		return reply_name;
	}

	public void setReply_name(String reply_name) {
		this.reply_name = reply_name;
	}

	public String getReply_content() {
		return reply_content;
	}

	public void setReply_content(String reply_content) {
		this.reply_content = reply_content;
	}

	public Date getReply_indate() {
		return reply_indate;
	}

	public void setReply_indate(Date reply_indate) {
		this.reply_indate = reply_indate;
	}

	@Override
	public String toString() {
		return "ReplyDto [reply_idx=" + reply_idx + ", board_idx=" + board_idx + ", reply_name=" + reply_name
				+ ", reply_content=" + reply_content + ", reply_indate=" + reply_indate + "]";
	}

}
