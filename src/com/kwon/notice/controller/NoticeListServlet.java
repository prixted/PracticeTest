package com.kwon.notice.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kwon.notice.model.service.NoticeService;
import com.kwon.notice.model.vo.Notice;
import com.kwon.notice.model.vo.PageInfo;

/**
 * Servlet implementation class NoticeListServlet
 */
@WebServlet("/selectList.no")
public class NoticeListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Notice> list = new ArrayList<>();
		
		NoticeService ns = new NoticeService();
		
		System.out.println("처음시작");
		// 페이징 처리용 변수 설정
		int startPage;  	// (포이는 페이지 중)가장 앞 페이지(1~10 : 1 , 11~20 : 11)	
		int endPage;		// (보이는 페이지 중)가장 뒷 페이지 (1~10 : 10, 11~20 : 20)
		int maxPage;		// 전체 페이지 중 가장 마지막 페이지
		int currentPage;	// 사용자가 위치한 현재 페이지
		int limit;			// 한 페이지당 보여줄 게시글 수  (1페이지에 10개, 2페이지에 10개......)
		int pageLimit;		// 밑에 보여주는 페이지 수(1~5로 할래 1~10으로 할래?)
		
		currentPage = 1; 	// 처음 접속 시 페이지는 1페이지부터 시작한다
		limit = 10;			// 글 개수 10개로 제한하기 (나중에 select로 10 20 30 이렇게 바꾸고 싶다)
		pageLimit = 5;		// 아래쪽 페이지 5개로 제한하기(10으로 둬도 괜찮을 것 같기도 하구) 
		
		int maxPageLimit = limit/pageLimit;	// 마지막 페이지 막아주는거... 이거 왜 필요하짇ㄷ
		
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		
		// 페이징 처리(총 페이지 갯수)
		int listCount = (ns.getListCount()/maxPageLimit);
		if (listCount == 0) { // 이걸 안해주니까 게시글이 한개 일 땐 아래쪽에 번호가 안나오네
			listCount = 1;
		}
		
		// 마지막 페이지 갯수 구하기 만약 13개라면 1,2페이지가 나와야 한다   (int)(13 / 5.9 = 2.xx) = 2
		maxPage = (int)((double)listCount/pageLimit+0.9); 
		
		// 시작 페이지 계산하기
		// 1~10 : 7, 7/10 --> 0.7 --> 1.6--> 1-1 -- 0 * 10 + 1;
		// 11~20: 19, 19/10 --> 1.9 --> 2.8--> 2-1 -- 1*10 +1;
		startPage = ((int)((double)currentPage/pageLimit + 0.99)-1) * pageLimit + 1;
		
		// 마지막 페이지
		// 1~10 : 10
		// 11~20: 20
		endPage = startPage + pageLimit -1;

		// 만약 마지막 페이지보다 현재 게시글이 끝나는 페이지가 적다면
		// 1~10 : 7
		if(endPage > maxPage) {
			endPage = maxPage;
		}
		
		list = ns.noticeList(currentPage, limit);
		
		String page = "";
		System.out.println("[servlet] 공지사항 목록 : " + list);

		if(list != null) {
			page = "views/notice/noticeList.jsp";
			
			PageInfo pi = new PageInfo(currentPage, listCount, limit, maxPage, startPage, endPage);
			System.out.println("StartPage : " + startPage);
			request.setAttribute("list", list);
			request.setAttribute("pi", pi);
		} else {
			page = "views/common/error.jsp";
			request.setAttribute("msg", "게시글 목록 조회 실패!");
		}
		
		request.getRequestDispatcher(page).forward(request, response);
		

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
