package com.kwon.board.model.dao;

import static com.kwon.common.JDBCTemplate.close;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOError;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import com.kwon.board.model.vo.Board;
import com.kwon.notice.model.vo.Notice;

public class BoardDao {
	private Properties prop;
	
	public BoardDao() {
		prop = new Properties();
		
		String filePath = BoardDao.class.getResource("/config/board-query.properties").getPath();
		
		
		try {
			prop.load(new FileReader(filePath));
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e) {
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
				System.out.println("[Dao] listCount : " + listCount);
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
		
		return listCount;
	}

	public ArrayList<Board> boardList(Connection conn, int currentPage, int limit) {
		ArrayList<Board> list = null;
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
				Board b = new Board();
				
				b.setBno(rset.getInt("B_NO"));
				b.setBtype(rset.getInt("B_TYPE"));
				b.setBtitle(rset.getString("B_TITLE"));
				b.setbUserName(rset.getString("B_USERNAME"));
				b.setbUserId(rset.getString("B_USERID"));
				b.setBcontent(rset.getString("B_CONTENT"));
				b.setBcount(rset.getInt("B_COUNT"));
				b.setBoardfile(rset.getString("B_BOARDFILE"));
				b.setBdate(rset.getDate("B_DATE"));
				b.setbStatus(rset.getString("B_STATUS"));
				
				list.add(b);
				
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
