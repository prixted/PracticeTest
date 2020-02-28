package com.kwon.notice.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class Notice implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7277682523690336513L;
	private int nno;
	private String nTitle;
	private String nWriter;
	private String nContent;
	private int nCount;
	private Date nDate;
	private String nStatus;
	
	public Notice() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Notice(int nno, String nTitle, String nWriter, String nContent, int nCount, Date nDate, String nStatus) {
		super();
		this.nno = nno;
		this.nTitle = nTitle;
		this.nWriter = nWriter;
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

	public String getnWriter() {
		return nWriter;
	}

	public void setnWriter(String nWriter) {
		this.nWriter = nWriter;
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

	@Override
	public String toString() {
		return "Notice [nno=" + nno + ", nTitle=" + nTitle + ", nWriter=" + nWriter + ", nContent=" + nContent
				+ ", nCount=" + nCount + ", nDate=" + nDate + ", nStatus=" + nStatus + "]";
	}
	
	

}
