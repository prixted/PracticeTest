package com.kwon.member.model.dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import static com.kwon.common.JDBCTemplate.*;

import com.kwon.member.exception.MemberException;
import com.kwon.member.model.vo.Member;

public class MemberDao {
	private Properties prop;

	public MemberDao() {
		prop = new Properties();
		
		String filePath = MemberDao.class.getResource("/config/member-query.properties").getPath();
		
		try {
			prop.load(new FileReader(filePath));
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		
	}

	/**
	 * 회원가입
	 * 2020.02.27 권구현
	 * @param conn
	 * @param m
	 * @return
	 */
	public int insertMember(Connection conn, Member m) {
		int result = 0;
		// 아 마이바티스 쓰고싶은데!!!!!!!!
		PreparedStatement pstmt = null;
		System.out.println("[Dao] insertMember : " + m);
		String sql = prop.getProperty("insertMember");

		try {
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, m.getUserId());
			pstmt.setString(2, m.getUserPwd());
			pstmt.setString(3, m.getUserName());
			pstmt.setString(4, m.getEmail());
			pstmt.setString(5, m.getPhone());
			pstmt.setString(6, m.getAddress());
			
			result = pstmt.executeUpdate();
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
		
	
	}

	/**
	 * 로그인
	 * 2020.02.27 권구현
	 * @param conn
	 * @param m
	 * @return
	 * @throws MemberException 
	 */
	public Member loginMember(Connection conn, Member m) throws MemberException {
		Member result = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("loginMember");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, m.getUserId());
			pstmt.setString(2, m.getUserPwd());
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				result = new Member();
				result.setUserId(m.getUserId());
				result.setUserPwd(m.getUserPwd());
				result.setUserName(rset.getString("USER_NAME"));
				result.setPhone(rset.getString("USER_PHONE"));
				result.setAddress(rset.getString("USER_ADDRESS"));
				result.setAge(rset.getInt("USER_AGE"));
				result.setEnrollDate(rset.getDate("USER_ENDATE"));
				result.setUserStatus(rset.getString("USER_STATUS"));
				result.setUserRoles(rset.getInt("USER_ROLES"));
				result.setUserNo(rset.getInt("USER_NO"));
			}
			
		} catch(SQLException e) {
			throw new MemberException(e.getMessage());
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return result;
	}

}
