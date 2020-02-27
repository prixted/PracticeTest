package com.kwon.member.model.service;

import java.sql.Connection;

import com.kwon.member.exception.MemberException;
import com.kwon.member.model.dao.MemberDao;
import com.kwon.member.model.vo.Member;

import static com.kwon.common.JDBCTemplate.*;

public class MemberService {
	
	private Connection conn;
	private MemberDao mDao = new MemberDao();
	
	/**
	 * 멤버 추가
	 * 2020.02.27 권구현
	 * @param m
	 * @return
	 */
	public int insertMember(Member m) {
		int result = 0;
		conn = getConnection();
		
		result = mDao.insertMember(conn, m);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		
		return result;
		
	}

	/**
	 * 로그인
	 * 2020.02.27 권구현
	 * @param m
	 * @return
	 * @throws MemberException 
	 */
	public Member loginMember(Member m) throws MemberException {
		conn = getConnection();
		
		Member result = mDao.loginMember(conn, m);
		
		close(conn);
		return result;
	}

}
