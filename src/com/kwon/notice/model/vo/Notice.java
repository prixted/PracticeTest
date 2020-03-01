package com.kwon.notice.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class Notice implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7277682523690336513L;
	private int nno;
	private String nTitle;   // 글 제목
	private String nUserName;  // 글 쓴 이름
	private String nUserId;	 // 아이디
	private String nContent; // 내용
	private int nCount;
	private Date nDate;
	private String nStatus;
	
	public Notice() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public Notice(int nno, String nTitle, String nUserName, String nUserId, String nContent, int nCount, Date nDate,
			String nStatus) {
		super();
		this.nno = nno;
		this.nTitle = nTitle;
		this.nUserName = nUserName;
		this.nUserId = nUserId;
		this.nContent = nContent;
		this.nCount = nCount;
		this.nDate = nDate;
		this.nStatus = nStatus;
	}

	public int getNno() {
		return nno;
	}

	public void setNno(int nno) {
		this.nno = nno;
	}

	public String getnTitle() {
		return nTitle;
	}

	public void setnTitle(String nTitle) {
		this.nTitle = nTitle;
	}

	public String getnContent() {
		return nContent;
	}

	public void setnContent(String nContent) {
		this.nContent = nContent;
	}

	public int getnCount() {
		return nCount;
	}

	public void setnCount(int nCount) {
		this.nCount = nCount;
	}

	public Date getnDate() {
		return nDate;
	}

	public void setnDate(Date nDate) {
		this.nDate = nDate;
	}

	public String getnStatus() {
		return nStatus;
	}

	public void setnStatus(String nStatus) {
		this.nStatus = nStatus;
	}

	public String getnUserName() {
		return nUserName;
	}

	public void setnUserName(String nUserName) {
		this.nUserName = nUserName;
	}

	public String getnUserId() {
		return nUserId;
	}

	public void setnUserId(String nUserId) {
		this.nUserId = nUserId;
	}


	@Override
	public String toString() {
		return "Notice [nno=" + nno + ", nTitle=" + nTitle + ", nUserName=" + nUserName + ", nUserId=" + nUserId
				+ ", nContent=" + nContent + ", nCount=" + nCount + ", nDate=" + nDate + ", nStatus=" + nStatus + "]";
	}

	
	
	
	

}
