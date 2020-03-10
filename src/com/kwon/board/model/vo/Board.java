package com.kwon.board.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class Board implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7105936759154521682L;
	private int bno;			// 게시글 번호
	private int btype;			// 게시판 종류
	private String btitle;		// 제목
	private String bcontent;	// 게시글 내용
	private String bUserName;	// 게시글 작성자
	private String bUserId;		// 게시글 작성자 아이디
	private int bcount;			// 게시글 조회수
	private String boardfile;	// 게시글 첨부파일
	private Date bdate;			// 작성일
	private String bStatus;		// 삭제여부('Y'이면 삭제 X, 'N'이면 삭제 O)
	
	public Board() {}

	public Board(String btitle, String bcontent, String bUserName, String boardfile) {
		super();
		this.btitle = btitle;
		this.bcontent = bcontent;
		this.bUserName = bUserName;
		this.boardfile = boardfile;
	}

	public Board(int bno, int btype, String btitle, String bcontent, String bUserName, String bUserId, int bcount,
			String boardfile, Date bdate, String bStatus) {
		super();
		this.bno = bno;
		this.btype = btype;
		this.btitle = btitle;
		this.bcontent = bcontent;
		this.bUserName = bUserName;
		this.bUserId = bUserId;
		this.bcount = bcount;
		this.boardfile = boardfile;
		this.bdate = bdate;
		this.bStatus = bStatus;
	}
	
	public Board(int bno, int btype, String btitle, String bcontent, String bUserName, int bcount, String boardfile,
			Date bdate, String bStatus) {
		super();
		this.bno = bno;
		this.btype = btype;
		this.btitle = btitle;
		this.bcontent = bcontent;
		this.bUserName = bUserName;
		this.bcount = bcount;
		this.boardfile = boardfile;
		this.bdate = bdate;
		this.bStatus = bStatus;
	}

	public int getBno() {
		return bno;
	}

	public void setBno(int bno) {
		this.bno = bno;
	}

	public int getBtype() {
		return btype;
	}

	public void setBtype(int btype) {
		this.btype = btype;
	}

	public String getBtitle() {
		return btitle;
	}

	public void setBtitle(String btitle) {
		this.btitle = btitle;
	}

	public String getBcontent() {
		return bcontent;
	}

	public void setBcontent(String bcontent) {
		this.bcontent = bcontent;
	}

	public String getbUserName() {
		return bUserName;
	}

	public void setbUserName(String bUserName) {
		this.bUserName = bUserName;
	}

	public String getbUserId() {
		return bUserId;
	}

	public void setbUserId(String bUserId) {
		this.bUserId = bUserId;
	}

	public int getBcount() {
		return bcount;
	}

	public void setBcount(int bcount) {
		this.bcount = bcount;
	}

	public String getBoardfile() {
		return boardfile;
	}

	public void setBoardfile(String boardfile) {
		this.boardfile = boardfile;
	}

	public Date getBdate() {
		return bdate;
	}

	public void setBdate(Date bdate) {
		this.bdate = bdate;
	}

	public String getbStatus() {
		return bStatus;
	}

	public void setbStatus(String bStatus) {
		this.bStatus = bStatus;
	}

	@Override
	public String toString() {
		return "Board [bno=" + bno + ", btype=" + btype + ", btitle=" + btitle + ", bcontent=" + bcontent + ", bUserName="
				+ bUserName + ", bUserId=" + bUserId + ", bcount=" + bcount + ", boardfile=" + boardfile + ", bdate="
				+ bdate + ", bStatus=" + bStatus + "]";
	}
	
}
