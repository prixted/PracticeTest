package com.kwon.notice.model.service;

import java.sql.Connection;
import java.util.ArrayList;
import static com.kwon.common.JDBCTemplate.*;

import com.kwon.notice.model.dao.NoticeDao;
import com.kwon.notice.model.vo.Notice;

public class NoticeService {
	
	private NoticeDao nDao = new NoticeDao();
	private Connection conn;
	
	
	

	/**
	 * 페이징 처리용 게시글 개수 가져오기
	 * @return
	 */
	public int getListCount() {
		conn = getConnection();
		int listCount = nDao.getListCount(conn);
		close(conn);
		
		return listCount;
	}

	
	/**
	 * 공지사항 리스트 불러오기
	 * 2020.02.28 Kwon
	 * @param limit 
	 * @param currentPage 
	 * @return
	 */
	public ArrayList<Notice> noticeList(int currentPage, int limit) {
		ArrayList<Notice> list = new ArrayList<Notice>();
		conn = getConnection();
		
		list = nDao.noticeList(conn, currentPage, limit);
		
		close(conn);
		
		return list;
	}


	/**
	 * 공지사항 1개 불러오기
	 * 2020.02.28 Kwon
	 * @param nno
	 * @return
	 */
	public Notice noticeSelectOne(int nno) {
		Notice n = new Notice();
		conn = getConnection();
		
		n = nDao.noticeSelectOne(conn, nno);
		
		close(conn);
		
		return n;
	}

}
