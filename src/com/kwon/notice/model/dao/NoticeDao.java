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
				n.setnWriter(rset.getString("N_WRITER"));
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
				n.setnWriter(rset.getString("N_WRITER"));
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



}
