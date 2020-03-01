package com.kwon.notice.model.dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;
import static com.kwon.common.JDBCTemplate.*;

import com.kwon.member.model.dao.MemberDao;
import com.kwon.notice.model.vo.Notice;

public class NoticeDao {
	private Properties prop;

	public NoticeDao() {
		prop = new Properties();
		
		String filePath = MemberDao.class.getResource("/config/notice-query.properties").getPath();
		
		try {
			prop.load(new FileReader(filePath));
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		
	}

	
	/**
	 * 페이징처리용 전체 글 개수 가져오기
	 * @param conn
	 * @return
	 */
	public int getListCount(Connection conn) {
		int listCount = 0;
		Statement stmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("listCount");
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(sql);
			
			if(rset.next()) {
				listCount = rset.getInt(1);
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
		
		return listCount;
	}

	
	/**
	 * Notice 리스트 가져오기
	 * @param conn
	 * @param limit 
	 * @param currentPage 
	 * @return
	 */
	public ArrayList<Notice> noticeList(Connection conn, int currentPage, int limit) {
		ArrayList<Notice> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			int startRow = (currentPage-1)*limit +1;
			int endRow = startRow + limit -1;
			
			pstmt.setInt(1, endRow);
			pstmt.setInt(2, startRow);
			
			rset = pstmt.executeQuery();
			
			list = new ArrayList<>();
			
			while(rset.next()) {
				Notice n = new Notice();
				
				n.setNno(rset.getInt("N_NO"));
				n.setnTitle(rset.getString("N_TITLE"));
				n.setnUserName(rset.getString("N_USERNAME"));
				n.setnUserId(rset.getString("N_USERID"));
				n.setnContent(rset.getString("N_CONTENT"));
				n.setnCount(rset.getInt("N_COUNT"));
				n.setnDate(rset.getDate("N_DATE"));
				n.setnStatus(rset.getString("N_STATUS"));
				
				list.add(n);
				
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}


	/**
	 * 게시글 1개 불러오기
	 * 2020.02.28 Kwon
	 * @param conn
	 * @param nno
	 * @return
	 */
	public Notice noticeSelectOne(Connection conn, int nno) {
		Notice n = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectOne");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, nno);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				n = new Notice();
				
				n.setNno(rset.getInt("N_NO"));
				n.setnTitle(rset.getString("N_TITLE"));
				n.setnUserName(rset.getString("N_USERNAME"));
				n.setnUserId(rset.getString("N_USERID"));
				n.setnContent(rset.getString("N_CONTENT"));
				n.setnCount(rset.getInt("N_COUNT"));
				n.setnDate(rset.getDate("N_DATE"));
				n.setnStatus(rset.getString("N_STATUS"));
				
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return n;
	}


	public int insertNotice(Connection conn, Notice n) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertNotice");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, n.getnTitle());
			pstmt.setString(2, n.getnUserName());
			pstmt.setString(3, n.getnUserId());
			pstmt.setString(4, n.getnContent());
			pstmt.setDate(5, n.getnDate());
			
			result = pstmt.executeUpdate();
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}


	/**
	 * 공지사항 수정 보여주기
	 * 2020.03.01 Kwon
	 * @param conn
	 * @return
	 */
	public Notice updateViewNotice(Connection conn, int nno) {
		Notice n = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectOne");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, nno);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				n = new Notice();
				
				n.setNno(nno);
				n.setnTitle(rset.getString("N_TITLE"));
				n.setnUserName(rset.getString("N_USERNAME"));
				n.setnUserId(rset.getString("N_USERID"));
				n.setnContent(rset.getString("N_CONTENT"));
				n.setnDate(rset.getDate("N_DATE"));
				n.setnStatus(rset.getString("N_STATUS"));
				
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return n;
	}


	/**
	 * 공지사항 조회수 증가 메소드
	 * 2020.03.01 Kwon
	 * @param nno
	 * @return 
	 */
	public int noticeCount(Connection conn, int nno) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("countUp");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, nno);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		
		return result;
		
	}


	/**
	 * 공지사항 수정하기
	 * 2020.03.01 kwon
	 * @param conn
	 * @param n
	 * @return
	 */
	public int updateNotice(Connection conn, Notice n) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateNotice");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, n.getnTitle());
			pstmt.setString(2, n.getnUserName());
			pstmt.setString(3, n.getnUserId());
			pstmt.setString(4, n.getnContent());
			pstmt.setDate(5, n.getnDate());
			
			pstmt.setInt(6, n.getNno());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}


	/**
	 * 공지사항 삭제하기 (STATUS = N으로 변경하기)
	 * 2020.03.01 Kwon
	 * @param conn
	 * @param nno
	 * @return
	 */
	public int deleteNotice(Connection conn, int nno) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteNotice");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, nno);
			
			result = pstmt.executeUpdate();
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}


	/**
	 * 공지사항 검색하기
	 * 2020.03.01 Kwon
	 * @param conn
	 * @param con
	 * @param keyword
	 * @return
	 */
	public ArrayList<Notice> searchNotice(Connection conn, String con, String keyword) {
		ArrayList<Notice> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = null;
		
		switch(con) {
			case "userName" : sql = prop.getProperty("searchNameNotice"); break;
			case "title" : sql = prop.getProperty("searchTitleNotice"); break;
			case "content" : sql = prop.getProperty("searchContentNotice"); break;
		}
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, keyword);
			
			rset = pstmt.executeQuery();
			
			list = new ArrayList<Notice>();
			
			while(rset.next()) {
				Notice n = new Notice();
				
				n.setNno(rset.getInt("N_NO"));
				n.setnTitle(rset.getString("N_TITLE"));
				n.setnUserName(rset.getString("N_USERNAME"));
				n.setnUserId(rset.getString("N_USERID"));
				n.setnContent(rset.getString("N_CONTENT"));
				n.setnCount(rset.getInt("N_COUNT"));
				n.setnDate(rset.getDate("N_DATE"));
				n.setnStatus(rset.getString("N_STATUS"));
				
				list.add(n);
				
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}



}
