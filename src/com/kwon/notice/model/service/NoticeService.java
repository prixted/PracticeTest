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

	/**
	 * 공지사항 추가
	 * 2020.02.29 Kwon
	 * @param n
	 * @return
	 */
	public int insertNotice(Notice n) {
		conn = getConnection();
		int result = nDao.insertNotice(conn, n);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	/**
	 * 공지사항 수정 보여주기
	 * 2020.03.01 Kwon
	 * @return
	 */
	public Notice updateViewNotice(int nno) {
		conn = getConnection();
		Notice n = new Notice();
		
		n = nDao.updateViewNotice(conn, nno);
		
		close(conn);
		
		return n;
	}

	/**
	 * 조회수 1 증가시켜주는 메소드
	 * @param nno
	 * @return
	 */
	public void noticeCount(int nno) {
		conn = getConnection();
		int result = 0;
		result = nDao.noticeCount(conn, nno);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		
		return ;
	}

	/**
	 * 공지사항 수정하기
	 * 2020.03.01 Kwon
	 * @param n
	 * @return
	 */
	public int updateNotice(Notice n) {
		int result = 0;
		conn = getConnection();
		
		result = nDao.updateNotice(conn, n);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		
		return result;
	}

	/**
	 * 공지사항 삭제하기 (STATUS = N 으로 변경하기)
	 * 2020.03.01 Kwon
	 * @param nno
	 * @return
	 */
	public int deleteNotice(int nno) {
		int result = 0;
		conn = getConnection();
		
		result = nDao.deleteNotice(conn, nno);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		
		return result;
	}

	/**
	 * 공지사항 검색용 리스트 숫자 가져오기
	 * 2020.03.01 Kwon
	 * @param category
	 * @param keyword
	 * @param currentPage
	 * @param limit
	 * @return
	 */
	public int getListSearchCount(String category, String keyword) {
		conn = getConnection();
		int listCount = nDao.getListSearchCount(conn, category, keyword);
		
		close(conn);
		
		return listCount;
	}
	
	/**
	 * 공지사항 검색하기
	 * 2020.03.01 Kwon
	 * @param con
	 * @param keyword
	 * @return
	 */
	public ArrayList<Notice> searchNoticeList(String category, String keyword, int currentPage, int limit) {
		ArrayList<Notice> list = new ArrayList<>();
		conn = getConnection();
		
		list = nDao.searchNoticeList(conn, category, keyword, currentPage, limit);
		
		close(conn);
		
		return list;
	}


}
