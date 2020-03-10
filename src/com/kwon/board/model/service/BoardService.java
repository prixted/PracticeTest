package com.kwon.board.model.service;

import static com.kwon.common.JDBCTemplate.close;
import static com.kwon.common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import com.kwon.board.model.dao.BoardDao;
import com.kwon.board.model.vo.Board;

public class BoardService {

	private BoardDao bDao = new BoardDao();
	private Connection conn;
	
	
	/**
	 * 페이징 처리용 게시글 개수 가져오기
	 * 2020.03.02 Kwon
	 * @return
	 */
	public int getListCount() {
		conn = getConnection();
		int listCount = bDao.getListCount(conn);
		close(conn);
		
		return listCount;
	}


	/**
	 * 게시글 리스트 불러오기
	 * 2020.03.02 Kwon
	 * @param currentPage
	 * @param limit
	 * @return
	 */
	public ArrayList<Board> boardList(int currentPage, int limit) {
		ArrayList<Board> list = new ArrayList<>();
		conn = getConnection();
		
		list = bDao.boardList(conn, currentPage, limit);
		
		close(conn);
		
		return list;
		

	}

}
