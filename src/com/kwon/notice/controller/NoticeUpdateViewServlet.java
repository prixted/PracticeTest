package com.kwon.notice.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kwon.notice.model.service.NoticeService;
import com.kwon.notice.model.vo.Notice;

/**
 * Servlet implementation class NoticeUpdateServlet
 */
@WebServlet("/nUpdateView.no")
public class NoticeUpdateViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeUpdateViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 업데이트니까 int값으로 result를 받아주자
		Notice n = new Notice();
		int nno = Integer.parseInt(request.getParameter("nno"));
		
		n = new NoticeService().updateViewNotice(nno); 
		System.out.println("[Servlet] nUpview Notice : " + n);
		
		String page = "";
		
		if(n != null) {		
			page = "views/notice/noticeUpdateForm.jsp";
			request.setAttribute("notice", n);
		} else {
			page = "views/common/error.jsp";
			request.setAttribute("msg", "공지사항 수정 불러오기 중 문제 발생!!");
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
